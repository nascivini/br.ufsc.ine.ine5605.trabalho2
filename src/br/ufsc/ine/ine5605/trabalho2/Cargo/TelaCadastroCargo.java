/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
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
        
        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        
        this.tipo = new JComboBox(tiposCargo);
        
        this.codigo = new JTextField(this.telaCargo.getControladorCargo().geraSequencialCargo());
        this.nome = new JTextField();
        this.horario1 = new JTextField();
        this.horario2 = new JTextField();
        
        container.add(codigo, 1);
        container.add(tipo, 2);
        container.add(nome, 3);
        container.add(horario1, 4);
        container.add(horario2, 5);
        
        this.setSize(600, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
