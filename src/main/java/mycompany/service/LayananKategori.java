/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.service;

import mycompany.model.Kategori;
import mycompany.Interface.KategoriInterface;

/**
 *
 * @author FARHAN
 */
import java.util.List;

public class LayananKategori {

    private final KategoriInterface repo;

    public LayananKategori(KategoriInterface r) {
        repo = r;
    }

    public void tambahKategori(Kategori k) throws Exception {
        validasi(k);
        repo.simpan(k);
    }

    public List<Kategori> tampilkanKategori() throws Exception {
        return repo.ambilSemua();
    }

    public void ubahKategori(Kategori k) throws Exception {
        if (k.getId() <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        validasi(k);
        repo.ubah(k);
    }

    public void hapusKategori(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        repo.hapus(id);
    }

    private void validasi(Kategori k) {
        if (k == null || k.getNamaKategori() == null || k.getNamaKategori().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama kategori wajib diisi");
        }
    }
}
