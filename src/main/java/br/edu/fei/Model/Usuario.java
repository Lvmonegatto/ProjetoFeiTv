/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.Model;

/**
 *
 * @author lucia
 */
public class Usuario {
    private int id_usuario;
    private String nome;
    private String cpf;
    private String usuario;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nome, String cpf, String usuario, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.usuario = usuario;
        this.senha = senha;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }         
}
