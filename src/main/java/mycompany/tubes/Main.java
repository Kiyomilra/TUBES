/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package mycompany.tubes;

import mycompany.Interface.Implementasi.*;
import mycompany.service.*;
import mycompany.controller.*;
import mycompany.view.MainFrame;

/**
 *
 * @author FARHAN
 */
import javax.swing.*;
import mycompany.Interface.KategoriInterface;
import mycompany.Interface.PengeluaranInterface;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                KategoriInterface rk = new KategoriIMP();
                PengeluaranInterface rp = new PengeluaranIMP();

                LayananKategori lk = new LayananKategori(rk);
                LayananPengeluaran lp = new LayananPengeluaran(rp);

                KategoriController ck = new KategoriController(lk);
                PengeluaranController cp = new PengeluaranController(lp);

                new MainFrame(cp, ck).setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Gagal menjalankan aplikasi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
