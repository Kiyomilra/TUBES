/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.service;

import mycompany.model.Pengeluaran;
import mycompany.Interface.PengeluaranInterface;
import mycompany.service.LayananPengeluaranInterface;

/**
 *
 * @author FARHAN
 */
import java.util.List;

public class LayananPengeluaran implements LayananPengeluaranInterface {

    private final PengeluaranInterface repo;

    public LayananPengeluaran(PengeluaranInterface r) {
        repo = r;
    }

    public void tambahPengeluaran(Pengeluaran p) throws Exception {
        validasi(p);
        repo.simpan(p);
    }

    public List<Pengeluaran> tampilkanPengeluaran() throws Exception {
        return repo.ambilSemua();
    }

    public void ubahPengeluaran(Pengeluaran p) throws Exception {
        if (p.getId() <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        validasi(p);
        repo.ubah(p);
    }

    public void hapusPengeluaran(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("ID tidak valid");
        }
        repo.hapus(id);
    }

    public PengeluaranInterface.Ringkasan ambilRingkasan() throws Exception {
        return repo.ambilRingkasan();
    }

    private void validasi(Pengeluaran p) {
        if (p == null || p.getTanggal() == null) {
            throw new IllegalArgumentException("Tanggal wajib diisi");
        }
        if (p.getDeskripsi() == null || p.getDeskripsi().trim().isEmpty()) {
            throw new IllegalArgumentException("Deskripsi wajib diisi");
        }
        if (p.getJumlah() == null || p.getJumlah().signum() <= 0) {
            throw new IllegalArgumentException("Jumlah harus lebih dari 0");
        }
        if (p.getIdKategori() <= 0) {
            throw new IllegalArgumentException("Kategori wajib dipilih");
        }
    }
}
