/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

import java.sql.Date;

/**
 *
 * @author lucia
 */
public class Filmes {
    private int idFilme;

    private String titulo;

    private String categoria;

    private String duracao;

    private int likes;

    private int deslikes;

    private String url;
    
    private String descricao;
    
    private String diretor;
    
    private int ano;
    
    private Date dataLancamento;
    
    private String imagem;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getAno() {
        return ano;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public String getImagem() {
        return imagem;
    }

    public Filmes(int idFilme, String titulo, String categoria, String duracao, int likes, int deslikes, String url, String descricao, String diretor, int ano, Date dataLancamento, String imagem) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.categoria = categoria;
        this.duracao = duracao;
        this.likes = likes;
        this.deslikes = deslikes;
        this.url = url;
        this.descricao = descricao;
        this.diretor = diretor;
        this.ano = ano;
        this.dataLancamento = dataLancamento;
        this.imagem = imagem;
    }

    public Filmes(int idFilme, String titulo, String categoria, String duracao, int likes, int deslikes, String url) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.categoria = categoria;
        this.duracao = duracao;
        this.likes = likes;
        this.deslikes = deslikes;
        this.url = url;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDuracao() {
        return duracao;
    }

    public int getLikes() {
        return likes;
    }

    public int getDeslikes() {
        return deslikes;
    }

    public String getUrl() {
        return url;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDeslikes(int deslikes) {
        this.deslikes = deslikes;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
