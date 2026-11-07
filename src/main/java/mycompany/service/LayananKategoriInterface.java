/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mycompany.service;

/**
 *
 * @author FARHAN
 */
import mycompany.model.Kategori;
import java.util.List;

public interface LayananKategoriInterface {

    void tambahKategori(Kategori kategori) throws Exception;

    List<Kategori> tampilkanKategori() throws Exception;

    void ubahKategori(Kategori kategori) throws Exception;

    void hapusKategori(int id) throws Exception;
}
