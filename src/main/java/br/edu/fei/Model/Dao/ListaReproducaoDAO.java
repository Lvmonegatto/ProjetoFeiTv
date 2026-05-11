/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import br.edu.fei.Model.ListaReproducao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucia
 */
public class ListaReproducaoDAO {
     private Connection conn;

    public ListaReproducaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void adicionarFilme(ListaReproducao lista) throws SQLException {
        String sql =
                """
                INSERT INTO lista_reproducao
                (id_usuario, id_filme)
                VALUES (?, ?)
                """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1,lista.getIdUsuario());

        statement.setInt(2,lista.getIdFilme());

        statement.execute();
    }

    public boolean verificarFilmeLista(int idUsuario,int idFilme) throws SQLException {

        String sql =
                """
                SELECT *
                FROM lista_reproducao
                WHERE id_usuario = ?
                AND id_filme = ?
                """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        ResultSet rs = statement.executeQuery();

        return rs.next();
    }
    public ResultSet listarLista(int idUsuario) throws SQLException {

        String sql =
            """
            SELECT filmes.*
            FROM lista_reproducao
            INNER JOIN filmes
            ON lista_reproducao.id_filme =
               filmes.id_filme
            WHERE lista_reproducao.id_usuario = ?
            """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        return statement.executeQuery();
    }
    public void removerFilmeLista(int idUsuario,int idFilme) throws SQLException {

        String sql =
                """
                DELETE FROM lista_reproducao
                WHERE id_usuario = ?
                AND id_filme = ?
                """;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        statement.execute();
    }
}
