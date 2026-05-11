/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.ListaReproducaoDAO;
import br.edu.fei.Model.Sessao;
import br.edu.fei.View.TelaFavoritos;
import br.edu.fei.View.TelaListaReproducao;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class ListaReproducaoController {
     private TelaListaReproducao view;

    public ListaReproducaoController(TelaListaReproducao view) {
        this.view = view;
    }

    public void listarFilmesLista() {

        try {

            Conexao conexao = new Conexao();

            ListaReproducaoDAO dao = new ListaReproducaoDAO(conexao.getConnection());

            ResultSet rs = dao.listarLista(Sessao.getIdUsuario());

            DefaultTableModel tabela =(DefaultTableModel)view.getTabelaLista().getModel();

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
    public void voltarFavoritos() {

        TelaFavoritos tela = new TelaFavoritos();

        FavoritosController controller = new FavoritosController(tela);

        tela.setController(controller);

        tela.setVisible(true);

        view.dispose();
    }
    public void removerDaLista() {

        int linhaSelecionada = view.getTabelaLista().getSelectedRow();

        if(linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecione um filme!");
            return;
        }

        int idFilme = (int)view.getTabelaLista().getValueAt(linhaSelecionada,0);

        try {

            Conexao conexao = new Conexao();

            ListaReproducaoDAO dao = new ListaReproducaoDAO(conexao.getConnection());

            dao.removerFilmeLista(Sessao.getIdUsuario(),idFilme);

            JOptionPane.showMessageDialog(null,"Filme removido da lista!");

            listarFilmesLista();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
