/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import javax.swing.JFrame;

/**
 *
 * @author 09108881910
 */
public class TelaListagemFuncionario extends JFrame {
    
    private final TelaFuncionario telaFuncionario;
    
    
    public TelaListagemFuncionario(TelaFuncionario telaFuncionario) {
        super("Listar Funcionários");
        this.telaFuncionario = telaFuncionario;
        this.inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        
    }
    
    
}