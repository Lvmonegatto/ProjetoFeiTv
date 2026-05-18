/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

/**
* Classe responsável por representar
* a avaliação de um filme no sistema.
* 
* A avaliação pode ser:
* - LIKE
* - DESLIKE
* @author lucia
*/
public class AvaliacaoFilme {
    private int idUsuario;

    private int idFilme;

    private String tipo;
    
    /**
     * Construtor da classe AvaliacaoFilme.
     * @param idUsuario
     * @param idFilme
     * @param tipo 
     */
    public AvaliacaoFilme(int idUsuario,int idFilme,String tipo) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
        this.tipo = tipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public String getTipo() {
        return tipo;
    }
}
