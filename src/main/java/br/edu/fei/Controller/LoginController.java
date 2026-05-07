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
import br.edu.fei.View.TelaPrincipal;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author lucia
 */
public class LoginController {

    private Login view;
    private Cadastro cadastroView;

    public LoginController(Login view, Cadastro cadastroView) {
        this.view = view;
        this.cadastroView = cadastroView;
    }

    public void abrirCadastro() {

        view.setVisible(false);
        cadastroView.setVisible(true);
    }

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