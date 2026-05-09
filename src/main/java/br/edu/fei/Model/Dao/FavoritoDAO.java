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
 *
 * @author lucia
 */
public class FavoritoDAO {
    private Connection conn;

    public FavoritoDAO(Connection conn) {

        this.conn = conn;
    }
    
    public void adicionarFavorito(Favorito favorito) throws SQLException {

    String sql = "INSERT INTO favoritos(id_usuario, id_filme)VALUES (?, ?)";

    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1,favorito.getIdUsuario());

    statement.setInt(2,favorito.getIdFilme());

    statement.execute();
}
     public boolean verificarFavorito(int idUsuario,int idFilme) throws SQLException {

        String sql = "SELECT *FROM favoritos WHERE id_usuario = ?AND id_filme = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, idUsuario);

        statement.setInt(2, idFilme);

        ResultSet rs = statement.executeQuery();

        return rs.next();
    }
}
