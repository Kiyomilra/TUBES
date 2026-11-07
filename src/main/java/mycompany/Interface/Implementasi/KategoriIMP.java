/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.Interface.Implementasi;

import mycompany.model.Kategori;
import mycompany.Interface.KategoriInterface;
import mycompany.db.KoneksiDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FARHAN
 */
import java.util.*;
import mycompany.db.Database;

public class KategoriIMP implements KategoriInterface {

    private final Database koneksi;

    public KategoriIMP(Database koneksi) {
        this.koneksi = koneksi;
    }

    public void simpan(Kategori k) throws Exception {
        String q = "INSERT INTO categories(name,description) VALUES(?,?)";
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement(q)) {
            s.setString(1, k.getNamaKategori().trim());
            s.setString(2, k.getDeskripsi());
            s.executeUpdate();
        }
    }

    public List<Kategori> ambilSemua() throws Exception {
        List<Kategori> a = new ArrayList<>();
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement("SELECT * FROM categories ORDER BY name"); ResultSet r = s.executeQuery()) {
            while (r.next()) {
                a.add(new Kategori(r.getInt("id"), r.getString("name"), r.getString("description")));
            }
        }
        return a;
    }

    public void ubah(Kategori k) throws Exception {
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement("UPDATE categories SET name=?,description=? WHERE id=?")) {
            s.setString(1, k.getNamaKategori().trim());
            s.setString(2, k.getDeskripsi());
            s.setInt(3, k.getId());
            s.executeUpdate();
        }
    }

    public void hapus(int id) throws Exception {
        try (Connection c = koneksi.getConnection(); PreparedStatement s = c.prepareStatement("DELETE FROM categories WHERE id=?")) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }
}
