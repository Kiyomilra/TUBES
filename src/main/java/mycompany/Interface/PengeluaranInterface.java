/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mycompany.Interface;

import mycompany.model.Pengeluaran;
import java.util.List;
import java.math.BigDecimal;

/**
 *
 * @author FARHAN
 */
public interface PengeluaranInterface {

    void simpan(Pengeluaran p) throws Exception;

    List<Pengeluaran> ambilSemua() throws Exception;

    void ubah(Pengeluaran p) throws Exception;

    void hapus(int id) throws Exception;

    Ringkasan ambilRingkasan() throws Exception;

    class Ringkasan {

        private final BigDecimal total;
        private final int jumlah;

        public Ringkasan(BigDecimal t, int j) {
            total = t;
            jumlah = j;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public int getJumlah() {
            return jumlah;
        }
    }
}
