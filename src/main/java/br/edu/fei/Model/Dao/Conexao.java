/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucia
 */
public class Conexao {
    public Connection getConnection() throws SQLException{
        Dotenv dotenv = Dotenv.load();
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usuario", "postgres", dotenv.get("SENHA"));
        System.out.println("Sistema funcionando");
        return conexao;
    }
}
