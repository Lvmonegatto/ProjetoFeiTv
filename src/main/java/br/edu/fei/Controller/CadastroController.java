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
 * Controller responsável por gerenciar o cadastro de novos usuários no sistema.
 * 
 * Esta classe controla:
 * - validação dos campos de cadastro
 * - criação de usuários
 * - persistência no banco de dados
 * - navegação entre cadastro e login
 * @author lucia
 */
public class CadastroController {

    private Cadastro view;
    private Login loginView;

    /**
    * Construtor da classe CadastroController.
    * 
    * @param view Tela de cadastro controlada pelo controller.
    * @param loginView Tela de login utilizada para navegação.
    */
    public CadastroController(Cadastro view, Login loginView) {
        this.view = view;
        this.loginView = loginView;
    }
    
    /**
    * Realiza o cadastro de um novo usuário.
    * 
    * O método:
    * - valida os campos obrigatórios
    * - valida o tamanho do CPF
    * - cria o objeto usuário
    * - salva os dados no banco de dados
    * - retorna para a tela de login após o cadastro
    */
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
    /**
    * Retorna para a tela de login
    * e oculta a tela de cadastro.
    */
    public void voltarLogin() {

        view.setVisible(false);
        loginView.setVisible(true);
    }
}
