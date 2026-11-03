/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.model;

/**
 *
 * @author FARHAN
 */
public class Kategori {

    private int id;
    private String namaKategori, deskripsi;

    public Kategori(int id, String namaKategori, String deskripsi) {
        this.id = id;
        this.namaKategori = namaKategori;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaKategori(String n) {
        namaKategori = n;
    }

    public void setDeskripsi(String d) {
        deskripsi = d;
    }

    @Override
    public String toString() {
        return namaKategori;
    }
}
