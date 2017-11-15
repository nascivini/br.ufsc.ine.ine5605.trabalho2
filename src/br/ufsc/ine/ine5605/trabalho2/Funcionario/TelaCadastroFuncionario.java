/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Funcionario;


import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Marina
 */
public class TelaCadastroFuncionario extends JFrame {
    
    private final TelaFuncionario telaFuncionario;
    private JTextField matricula;
    private JTextField cpf;
    private JTextField nome;
    private JComboBox cargo;
    private JTextField nascimento;
    private JTextField telefone;
    private JTextField salario;
    private JButton cadastrar;
    private JButton limparTela;
    private JLabel descricao;
    //GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
    
    public TelaCadastroFuncionario(TelaFuncionario telaFuncionario){
        super("Cadastrar Funcionário");
        this.telaFuncionario = telaFuncionario;
        inicializarComponentes();
        //this.gerenciador = new GerenciadorBotoesCadastroFuncionario();
    }

    private void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
      
        this.matricula = new JTextField(this.telaFuncionario.getControladorFuncionario().gerarMatriculaSequencial());
        this.cpf = new JTextField();
        this.nome = new JTextField();
        this.cargo = new JComboBox();
        this.nascimento = new JTextField();
        this.telefone = new JTextField();
        this.salario = new JTextField();
        descricao = new JLabel();
        cadastrar = new JButton("Cadastrar");
        limparTela = new JButton("Limpar Tela");
        
        
        GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
        cadastrar.addActionListener(gerenciador);
        limparTela.addActionListener(gerenciador);

        
        c.insets = new Insets(10,0,0,0); 
        
        c.gridx = 0;
        c.gridy = 0;
        matricula.setText("Matrícula seguencial: " + telaFuncionario.getControladorFuncionario().gerarMatriculaSequencial());
        container.add(matricula, c);
        
        //cpf
        c.gridx = 0;
        c.gridy = 1;
        descricao.setText("CPF: ");
        
        c.gridx = 1;
        c.gridy = 1;
        container.add(cpf, c);
        
        //nome
        c.gridx = 0;
        c.gridy = 2;
        descricao.setText("Nome: ");
        
        c.gridx = 1;
        c.gridy = 2;
        container.add(nome, c);
        
        //cargo
        c.gridx = 0;
        c.gridy = 3;
        descricao.setText("Cargo: ");
        
        c.gridx = 1;
        c.gridy = 3;
        container.add(cargo, c);
        
        //nascimento
        c.gridx = 0;
        c.gridy = 4;
        descricao.setText("Nascimento: ");
        
        c.gridx = 1;
        c.gridy = 4;
        container.add(nascimento, c);
        
        //telefone
        c.gridx = 0;
        c.gridy = 5;
        descricao.setText("Telefone: ");
        
        c.gridx = 1;
        c.gridy = 5;
        container.add(telefone, c);
        
        //salario
        c.gridx = 0;
        c.gridy = 6;
        descricao.setText("Salário: ");
        
        c.gridx = 1;
        c.gridy = 6;
        container.add(salario, c);
        
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
   
    
        private class GerenciadorBotoesCadastroFuncionario implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == cadastrar){
                setVisible(false);
                
            }
            else {
                setVisible(false);
                
            }
        }
    
}
}
