/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.FilmeDAO;
import br.edu.fei.View.TelaPrincipal;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucia
 */
public class TelaPrincipalController {
    private TelaPrincipal view;

    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
    }

    public void listarFilmes() {
        
        try {
            Conexao conexao = new Conexao();
            FilmeDAO dao = new FilmeDAO(conexao.getConnection());
            ResultSet rs = dao.listarFilmes();
            DefaultTableModel tabela = (DefaultTableModel)view.getTabelaFilmes().getModel();
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
    public void buscarFilme() {

    String nome = view.getTfBuscar().getText();

        try {

            Conexao conexao = new Conexao();

            FilmeDAO dao = new FilmeDAO(conexao.getConnection());

            ResultSet rs = dao.buscarFilme(nome);

            DefaultTableModel tabela = new DefaultTableModel();

            tabela.addColumn("ID");
            tabela.addColumn("Título");
            tabela.addColumn("Categoria");
            tabela.addColumn("Duração");
            tabela.addColumn("Likes");
            tabela.addColumn("Deslikes");

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

            view.getTabelaFilmes().setModel(tabela);

        } catch (SQLException e) {

            e.printStackTrace();
        }
}
    public void curtirFilme() {

    int linhaSelecionada = view.getTabelaFilmes().getSelectedRow();

    if(linhaSelecionada == -1) {

         JOptionPane.showMessageDialog(null,"Selecione um filme!");
    }

    int idFilme = (int)view.getTabelaFilmes().getValueAt(linhaSelecionada,0);

        try {
            Conexao conexao = new Conexao();

            FilmeDAO dao = new FilmeDAO(conexao.getConnection());
            dao.curtir(idFilme);
            listarFilmes();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
