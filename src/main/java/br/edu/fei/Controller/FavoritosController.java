/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.FavoritoDAO;
import br.edu.fei.Model.Dao.ListaReproducaoDAO;
import br.edu.fei.Model.ListaReproducao;
import br.edu.fei.Model.Sessao;
import br.edu.fei.View.TelaFavoritos;
import br.edu.fei.View.TelaListaReproducao;
import br.edu.fei.View.TelaPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Controller responsável por gerenciar
 * os filmes favoritos do usuário.
 * 
 * Esta classe controla:
 * - listagem dos favoritos
 * - remoção de favoritos
 * - adição de filmes na lista de reprodução
 * - navegação entre telas
 * @author lucia
 */
public class FavoritosController {
    private TelaFavoritos view;

    /**
    * Construtor da classe FavoritosController.
    * 
    * @param view Tela de favoritos controlada pelo controller.
    */
    public FavoritosController(TelaFavoritos view) {
        this.view = view;
    }
    /**
    * Lista todos os filmes favoritados
    * pelo usuário logado e preenche
    * a JTable da tela de favoritos.
    */
    public void listarFavoritos() {

        try {

            Conexao conexao = new Conexao();

            FavoritoDAO dao = new FavoritoDAO(conexao.getConnection());

            ResultSet rs = dao.listarFavoritos(Sessao.getIdUsuario());

            DefaultTableModel tabela =(DefaultTableModel)view.getTabelaFavoritos()
                    .getModel();

            tabela.setRowCount(0);

            while(rs.next()) {

                Object[] linha = {

                    rs.getInt("id_filme"),
                    rs.getString("titulo"),
                    rs.getString("categoria"),
                    rs.getString("duracao"),
                    rs.getInt("likes"),
                    rs.getInt("deslikes")
                };

                tabela.addRow(linha);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    /**
    * Remove um filme selecionado
    * da lista de favoritos do usuário.
    * Após a remoção, a tabela é atualizada.
    */
    public void removerFavorito() {

        int linhaSelecionada =view.getTabelaFavoritos().getSelectedRow();

        if(linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecione um filme!");
            return;
        }

        int idFilme = (int)view.getTabelaFavoritos().getValueAt(linhaSelecionada,0);

        try {

            Conexao conexao = new Conexao();

            FavoritoDAO dao = new FavoritoDAO(conexao.getConnection());

            dao.removerFavorito(Sessao.getIdUsuario(),idFilme);

            JOptionPane.showMessageDialog(null,"Favorito removido!");

            listarFavoritos();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Retorna para a tela principal
    * do sistema e fecha a tela de favoritos.
    */
    public void voltarTelaPrincipal() {

        TelaPrincipal tela = new TelaPrincipal();

        TelaPrincipalController controller = new TelaPrincipalController(tela);

        tela.setController(controller);

        tela.setVisible(true);

        view.dispose();
    }
    /**
    * Adiciona um filme favorito
    * à lista de reprodução do usuário.
    * O sistema impede duplicidade
    * de filmes na lista.
    */
    public void adicionarListaReproducao() {

        int linhaSelecionada = view.getTabelaFavoritos().getSelectedRow();

        if(linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecione um filme!");
            return;
        }

        int idFilme = (int)view.getTabelaFavoritos().getValueAt(linhaSelecionada,0);

        int idUsuario = Sessao.getIdUsuario();

        try {

            Conexao conexao = new Conexao();

            ListaReproducaoDAO dao = new ListaReproducaoDAO(conexao.getConnection());

            if(dao.verificarFilmeLista(idUsuario,idFilme)) {
                JOptionPane.showMessageDialog(null,"Esse filme já está na lista!");
                return;
            }

            ListaReproducao lista = new ListaReproducao(idUsuario,idFilme);

            dao.adicionarFilme(lista);

            JOptionPane.showMessageDialog(null,"Filme adicionado à lista!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Abre a tela da lista de reprodução
    * do usuário e fecha a tela de favoritos.
    */
    public void abrirListaReproducao() {

        TelaListaReproducao tela = new TelaListaReproducao();

        ListaReproducaoController controller = new ListaReproducaoController(tela);

        tela.setController(controller);

        tela.setVisible(true);

        view.dispose();
    }
}
