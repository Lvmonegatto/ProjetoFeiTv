/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import br.edu.fei.Model.AvaliacaoFilme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável pelas operações de acesso à tabela avaliacao_filme.
 * @author lucia
 */
public class AvaliacaoFilmeDAO {
     private Connection conn;
    /**
     * Construtor da classe AvaliacaoFilmeDAO.
     * @param conn 
     */
    public AvaliacaoFilmeDAO(Connection conn) {
        this.conn = conn;
    }
    /**
     * Verifica se o usuário já realizou uma avaliação em determinado filme.
     * @param idUsuario
     * @param idFilme
     * @return
     * @throws SQLException 
     */
    public String verificarAvaliacao(int idUsuario,int idFilme) throws SQLException {
        String sql = "SELECT tipo FROM avaliacao_filme WHERE id_usuario = ? AND id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        ResultSet rs = statement.executeQuery();

        if(rs.next()) {
            return rs.getString("tipo");
        }

        return null;
    }
    /**
     * Adiciona uma nova avaliação de filme no banco de dados.
     * @param avaliacao
     * @throws SQLException 
     */
    public void adicionarAvaliacao(AvaliacaoFilme avaliacao) throws SQLException {
        String sql = "INSERT INTO avaliacao_filme(id_usuario, id_filme, tipo)VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1,avaliacao.getIdUsuario());

        statement.setInt(2,avaliacao.getIdFilme());

        statement.setString(3,avaliacao.getTipo());

        statement.execute();
    }
    /**
     * Atualiza a avaliação já existente de um usuário em um filme.
     * @param avaliacao
     * @throws SQLException 
     */
    public void atualizarAvaliacao(AvaliacaoFilme avaliacao) throws SQLException {
        String sql = "UPDATE avaliacao_filme SET tipo = ? WHERE id_usuario = ? AND id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1,avaliacao.getTipo());

        statement.setInt(2,avaliacao.getIdUsuario());

        statement.setInt(3,avaliacao.getIdFilme());

        statement.execute();
    }
    /**
     * Remove a avaliação de um filme realizada pelo usuário.
     * @param idUsuario
     * @param idFilme
     * @throws SQLException 
     */
    public void removerAvaliacao(int idUsuario,int idFilme) throws SQLException {
        String sql = "DELETE FROM avaliacao_filme WHERE id_usuario = ? AND id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        statement.execute();
    }
}
