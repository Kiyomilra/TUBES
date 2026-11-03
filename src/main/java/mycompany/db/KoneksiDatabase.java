/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.db;

import java.sql.*;

/**
 *
 * @author FARHAN
 */
public class KoneksiDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/pengeluaranadpl";
    private static final String USER = "root";
    private static final String PASS = "Kiyomilara10";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
