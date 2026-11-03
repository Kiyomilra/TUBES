/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mycompany.Interface;

import java.util.List;
import mycompany.model.Kategori;

/**
 *
 * @author FARHAN
 */
public interface KategoriInterface {

    void simpan(Kategori k) throws Exception;

    List<Kategori> ambilSemua() throws Exception;

    void ubah(Kategori k) throws Exception;

    void hapus(int id) throws Exception;
}
