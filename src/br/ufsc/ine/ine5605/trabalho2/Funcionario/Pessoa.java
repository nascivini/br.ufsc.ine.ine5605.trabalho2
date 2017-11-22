/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import java.io.Serializable;

/**
 *
 * @author ClipEscolaEstagiario
 */
public class Pessoa implements Serializable{
    public long cpf;
    public String nome;
    public String nascimento;
    public long telefone;

    public Pessoa(DadosFuncionario dados) {
        this.cpf = dados.cpf;
        this.nome = dados.nome;
        this.nascimento = dados.nascimento;
        this.telefone = dados.telefone;
    }
    
    
}
