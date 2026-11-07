/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.db;

/**
 *
 * @author FARHAN
 */
import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    Connection getConnection() throws SQLException;
}
