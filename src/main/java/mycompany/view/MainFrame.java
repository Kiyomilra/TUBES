package mycompany.view;

import javax.swing.*;
import java.awt.*;
import mycompany.controller.PengeluaranController;
import mycompany.controller.KategoriController;

public class MainFrame extends JFrame {

    public MainFrame(PengeluaranController p, KategoriController k) {
        setTitle("LabaKu - Sistem Pembukuan Keuangan Digital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        JTabbedPane tab = new JTabbedPane();

        DashboardPanel d = new DashboardPanel(p);

        tab.addTab("Dashboard", d);
        tab.addTab("Pengeluaran", new PengeluaranPanel(p, k));

        tab.addChangeListener(e -> {
            if (tab.getSelectedIndex() == 0) {
                d.muat();
            }
        });

        add(tab, BorderLayout.CENTER);
    }
}
