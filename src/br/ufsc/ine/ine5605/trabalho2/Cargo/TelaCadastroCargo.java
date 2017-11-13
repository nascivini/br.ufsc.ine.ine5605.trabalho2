/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author 47895159828
 */
public class TelaCadastroCargo extends JFrame {
    
    private JComboBox tipo;
    private JTextField codigo;
    private JTextField nome;
    private JTextField horario1;
    private JTextField horario2;
    private final TelaCargo telaCargo;
    
    public TelaCadastroCargo(TelaCargo telaCargo){
        super("Tela de Cadastro de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
    }

    private void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 1));
        GridBagConstraints c = new GridBagConstraints();
        
        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        
        this.tipo = new JComboBox(tiposCargo);
        
        this.codigo = new JTextField(this.telaCargo.getControladorCargo().geraSequencialCargo());
        this.nome = new JTextField();
        this.horario1 = new JTextField();
        this.horario2 = new JTextField();
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, WIDTH, WIDTH, WIDTH);
        container.add(codigo, c);
        
        container.add(tipo, c);
        c.gridx = 0;
        c.gridy = 1;        
        container.add(nome, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(horario1, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(horario2, c);
        
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
