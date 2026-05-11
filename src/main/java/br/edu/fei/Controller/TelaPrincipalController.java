/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Controller;

import br.edu.fei.Model.AvaliacaoFilme;
import br.edu.fei.Model.Dao.AvaliacaoFilmeDAO;
import br.edu.fei.Model.Dao.Conexao;
import br.edu.fei.Model.Dao.FavoritoDAO;
import br.edu.fei.Model.Dao.FilmeDAO;
import br.edu.fei.Model.Favorito;
import br.edu.fei.Model.Sessao;
import br.edu.fei.View.TelaFavoritos;
import br.edu.fei.View.TelaPrincipal;
import java.sql.PreparedStatement;
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
            view.getTabelaFilmes().getColumnModel().getColumn(1).setPreferredWidth(220);
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
                view.getTabelaFilmes().getColumnModel().getColumn(1).setPreferredWidth(220);
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public void curtirFilme() {

        int linhaSelecionada = view.getTabelaFilmes().getSelectedRow();

        if(linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecione um filme!");
        return;
        }
        int idFilme = (int)view.getTabelaFilmes().getValueAt(linhaSelecionada,0);
        int idUsuario = Sessao.getIdUsuario();

        try {

            Conexao conexao = new Conexao();

            AvaliacaoFilmeDAO dao = new AvaliacaoFilmeDAO(conexao.getConnection());

            String avaliacaoAtual = dao.verificarAvaliacao(idUsuario,idFilme);

        if(avaliacaoAtual == null) {

            AvaliacaoFilme avaliacao = new AvaliacaoFilme(idUsuario,idFilme,"LIKE");

            dao.adicionarAvaliacao(avaliacao);

            JOptionPane.showMessageDialog(null,"Filme curtido!");
        }

        else if(avaliacaoAtual.equals("LIKE")) {
            dao.removerAvaliacao(idUsuario,idFilme);
            JOptionPane.showMessageDialog(null,"Curtida removida!");
        }

        else if(avaliacaoAtual.equals("DESLIKE")) {

            AvaliacaoFilme avaliacao = new AvaliacaoFilme(idUsuario,idFilme,"LIKE");
            dao.atualizarAvaliacao(avaliacao);
            JOptionPane.showMessageDialog(null,"Filme curtido!");
        }

        atualizarLikesDeslikes();

        listarFilmes();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    public void atualizarLikesDeslikes() {

        try {

         Conexao conexao = new Conexao();
        String sqlLikes =
                """
                UPDATE filmes
                SET likes = (
                    SELECT COUNT(*)
                    FROM avaliacao_filme
                    WHERE filmes.id_filme =
                          avaliacao_filme.id_filme
                    AND tipo = 'LIKE'
                )
                """;

        String sqlDeslikes =
                """
                UPDATE filmes
                SET deslikes = (
                    SELECT COUNT(*)
                    FROM avaliacao_filme
                    WHERE filmes.id_filme =
                          avaliacao_filme.id_filme
                    AND tipo = 'DESLIKE'
                )
                """;

        PreparedStatement statementLikes = conexao.getConnection().prepareStatement(sqlLikes);

        PreparedStatement statementDeslikes = conexao.getConnection().prepareStatement(sqlDeslikes);

        statementLikes.execute();

        statementDeslikes.execute();

    } catch (SQLException e) {

        e.printStackTrace();
    }
}
    public void descurtirFilme() {

        int linhaSelecionada = view.getTabelaFilmes().getSelectedRow();

        if(linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(null,"Selecione um filme!");
            return;
        }

        int idFilme = (int)view.getTabelaFilmes().getValueAt(linhaSelecionada,0);

        int idUsuario = Sessao.getIdUsuario();

        try {

            Conexao conexao = new Conexao();

            AvaliacaoFilmeDAO dao = new AvaliacaoFilmeDAO(conexao.getConnection());

            String avaliacaoAtual = dao.verificarAvaliacao(idUsuario,idFilme);

            if(avaliacaoAtual == null) {
                AvaliacaoFilme avaliacao = new AvaliacaoFilme(idUsuario,idFilme,"DESLIKE");

                dao.adicionarAvaliacao(avaliacao);

                JOptionPane.showMessageDialog(null,"Filme descurtido!");
            }
            else if(avaliacaoAtual.equals("DESLIKE")) {

                dao.removerAvaliacao(idUsuario,idFilme);

                JOptionPane.showMessageDialog(null,"Descurtida removida!");
            }
            else if(avaliacaoAtual.equals("LIKE")) {
                AvaliacaoFilme avaliacao = new AvaliacaoFilme(idUsuario,idFilme,"DESLIKE");
                dao.atualizarAvaliacao(avaliacao);

                JOptionPane.showMessageDialog(null,"Filme descurtido!");
            }
            atualizarLikesDeslikes();
            listarFilmes();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void carregarDetalhesFilme() {

        int linhaSelecionada = view.getTabelaFilmes().getSelectedRow();

        if(linhaSelecionada == -1) {

            return;
        }

        int idFilme = (int)
            view.getTabelaFilmes().getValueAt(linhaSelecionada,0);

        try {
            Conexao conexao = new Conexao();

            FilmeDAO dao =
                new FilmeDAO(conexao.getConnection());

            ResultSet rs = dao.buscarDetalhesFilme(idFilme);

            if(rs.next()) {

                view.getTituloValor().setText(rs.getString("titulo"));

                view.getCategoriaValor().setText(rs.getString("categoria"));

                view.getDiretorValor().setText(rs.getString("diretor"));

                view.getAnoValor().setText(String.valueOf(rs.getInt("ano")));

                view.getDuracaoValor().setText(rs.getString("duracao"));

                view.getDataValor().setText(String.valueOf(rs.getDate("data_lancamento")));

                view.getTxtDescricao().setText(rs.getString("descricao"));
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
        public void favoritarFilme() {

            int linhaSelecionada = view.getTabelaFilmes().getSelectedRow();

            if(linhaSelecionada == -1) {

                JOptionPane.showMessageDialog(null,"Selecione um filme!");

            return;
            }

            int idFilme = (int)view.getTabelaFilmes().getValueAt(linhaSelecionada,0);

            int idUsuario = Sessao.getIdUsuario();

            Favorito favorito = new Favorito(idUsuario,idFilme);

        try {
            Conexao conexao = new Conexao();

            FavoritoDAO dao =new FavoritoDAO(conexao.getConnection());
            if(dao.verificarFavorito(idUsuario,idFilme)) {
                JOptionPane.showMessageDialog(null,"Esse filme já foi favoritado!");
                return;
            }

            dao.adicionarFavorito(favorito);

            JOptionPane.showMessageDialog(null,"Filme favoritado!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void abrirFavoritos() {

        TelaFavoritos tela = new TelaFavoritos();

        FavoritosController controller = new FavoritosController(tela);

        tela.setController(controller);

        tela.setVisible(true);
}
}
