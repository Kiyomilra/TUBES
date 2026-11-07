/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.controller;

import java.util.List;
import mycompany.model.Kategori;
import mycompany.service.LayananKategoriInterface;

/**
 *
 * @author FARHAN
 */
public class KategoriController {

    private final LayananKategoriInterface layanan;

    public KategoriController(LayananKategoriInterface l) {
        layanan = l;
    }

    public void tambah(Kategori k) throws Exception {
        layanan.tambahKategori(k);
    }

    public List<Kategori> tampilkan() throws Exception {
        return layanan.tampilkanKategori();
    }

    public void ubah(Kategori k) throws Exception {
        layanan.ubahKategori(k);
    }

    public void hapus(int id) throws Exception {
        layanan.hapusKategori(id);
    }
}
