/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model.Dao;

import br.edu.fei.Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável pelas operações de acesso à tabela usuarios.
 * @author lucia
 */
public class UsuarioDAO {
    private Connection conn;
    /**
     * Construtor da classe UsuarioDAO.
     * @param conn 
     */
    public UsuarioDAO(Connection conn){
        this.conn = conn;
    }
    /**
     * Insere um novo usuário no banco de dados.
     * @param usuario
     * @throws SQLException 
     */
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nome, cpf, usuario, senha) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getCpf());
        statement.setString(3, usuario.getUsuario());
        statement.setString(4, usuario.getSenha());

        statement.execute();

        System.out.println("Informacao inserida com sucesso!");

        conn.close(); 
    }
    /**
     * Consulta um usuário no banco para realizar autenticação de login.
     * @param usuario
     * @return
     * @throws SQLException 
     */
    public ResultSet consultar(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, usuario.getUsuario());
        statement.setString(2, usuario.getSenha());

        return statement.executeQuery(); 
    }
}
