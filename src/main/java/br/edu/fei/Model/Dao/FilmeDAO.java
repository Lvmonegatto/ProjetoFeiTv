/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe responsável pelas operações de acesso à tabela filmes.
 * @author lucia
 */
public class FilmeDAO {

    private Connection conn;
    /**
     * Construtor da classe FilmeDAO.
     * @param conn 
     */
    public FilmeDAO(Connection conn) {

        this.conn = conn;
    }
    /**
     * Lista todos os filmes cadastrados no banco de dados.
     * @return
     * @throws SQLException 
     */
    public ResultSet listarFilmes()throws SQLException {
        String sql = "SELECT * FROM filmes ORDER BY id_filme";

        PreparedStatement statement = conn.prepareStatement(sql);

        return statement.executeQuery();
    }
    /**
     * Busca filmes pelo título.
     * @param nome
     * @return
     * @throws SQLException 
     */
    public ResultSet buscarFilme(String nome)throws SQLException {
        String sql = "SELECT * FROM filmes WHERE titulo ILIKE ? ";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, "%" + nome + "%");

        return statement.executeQuery();
    }
    /**
     * Incrementa a quantidade de likes de um filme.
     * @param idFilme
     * @throws SQLException 
     */
    public void curtir(int idFilme)throws SQLException {

        String sql = "UPDATE filmes SET likes = likes + 1 WHERE id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idFilme);

        statement.execute();
    }
    /**
     * Incrementa a quantidade de deslikes de um filme.
     * @param idFilme
     * @throws SQLException 
     */
    public void descurtir(int idFilme)throws SQLException {

        String sql ="UPDATE filmes SET deslikes = deslikes + 1 WHERE id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idFilme);

        statement.execute();
    }
    /**
     * Busca os detalhes completos de um filme específico.
     * @param idFilme
     * @return
     * @throws SQLException 
     */
    public ResultSet buscarDetalhesFilme(int idFilme) throws SQLException {

        String sql = "SELECT * FROM filmes WHERE id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idFilme);

        return statement.executeQuery();
    }   
}