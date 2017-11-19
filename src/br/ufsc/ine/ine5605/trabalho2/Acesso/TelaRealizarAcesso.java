package br.ufsc.ine.ine5605.trabalho2.Acesso;

import br.ufsc.ine.ine5605.trabalho2.Funcionario.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public class TelaRealizarAcesso extends JFrame {

    private final TelaAcesso telaAcesso;
    private JLabel matricula;
    private JLabel horario;
    private JComboBox matriculaEditavel;
    private JComboBox horaEditavel;
    private JComboBox minutoEditavel;
    private JButton realizarAcesso;
    private JButton voltar;
    private JPanel CRealizarAcesso;
    private JFrame telaCRealizarAcesso;

    public TelaRealizarAcesso(TelaAcesso telaAcesso) {
        super("Tela para Realizar os Acessos");
        this.telaAcesso = telaAcesso;
        this.inicializarComponentes();
        this.inicializarTelaCRealizarAcesso();
    }

    private void inicializarComponentes() {
        Dimension dimensaoTextos = new Dimension(140, 20);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        Funcionario[] funcionarios = new Funcionario[this.telaAcesso.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().getFuncionarios().size()];
        for(int i = 0; i < this.telaAcesso.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().getFuncionarios().size(); i++) {
            funcionarios[i] = this.telaAcesso.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().getFuncionarios().get(i);
        }
        GerenciadorJComboBox gerenciadorCaixa = new GerenciadorJComboBox();
        this.matricula = new JLabel();
        this.matriculaEditavel = new JComboBox(funcionarios);
        this.matriculaEditavel.addActionListener(gerenciadorCaixa);
        
        Integer[] horas = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
        Integer[] minutos = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
        
        this.horario = new JLabel();
        this.horaEditavel = new JComboBox(horas);        
        this.horaEditavel.addActionListener(gerenciadorCaixa);
        this.minutoEditavel = new JComboBox(minutos);
        this.minutoEditavel.addActionListener(gerenciadorCaixa);
        this.realizarAcesso = new JButton("Acessar");
        this.voltar = new JButton("Voltar à tela de Acessos");
        
        GerenciadorTextosBotoesAcesso gerenciadorBotoesAcesso = new GerenciadorTextosBotoesAcesso();
        this.realizarAcesso.addActionListener(gerenciadorBotoesAcesso);
        this.voltar.addActionListener(gerenciadorBotoesAcesso);
        
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        this.matricula.setText("Matricula:  ");
        container.add(this.matricula, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        this.matriculaEditavel.setPreferredSize(dimensaoTextos);
        container.add(this.matriculaEditavel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        this.horario.setText("Horario Atual :");
        container.add(this.horario, c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        this.horaEditavel.setPreferredSize(dimensaoTextos);
        container.add(this.horaEditavel, c);
        
        c.gridx = 2;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        this.minutoEditavel.setPreferredSize(dimensaoTextos);
        container.add(this.minutoEditavel, c);
        
        c.gridy = 3;
        c.gridx = 0;
        container.add(this.realizarAcesso, c);

        c.gridy = 3;
        c.gridx = 2;
        container.add(this.voltar, c);
        
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void inicializarTelaCRealizarAcesso(){
        this.CRealizarAcesso = new JPanel(new GridBagLayout());
        this.telaCRealizarAcesso = new JFrame();
        
        Container container = telaCRealizarAcesso.getContentPane();
        container.add(CRealizarAcesso);      
    }
    
    public void updateData(){
        this.matriculaEditavel.removeAllItems();
        this.horaEditavel.removeAllItems();
        this.minutoEditavel.removeAllItems();       
        for (Funcionario funcionarioAtual : telaAcesso.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().getFuncionarios()) {
            this.matriculaEditavel.addItem(funcionarioAtual);
        }
        for (int i = 0; i < 24; i++) {
            this.horaEditavel.addItem(i);
        }
        for (int i = 0; i < 60; i++) {
            this.minutoEditavel.addItem(i);
        }
    }

    public class GerenciadorTextosBotoesAcesso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == realizarAcesso) {                
                try {
                    int matricula = matriculaEditavel.getSelectedIndex() + 1;
                    int hora = (int) horaEditavel.getSelectedItem();
                    int minuto = (int) minutoEditavel.getSelectedItem();
                    
                    if(telaAcesso.getControladorAcesso().verificaAcesso(matricula, hora, minuto) != null) {
                        JOptionPane.showConfirmDialog(null, "Acess OK!");
                        updateData();
                        setVisible(true);
                    } else {
                        JOptionPane.showConfirmDialog(null, "Problema ao realizar o Acesso!");
                        updateData();
                        setVisible(true);
                    }        
                }
                catch (IllegalArgumentException exc) {
                    Logger.getLogger(TelaRealizarAcesso.class.getName()).log(Level.SEVERE, null, exc);
                    JOptionPane.showMessageDialog(rootPane, exc.getMessage());
                }
                updateData();
            }
            else if(event.getSource() == voltar){
                setVisible(false);
                telaAcesso.setVisible(true);
            }
        }
    }
    
    public class GerenciadorJComboBox implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){}
    }
}