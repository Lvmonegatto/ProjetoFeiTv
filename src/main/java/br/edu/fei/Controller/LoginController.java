/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.UsuarioDAO;
import br.edu.fei.Model.Sessao;
import br.edu.fei.Model.Usuario;
import br.edu.fei.View.Cadastro;
import br.edu.fei.View.Login;
import br.edu.fei.View.TelaPrincipal;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Controller responsável por gerenciar
 * o sistema de login do GhibliFlix.
 * Esta classe controla:
 * - autenticação de usuários
 * - abertura da tela de cadastro
 * - inicialização da sessão do usuário
 * - abertura da tela principal do sistema
 * @author lucia
 */
public class LoginController {

    private Login view;
    private Cadastro cadastroView;
    
    /**
    * Construtor da classe LoginController.
    * 
    * @param view Tela de login gerenciada pelo controller.
    * @param cadastroView Tela de cadastro utilizada para navegação.
    */
    public LoginController(Login view, Cadastro cadastroView) {
        this.view = view;
        this.cadastroView = cadastroView;
    }
    
    /**
    * Abre a tela de cadastro
    * e oculta a tela de login.
    */
    public void abrirCadastro() {

        view.setVisible(false);
        cadastroView.setVisible(true);
    }
    /**
    * Realiza a autenticação do usuário no sistema.
    * O método:
    * - captura usuário e senha digitados
    * - consulta o banco de dados
    * - inicia a sessão do usuário logado
    * - abre a tela principal do sistema
    */
    public void login() {

        String usuario = view.getTfUsuario().getText();
        String senha = view.getTfSenha().getText();

        Usuario usuarioObj =
                new Usuario(null, null, usuario, senha);

        try {

            Conexao conexao = new Conexao();

            UsuarioDAO dao =
                    new UsuarioDAO(conexao.getConnection());

            ResultSet rs = dao.consultar(usuarioObj);

            if (rs.next()) {
                Sessao.setIdUsuario(rs.getInt("id_usuario"));
                
                TelaPrincipal telaPrincipal = new TelaPrincipal();

                TelaPrincipalController controller = new TelaPrincipalController(telaPrincipal);

                telaPrincipal.setController(controller);

                telaPrincipal.setVisible(true);

                view.dispose();
            } else {

                System.out.println("Usuário ou senha inválidos");
            }

        } catch (SQLException e) {

            System.out.println("Erro no login");
            e.printStackTrace();
        }
    }
}