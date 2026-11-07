/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mycompany.service;

/**
 *
 * @author FARHAN
 */
public interface LayananPengeluaranInterface {

    void tambahPengeluaran(Pengeluaran p) throws Exception;

    List<Pengeluaran> tampilkanPengeluaran() throws Exception;

    void ubahPengeluaran(Pengeluaran p) throws Exception;

    void hapusPengeluaran(int id) throws Exception;

    PengeluaranInterface.Ringkasan ambilRingkasan() throws Exception;
}
