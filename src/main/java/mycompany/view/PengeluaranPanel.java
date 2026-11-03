package mycompany.view;

import mycompany.model.*;
import mycompany.controller.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PengeluaranPanel extends JPanel {

    private final PengeluaranController c;
    private final KategoriController kc;

    private final DefaultTableModel m = new DefaultTableModel(
            new Object[]{
                "ID", "Tanggal", "Deskripsi",
                "Uang yang Dikeluarkan",
                "Kategori", "Pembayaran", "IDKategori"
            }, 0
    ) {
        public boolean isCellEditable(int r, int x) {
            return false;
        }
    };

    private final JTable t = new JTable(m);

    public PengeluaranPanel(PengeluaranController c, KategoriController kc) {
        this.c = c;
        this.kc = kc;

        setLayout(new BorderLayout());
        add(new JScrollPane(t), BorderLayout.CENTER);

        JPanel b = new JPanel();

        JButton a = new JButton("Tambah");
        JButton u = new JButton("Ubah");
        JButton h = new JButton("Hapus");
        JButton r = new JButton("Muat Ulang");

        b.add(a);
        b.add(u);
        b.add(h);
        b.add(r);

        add(b, BorderLayout.SOUTH);

        a.addActionListener(e -> tambah());
        u.addActionListener(e -> ubah());
        h.addActionListener(e -> hapus());
        r.addActionListener(e -> muat());

        t.getColumnModel().removeColumn(
                t.getColumnModel().getColumn(6)
        );

        muat();
    }

    private JComboBox<Kategori> kategori() throws Exception {
        JComboBox<Kategori> x = new JComboBox<>();

        for (Kategori k : kc.tampilkan()) {
            x.addItem(k);
        }

        return x;
    }

    private void form(boolean edit) {
        try {
            int row = edit ? t.getSelectedRow() : -1;

            if (edit && row < 0) {
                JOptionPane.showMessageDialog(
                        this, "Pilih data terlebih dahulu."
                );
                return;
            }

            int id = edit ? (int) m.getValueAt(row, 0) : 0;

            JTextField tanggal = new JTextField(
                    edit ? "" + m.getValueAt(row, 1)
                            : LocalDate.now().toString()
            );

            JTextField deskripsi = new JTextField(
                    edit ? "" + m.getValueAt(row, 2) : ""
            );

            JTextField jumlah = new JTextField(
                    edit ? "" + m.getValueAt(row, 3) : ""
            );

            JComboBox<Kategori> kat = kategori();

            if (edit) {
                int kid = (int) m.getValueAt(row, 6);

                for (int i = 0; i < kat.getItemCount(); i++) {
                    if (kat.getItemAt(i).getId() == kid) {
                        kat.setSelectedIndex(i);
                    }
                }
            }

            JComboBox<String> bayar = new JComboBox<>(
                    new String[]{"Cash", "QRIS", "Transfer Bank"}
            );

            if (edit) {
                bayar.setSelectedItem(m.getValueAt(row, 5));
            }

            Object[] form = {
                "Tanggal (YYYY-MM-DD)", tanggal,
                "Deskripsi", deskripsi,
                "Uang yang Dikeluarkan (Rp)", jumlah,
                "Kategori", kat,
                "Pembayaran", bayar
            };

            if (JOptionPane.showConfirmDialog(
                    this, form,
                    edit ? "Ubah Pengeluaran" : "Tambah Pengeluaran",
                    JOptionPane.OK_CANCEL_OPTION
            ) == JOptionPane.OK_OPTION) {

                Kategori k = (Kategori) kat.getSelectedItem();

                if (k == null) {
                    JOptionPane.showMessageDialog(
                            this, "Pilih kategori terlebih dahulu."
                    );
                    return;
                }

                Pengeluaran p = new Pengeluaran(
                        id,
                        LocalDate.parse(tanggal.getText().trim()),
                        deskripsi.getText(),
                        new BigDecimal(jumlah.getText().trim()),
                        k.getId(),
                        k.getNamaKategori(),
                        (String) bayar.getSelectedItem()
                );

                if (edit) {
                    c.ubah(p);
                } else {
                    c.tambah(p);
                }

                muat();
            }

        } catch (Exception e) {
            error(e);
        }
    }

    private void tambah() {
        form(false);
    }

    private void ubah() {
        form(true);
    }

    private void hapus() {
        int r = t.getSelectedRow();

        if (r < 0) {
            JOptionPane.showMessageDialog(
                    this, "Pilih data yang ingin dihapus."
            );
            return;
        }

        if (JOptionPane.showConfirmDialog(
                this, "Hapus data terpilih?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {

            try {
                c.hapus((int) m.getValueAt(r, 0));
                muat();
            } catch (Exception e) {
                error(e);
            }
        }
    }

    public void muat() {
        try {
            m.setRowCount(0);

            for (Pengeluaran p : c.tampilkan()) {
                m.addRow(new Object[]{
                    p.getId(),
                    p.getTanggal(),
                    p.getDeskripsi(),
                    p.getJumlah(),
                    p.getNamaKategori(),
                    p.getMetodePembayaran(),
                    p.getIdKategori()
                });
            }

        } catch (Exception e) {
            error(e);
        }
    }

    private void error(Exception e) {
        JOptionPane.showMessageDialog(
                this, e.getMessage(),
                "Kesalahan", JOptionPane.ERROR_MESSAGE
        );
    }
}
