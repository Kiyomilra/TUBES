/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.model;

import java.math.BigDecimal;

import java.time.LocalDate;

/**
 *
 * @author FARHAN
 */
public class Pengeluaran {

    private int id, idKategori;
    private LocalDate tanggal;
    private String deskripsi, namaKategori, metodePembayaran;
    private BigDecimal jumlah;

    public Pengeluaran(int id, LocalDate tanggal, String deskripsi, BigDecimal jumlah, int idKategori, String namaKategori, String metodePembayaran) {
        this.id = id;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.idKategori = idKategori;
        this.namaKategori = namaKategori;
        this.metodePembayaran = metodePembayaran;
    }

    public int getId() {
        return id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }
}
