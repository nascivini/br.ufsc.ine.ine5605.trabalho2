package br.ufsc.ine.ine5605.trabalho2.Acesso;

import br.ufsc.ine.ine5605.trabalho2.Funcionario.Funcionario;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
        this.inicializarTelaRealizarAcesso();
    }

    private void inicializarComponentes() {
        Dimension dimensaoTextos = new Dimension(200, 30);
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
        
        this.horario = new JLabel();
        this.horaEditavel = new JComboBox();        
        this.horaEditavel.addActionListener(gerenciadorCaixa);
        this.minutoEditavel = new JComboBox();
        this.minutoEditavel.addActionListener(gerenciadorCaixa);
        this.realizarAcesso = new JButton("Acessar");
        this.voltar = new JButton("Voltar à tela de Acessos");
        
        GerenciadorTextosBotoesAcesso gerenciadorBotoesAcesso = new GerenciadorTextosBotoesAcesso();
        this.realizarAcesso.addActionListener(gerenciadorBotoesAcesso);
        this.voltar.addActionListener(gerenciadorBotoesAcesso);
        
        c.insets = new Insets(20, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        this.matricula.setText("Selecione o Funcionário Abaixo para Acessar: ");
        container.add(this.matricula, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        this.matriculaEditavel.setPreferredSize(new Dimension(300, 20));
        container.add(this.matriculaEditavel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        this.horario.setText("Horario Atual: ");
        container.add(this.horario, c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.RELATIVE;
        this.horaEditavel.setPreferredSize(new Dimension(40,20));
        container.add(this.horaEditavel, c);
        
        c.gridx = 2;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = GridBagConstraints.REMAINDER;
        this.minutoEditavel.setPreferredSize(new Dimension(40,20));
        container.add(this.minutoEditavel, c);
        
        GridBagConstraints c1 = new GridBagConstraints();
        
        c1.gridx = 1;
        c1.gridy = 3;
        c1.insets = new Insets(30,20,0,20);
        realizarAcesso.setPreferredSize(dimensaoTextos);
        container.add(this.realizarAcesso, c1);

        c1.gridx = 1;
        c1.gridy = 4;
        voltar.setPreferredSize(dimensaoTextos);
        container.add(this.voltar, c1);
        
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void inicializarTelaRealizarAcesso(){
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
                    Funcionario funcionarioSelecionado = (Funcionario)matriculaEditavel.getSelectedItem();
                    
                    int matricula = funcionarioSelecionado.getMatricula();
                    int hora = (int) horaEditavel.getSelectedItem();
                    int minuto = (int) minutoEditavel.getSelectedItem();
                    
                    Acesso acessoRealizado = telaAcesso.getControladorAcesso().verificaAcesso(matricula, hora, minuto);
                    if(acessoRealizado != null) {
                        JOptionPane.showMessageDialog(null, acessoRealizado.getMotivo().getDescricao(), "Atenção!", JOptionPane.INFORMATION_MESSAGE);
                        updateData();
                        setVisible(true);
                    } 
                    else {
                        JOptionPane.showMessageDialog(null, acessoRealizado.getMotivo().getDescricao(), "Atenção", JOptionPane.ERROR_MESSAGE);
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