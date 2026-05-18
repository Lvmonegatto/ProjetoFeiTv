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
import java.awt.Image;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

    /**
    ** Controller responsável por gerenciar as funcionalidades
    * da tela principal do sistema GhibliFlix.
    * Esta classe controla:
    * - listagem e busca de filmes
    * - curtidas e descurtidas
    * - favoritos
    * - carregamento dos detalhes dos filmes
    * - navegação para a tela de favoritos
    * @author lucia
    */
public class TelaPrincipalController {
    private TelaPrincipal view;
    
    /**
    * Construtor da classe TelaPrincipalController.
    * @param view TelaPrincipal que será controlada.
    */
    public TelaPrincipalController(TelaPrincipal view) {
        this.view = view;
    }

    /**
    * Lista todos os filmes cadastrados no banco de dados
    * e preenche a JTable da tela principal.
    */
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
            view.getTabelaFilmes().getColumnModel().getColumn(2).setPreferredWidth(120);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
    * Busca filmes pelo nome digitado no campo de pesquisa
    * e atualiza a tabela com os resultados encontrados.
    */
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
    /**
    * Realiza a curtida de um filme selecionado. 
    * O usuário pode:
    * - curtir um filme
    * - remover a curtida
    * - trocar um deslike por like
     */
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
    /**
    * Atualiza a quantidade de likes e deslikes
    * dos filmes com base nas avaliações registradas
    * no banco de dados.
    */
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

        PreparedStatement statementLikes = conexao.getConnection()
                .prepareStatement(sqlLikes);

        PreparedStatement statementDeslikes = conexao.getConnection()
                .prepareStatement(sqlDeslikes);

        statementLikes.execute();

        statementDeslikes.execute();

        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    
    /**
    * Realiza a descurtida de um filme selecionado.
    * 
    * O usuário pode:
    * - descurtir um filme
    * - remover o deslike
     * - trocar um like por deslike
    */
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

            AvaliacaoFilmeDAO dao = 
                    new AvaliacaoFilmeDAO(conexao.getConnection());

            String avaliacaoAtual = dao.verificarAvaliacao(idUsuario,idFilme);

            if(avaliacaoAtual == null) {
                AvaliacaoFilme avaliacao = 
                        new AvaliacaoFilme(idUsuario,idFilme,"DESLIKE");

                dao.adicionarAvaliacao(avaliacao);

                JOptionPane.showMessageDialog(null,"Filme descurtido!");
            }
            else if(avaliacaoAtual.equals("DESLIKE")) {

                dao.removerAvaliacao(idUsuario,idFilme);

                JOptionPane.showMessageDialog(null,"Descurtida removida!");
            }
            else if(avaliacaoAtual.equals("LIKE")) {
                AvaliacaoFilme avaliacao = 
                        new AvaliacaoFilme(idUsuario,idFilme,"DESLIKE");
                dao.atualizarAvaliacao(avaliacao);

                JOptionPane.showMessageDialog(null,"Filme descurtido!");
            }
            atualizarLikesDeslikes();
            listarFilmes();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Carrega e exibe os detalhes completos do filme
    * selecionado na tabela. 
    * São exibidos:
    * - imagem
    * - título
    * - categoria
    * - diretor
    * - ano
    * - duração
    * - data de lançamento
    * - descrição
    */
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
                String nomeImagem = rs.getString("imagem");

                URL caminhoImagem =getClass().getResource("/imagens/" + nomeImagem);

                if(caminhoImagem != null) {

                ImageIcon icon = new ImageIcon(caminhoImagem);

                Image imagem =icon.getImage().getScaledInstance(160,220,Image.SCALE_SMOOTH);

                view.getImagem().setIcon(new ImageIcon(imagem));
                }
                
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
    /**
    * Adiciona um filme à lista de favoritos
    * do usuário logado.
     * O sistema impede duplicidade de favoritos.
    */
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
    /**
    * Abre a tela de favoritos do usuário
    * e fecha a tela principal.
    */
    public void abrirFavoritos() {

        TelaFavoritos tela = new TelaFavoritos();

        FavoritosController controller = new FavoritosController(tela);

        tela.setController(controller);

        tela.setVisible(true);

        view.dispose();
    }
}
