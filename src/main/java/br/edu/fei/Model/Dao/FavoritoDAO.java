 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import java.sql.Connection;
import br.edu.fei.Model.Favorito;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável pelas operações de acesso à tabela favoritos.
 * @author lucia
 */
public class FavoritoDAO {
    private Connection conn;
    
    /**
     * Construtor da classe FavoritoDAO.
     * @param conn 
     */
    public FavoritoDAO(Connection conn) {
        this.conn = conn;
    }
    /**
     * Adiciona um filme aos favoritos do usuário.
     * @param favorito
     * @throws SQLException 
     */
    public void adicionarFavorito(Favorito favorito) throws SQLException {

        String sql = "INSERT INTO favoritos(id_usuario, id_filme)VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1,favorito.getIdUsuario());

        statement.setInt(2,favorito.getIdFilme());

        statement.execute();
    }
    /**
     * Verifica se um filme já foi favoritado pelo usuário.
     * @param idUsuario
     * @param idFilme
     * @return
     * @throws SQLException 
     */
     public boolean verificarFavorito(int idUsuario,int idFilme) throws SQLException {

        String sql = "SELECT *FROM favoritos WHERE id_usuario = ? AND id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        ResultSet rs = statement.executeQuery();

        return rs.next();
    }
     /**
      * Lista todos os filmes favoritados pelo usuário logado.
      * @param idUsuario
      * @return
      * @throws SQLException 
      */
    public ResultSet listarFavoritos(int idUsuario) throws SQLException {

        String sql =
            """
            SELECT filmes.*
            FROM favoritos
            INNER JOIN filmes
            ON favoritos.id_filme =
               filmes.id_filme
            WHERE favoritos.id_usuario = ?
            """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        return statement.executeQuery();
    }
    /**
     * Remove um filme da lista de favoritos do usuário.
     * @param idUsuario
     * @param idFilme
     * @throws SQLException 
     */
    public void removerFavorito(int idUsuario,int idFilme) throws SQLException {

        String sql =
            """
            DELETE FROM favoritos
            WHERE id_usuario = ?
            AND id_filme = ?
            """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        statement.execute();
    }
}
