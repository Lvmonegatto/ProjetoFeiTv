/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.edu.fei.projetofeitv;

import br.edu.fei.Controller.CadastroController;
import br.edu.fei.Controller.LoginController;
import br.edu.fei.Controller.TelaPrincipalController;
import br.edu.fei.View.Cadastro;
import br.edu.fei.View.Login;
import br.edu.fei.View.TelaPrincipal;

/**
 * Classe principal responsável por inicializar o sistema FEItv.
 * 
 * Esta classe:
 * - cria as telas do sistema
 * - instancia os controllers
 * - realiza a ligação entre View e Controller
 * - inicia a aplicação pela tela de login
 * @author lucia
 */
public class ProjetoFeiTv {
    /**
     * Método principal responsável por iniciar a execução do sistema.
     * @param args 
     */
    public static void main(String[] args) {
        Login login = new Login();
        Cadastro cadastro = new Cadastro();

        LoginController loginController = new LoginController(login, cadastro);

        CadastroController cadastroController = new CadastroController(cadastro, login);

        login.setController(loginController);
        cadastro.setController(cadastroController);

        login.setVisible(true);
        
    }
}
