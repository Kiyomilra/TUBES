/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.Interface.Implementasi;

import mycompany.db.KoneksiDatabase;
import mycompany.model.Pengeluaran;
import mycompany.Interface.PengeluaranInterface;
import java.sql.*;
import java.util.*;
import mycompany.db.Database;

/**
 *
 * @author FARHAN
 */
public class PengeluaranIMP implements PengeluaranInterface {

    private final Database koneksi;

    public PengeluaranIMP(Database koneksi) {
        this.koneksi = koneksi;
    }

    private Pengeluaran map(ResultSet r) throws SQLException {
        return new Pengeluaran(r.getInt("id"), r.getDate("expense_date").toLocalDate(), r.getString("description"), r.getBigDecimal("amount"), r.getInt("category_id"), r.getString("category_name"), r.getString("payment_method"));
    }

    public void simpan(Pengeluaran p) throws Exception {
        String q = "INSERT INTO expenses(expense_date,description,amount,category_id,payment_method) VALUES(?,?,?,?,?)";
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement(q)) {
            s.setDate(1, java.sql.Date.valueOf(p.getTanggal()));
            s.setString(2, p.getDeskripsi().trim());
            s.setBigDecimal(3, p.getJumlah());
            s.setInt(4, p.getIdKategori());
            s.setString(5, p.getMetodePembayaran());
            s.executeUpdate();
        }
    }

    public List<Pengeluaran> ambilSemua() throws Exception {
        List<Pengeluaran> a = new ArrayList<>();
        String q = "SELECT e.*,c.name category_name FROM expenses e JOIN categories c ON c.id=e.category_id ORDER BY e.expense_date DESC,e.id DESC";
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement(q); ResultSet r = s.executeQuery()) {
            while (r.next()) {
                a.add(map(r));
            }
        }
        return a;
    }

    public void ubah(Pengeluaran p) throws Exception {
        String q = "UPDATE expenses SET expense_date=?,description=?,amount=?,category_id=?,payment_method=? WHERE id=?";
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement(q)) {
            s.setDate(1, java.sql.Date.valueOf(p.getTanggal()));
            s.setString(2, p.getDeskripsi().trim());
            s.setBigDecimal(3, p.getJumlah());
            s.setInt(4, p.getIdKategori());
            s.setString(5, p.getMetodePembayaran());
            s.setInt(6, p.getId());
            s.executeUpdate();
        }
    }

    @Override
    public void hapus(int id) throws Exception {
        try (Connection c = koneksi.getConnection()) {

            try (PreparedStatement s = c.prepareStatement(
                    "DELETE FROM expenses WHERE id=?")) {
                s.setInt(1, id);
                s.executeUpdate();
            }

            // Memeriksa apakah masih ada transaksi
            String cek = "SELECT COUNT(*) FROM expenses";

            try (PreparedStatement s = c.prepareStatement(cek); ResultSet r = s.executeQuery()) {

                if (r.next() && r.getInt(1) == 0) {

                    // Reset ID jika semua transaksi sudah dihapus
                    try (Statement st = c.createStatement()) {
                        st.executeUpdate(
                                "ALTER TABLE expenses AUTO_INCREMENT = 1"
                        );
                    }
                }
            }
        }
    }

    public Ringkasan ambilRingkasan() throws Exception {
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement("SELECT COALESCE(SUM(amount),0) total,COUNT(*) jumlah FROM expenses"); ResultSet r = s.executeQuery()) {
            r.next();
            return new Ringkasan(r.getBigDecimal("total"), r.getInt("jumlah"));
        }
    }
}
