/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.controller;

import mycompany.model.Pengeluaran;
import mycompany.service.LayananPengeluaran;
import mycompany.Interface.PengeluaranInterface;

/**
 *
 * @author FARHAN
 */
import java.util.List;

public class PengeluaranController {

    private final LayananPengeluaran layanan;

    public PengeluaranController(LayananPengeluaran l) {
        layanan = l;
    }

    public void tambah(Pengeluaran p) throws Exception {
        layanan.tambahPengeluaran(p);
    }

    public List<Pengeluaran> tampilkan() throws Exception {
        return layanan.tampilkanPengeluaran();
    }

    public void ubah(Pengeluaran p) throws Exception {
        layanan.ubahPengeluaran(p);
    }

    public void hapus(int id) throws Exception {
        layanan.hapusPengeluaran(id);
    }

    public PengeluaranInterface.Ringkasan ringkasan() throws Exception {
        return layanan.ambilRingkasan();
    }
}
