/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Principal;
/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ClassePrincipal {
    
    ControladorPrincipal ctrl;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControladorPrincipal ctrl = new ControladorPrincipal();
        ctrl.getTelaPrincipal().inicia();
    
    }
}
