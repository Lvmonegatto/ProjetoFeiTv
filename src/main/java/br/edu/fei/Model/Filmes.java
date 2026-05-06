/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

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
