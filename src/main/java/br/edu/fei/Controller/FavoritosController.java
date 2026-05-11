/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.FavoritoDAO;
import br.edu.fei.Model.Sessao;
import br.edu.fei.View.TelaFavoritos;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class FavoritosController {
    private TelaFavoritos view;

    public FavoritosController(TelaFavoritos view) {
        this.view = view;
    }

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
}
