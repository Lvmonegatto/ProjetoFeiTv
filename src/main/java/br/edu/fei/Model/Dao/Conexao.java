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
 * Classe responsável por realizar
 * a conexão do sistema com o banco
 * de dados PostgreSQL.
 * @author lucia
 */
public class Conexao {
    /**
     * Cria e retorna uma conexão ativa
     * com o banco de dados.
     * @return
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException{
        Dotenv dotenv = Dotenv.load();
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/usuario", "postgres", dotenv.get("SENHA"));
        System.out.println("Sistema funcionando");
        return conexao;
    }
}
