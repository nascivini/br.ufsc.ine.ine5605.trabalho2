/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Funcionario;

/**
 *
 * @author ClipEscolaEstagiario
 */
public class Pessoa {
    public String cpf;
    public String nome;
    public String nascimento;
    public long telefone;

    public Pessoa(String cpf, String nome, String nascimento, long telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
    }
    
    
}
