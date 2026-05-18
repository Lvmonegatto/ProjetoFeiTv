/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

/**
 * Classe responsável por representar
 * um filme favoritado pelo usuário.
 * @author lucia
 */
public class Favorito {
    private int idFavorito;

    private int idUsuario;

    private int idFilme;
    /**
     * Construtor da classe Favorito.
     * @param idUsuario
     * @param idFilme 
     */
    public Favorito(
            int idUsuario,
            int idFilme
    ) {

        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdFilme() {
        return idFilme;
    }
}

