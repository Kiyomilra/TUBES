package mycompany.view;

import mycompany.controller.PengeluaranController;
import mycompany.Interface.PengeluaranInterface;
import mycompany.model.Pengeluaran;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class DashboardPanel extends JPanel {

    private final PengeluaranController c;
    private final JLabel total = new JLabel();
    private final JLabel jumlah = new JLabel();

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Tanggal", "Deskripsi", "Uang yang Dikeluarkan"}, 0
    ) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    public DashboardPanel(PengeluaranController c) {
        this.c = c;

        setLayout(new BorderLayout(15, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JPanel atas = new JPanel(new BorderLayout(10, 15));

        JLabel judul = new JLabel("LabaKu — Dashboard");
        judul.setFont(new Font("Arial", Font.BOLD, 24));
        atas.add(judul, BorderLayout.NORTH);

        JPanel ringkasan = new JPanel(new GridLayout(1, 2, 15, 0));
        ringkasan.add(kartu("Total Pengeluaran", total));
        ringkasan.add(kartu("Jumlah Transaksi", jumlah));

        atas.add(ringkasan, BorderLayout.CENTER);
        add(atas, BorderLayout.NORTH);

        JPanel riwayat = new JPanel(new BorderLayout(10, 10));

        JLabel subjudul = new JLabel("Riwayat Pengeluaran");
        subjudul.setFont(new Font("Arial", Font.BOLD, 18));

        riwayat.add(subjudul, BorderLayout.NORTH);
        riwayat.add(new JScrollPane(tabel), BorderLayout.CENTER);

        add(riwayat, BorderLayout.CENTER);

        muat();
    }

    private JPanel kartu(String nama, JLabel nilai) {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel label = new JLabel(nama);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        nilai.setFont(new Font("Arial", Font.BOLD, 22));

        p.add(label, BorderLayout.NORTH);
        p.add(nilai, BorderLayout.CENTER);

        return p;
    }

    public void muat() {
        try {
            PengeluaranInterface.Ringkasan r = c.ringkasan();

            NumberFormat rupiah
                    = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            total.setText(rupiah.format(r.getTotal()));
            jumlah.setText(String.valueOf(r.getJumlah()));

            model.setRowCount(0);

            for (Pengeluaran p : c.tampilkan()) {
                model.addRow(new Object[]{
                    p.getTanggal(),
                    p.getDeskripsi(),
                    rupiah.format(p.getJumlah())
                });
            }

        } catch (Exception e) {
            total.setText("Tidak tersedia");
            jumlah.setText("-");
        }
    }
}
