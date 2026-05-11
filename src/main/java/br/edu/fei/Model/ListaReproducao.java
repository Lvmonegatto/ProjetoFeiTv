/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

/**
 *
 * @author lucia
 */
public class ListaReproducao {
    private int idUsuario;

    private int idFilme;

    public ListaReproducao(
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
