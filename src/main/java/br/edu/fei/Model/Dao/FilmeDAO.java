/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

/**
 *
 * @author lucia
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmeDAO {

    private Connection conn;

    public FilmeDAO(Connection conn) {

        this.conn = conn;
    }

    public ResultSet listarFilmes()throws SQLException {
        String sql = "SELECT * FROM filmes ORDER BY id_filme";

        PreparedStatement statement = conn.prepareStatement(sql);

        return statement.executeQuery();
    }

    public ResultSet buscarFilme(String nome)throws SQLException {
        String sql = "SELECT * FROM filmes WHERE titulo ILIKE ? ";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, "%" + nome + "%");

        return statement.executeQuery();
    }

    public void curtir(int idFilme)throws SQLException {

        String sql = "UPDATE filmes SET likes = likes + 1 WHERE id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idFilme);

        statement.execute();
    }

    public void descurtir(int idFilme)throws SQLException {

        String sql ="UPDATE filmes SET deslikes = deslikes + 1 WHERE id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idFilme);

        statement.execute();
    }
}