/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.fei.projetofeitv;

import br.edu.fei.Controller.CadastroController;
import br.edu.fei.Controller.LoginController;
import br.edu.fei.View.Cadastro;
import br.edu.fei.View.Login;

/**
 *
 * @author lucia
 */
public class ProjetoFeiTv {

    public static void main(String[] args) {
        Login login = new Login();
        Cadastro cadastro = new Cadastro();

        LoginController loginController =
                new LoginController(login, cadastro);

        CadastroController cadastroController =
                new CadastroController(cadastro, login);

        login.setController(loginController);
        cadastro.setController(cadastroController);

        login.setVisible(true);
    }
}
