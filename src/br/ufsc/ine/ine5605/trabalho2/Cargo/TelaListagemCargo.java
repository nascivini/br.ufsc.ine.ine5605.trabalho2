/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Viinicius
 */
public class TelaListagemCargo extends JFrame {
    private final TelaCargo telaCargo;
    private JButton atualizarLista;
    private JTextField nome;
    private JTextField codigo;
    private JTextField horarios;
    private JTextField tipo;
    
    public TelaListagemCargo(TelaCargo telaCargo){
        super("Tela de Listagem de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
    }
    
    public void inicializarComponentes(){
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        this.atualizarLista = new JButton("Atualizar");
        nome = new JTextField();
        codigo = new JTextField();
        tipo = new JTextField();
        this.updateData(container);
        
    }
    
    public void updateData(Container container){
        ArrayList<Cargo> cargos = this.telaCargo.getControladorCargo().getCargos();
        GridBagConstraints c = new GridBagConstraints();
        Dimension dim = new Dimension(100,100);
        
        int line = 0;
        if(!cargos.isEmpty()){
            for(Cargo cargo : cargos){ 
                
                nome.setText(cargo.getNome());
                nome.setPreferredSize(dim);
                c.gridx = line;
                c.gridy = 0;
                container.add(nome, c);
            
                codigo.setText(String.valueOf(cargo.getCodigo()));
                codigo.setPreferredSize(dim);
                c.gridx = line;
                c.gridy = 1;
                container.add(nome, c);
            
                tipo.setText(cargo.getTipoCargo().getDescricao());
                tipo.setPreferredSize(dim);
                c.gridx = line;
                c.gridy = 2;
                container.add(nome, c);
            
                line++;
            
            }
        }
    }
    
    }

