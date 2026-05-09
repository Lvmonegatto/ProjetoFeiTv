/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

/**
 *
 * @author lucia
 */
public class Sessao {
    private static int idUsuario;

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(
            int idUsuario
    ) {

        Sessao.idUsuario = idUsuario;
    }
}
