/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.UsuarioDAO;
import br.edu.fei.Model.Usuario;
import br.edu.fei.View.Cadastro;
import br.edu.fei.View.Login;

import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author lucia
 */
public class CadastroController {

    private Cadastro view;
    private Login loginView;

    public CadastroController(Cadastro view, Login loginView) {
        this.view = view;
        this.loginView = loginView;
    }

    public void cadastrar() {

    String nome = view.getTfNome().getText();
    String cpf = view.getTfCpf().getText();
    String usuario = view.getTfUsuario().getText();
    String senha = view.getTfSenha().getText();

    if(nome.isEmpty() ||
       cpf.isEmpty() ||
       usuario.isEmpty() ||
       senha.isEmpty()) {

        JOptionPane.showMessageDialog(null,"Preencha todos os campos!");

        return;
    }

    if(cpf.length() != 14) {

        JOptionPane.showMessageDialog(null,"CPF deve possuir 14 caracteres!");
        return;
    }

    Usuario usuarioObj =
            new Usuario(nome, cpf, usuario, senha);

    try {

        Conexao conexao = new Conexao();

        UsuarioDAO dao =
                new UsuarioDAO(conexao.getConnection());

        dao.inserir(usuarioObj);

        JOptionPane.showMessageDialog(null,"Cadastro realizado com sucesso!");

        view.dispose();

        loginView.setVisible(true);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(null,"Erro ao cadastrar usuário!");

        e.printStackTrace();
    }
}
}
