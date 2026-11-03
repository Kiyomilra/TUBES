/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.view;

import mycompany.controller.KategoriController;
import mycompany.model.Kategori;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 *
 * @author FARHAN
 */
import java.util.Objects;
import javax.swing.table.DefaultTableModel;

public class KategoriPanel extends JPanel {

    private final KategoriController c;
    private final DefaultTableModel m = new DefaultTableModel(new Object[]{"ID", "Nama Kategori", "Deskripsi"}, 0) {
        public boolean isCellEditable(int r, int col) {
            return false;
        }
    };
    private final JTable t = new JTable(m);

    public KategoriPanel(KategoriController c) {
        this.c = c;
        setLayout(new BorderLayout());
        add(new JScrollPane(t), BorderLayout.CENTER);
        JPanel b = new JPanel();
        JButton add = new JButton("Tambah"), edit = new JButton("Ubah"), del = new JButton("Hapus"), refresh = new JButton("Muat Ulang");
        b.add(add);
        b.add(edit);
        b.add(del);
        b.add(refresh);
        add(b, BorderLayout.SOUTH);
        add.addActionListener(e -> tambah());
        edit.addActionListener(e -> ubah());
        del.addActionListener(e -> hapus());
        refresh.addActionListener(e -> muat());
        muat();
    }

    private void muat() {
        try {
            m.setRowCount(0);
            for (Kategori k : c.tampilkan()) {
                m.addRow(new Object[]{k.getId(), k.getNamaKategori(), k.getDeskripsi()});
            }
        } catch (Exception e) {
            error(e);
        }
    }

    private void tambah() {
        JTextField n = new JTextField(), d = new JTextField();
        if (JOptionPane.showConfirmDialog(this, new Object[]{"Nama", n, "Deskripsi", d}, "Tambah Kategori", JOptionPane.OK_CANCEL_OPTION) == 0)try {
            c.tambah(new Kategori(0, n.getText(), d.getText()));
            muat();
        } catch (Exception e) {
            error(e);
        }
    }

    private void ubah() {
        int r = t.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Pilih data.");
            return;
        }
        int id = (int) m.getValueAt(r, 0);
        JTextField n = new JTextField("" + m.getValueAt(r, 1)), d = new JTextField(Objects.toString(m.getValueAt(r, 2), ""));
        if (JOptionPane.showConfirmDialog(this, new Object[]{"Nama", n, "Deskripsi", d}, "Ubah Kategori", JOptionPane.OK_CANCEL_OPTION) == 0)try {
            c.ubah(new Kategori(id, n.getText(), d.getText()));
            muat();
        } catch (Exception e) {
            error(e);
        }
    }

    private void hapus() {
        int r = t.getSelectedRow();
        if (r < 0) {
            return;
        }
        try {
            c.hapus((int) m.getValueAt(r, 0));
            muat();
        } catch (Exception e) {
            error(e);
        }
    }

    private void error(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
    }
}
