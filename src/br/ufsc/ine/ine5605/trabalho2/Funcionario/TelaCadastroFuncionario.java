/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Funcionario;


import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
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
    private JLabel matricula;
    private JTextField cpf, nome, nascimento, telefone, salario;
    private JComboBox cargo;
    private JButton cadastrar, limparTela;
    private JLabel lMatricula, lCpf, lNome, lNascimento, lTelefone, lSalario, lCargo;
    //GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
    
    public TelaCadastroFuncionario(TelaFuncionario telaFuncionario){
        super("Cadastrar Funcionário");
        this.telaFuncionario = telaFuncionario;
        this.inicializarComponentes();
        //this.gerenciador = new GerenciadorBotoesCadastroFuncionario();
    }

    private void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
      
        this.matricula = new JLabel();
        this.cpf = new JTextField();
        this.nome = new JTextField();
        this.cargo = new JComboBox();
        this.nascimento = new JTextField();
        this.telefone = new JTextField();
        this.salario = new JTextField();
        this.lMatricula = new JLabel();
        this.lCpf = new JLabel();
        this.lNome = new JLabel();
        this.lCargo = new JLabel();
        this.lNascimento = new JLabel();
        this.lTelefone = new JLabel();
        this.lSalario = new JLabel();
        cadastrar = new JButton("Cadastrar");
        limparTela = new JButton("Limpar Tela");
        
        
        GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
        cadastrar.addActionListener(gerenciador);
        limparTela.addActionListener(gerenciador);

        nome.setBounds(10, 40, 100, 50);
        c.insets = new Insets(10,0,0,0); 
        
        c.gridx = 0;
        c.gridy = 0;
        lMatricula.setText("Matrícula: ");
        container.add(lMatricula,c);
        
        c.gridx = 1;
        c.gridy = 0;
        matricula.setText(telaFuncionario.getControladorFuncionario().gerarMatriculaSequencial() + " (gerado automaticamente)");
        container.add(matricula, c);
        
        //cpf
        c.gridx = 0;
        c.gridy = 1;
        lCpf.setText("CPF: ");
        container.add(lCpf,c);
        
        c.gridx = 1;
        c.gridy = 1;
        cpf.setSize(200, 200);
        container.add(cpf, c);
        
        //nome
        c.gridx = 0;
        c.gridy = 2;
        lNome.setText("Nome: ");
        container.add(lNome,c);
        
        c.gridx = 1;
        c.gridy = 2;
        container.add(nome, c);
        
        //cargo
        c.gridx = 0;
        c.gridy = 3;
        lCargo.setText("Cargo: ");
        container.add(lCargo,c);
        
        c.gridx = 1;
        c.gridy = 3;
        container.add(cargo, c);
        
        //nascimento
        c.gridx = 0;
        c.gridy = 4;
        lNascimento.setText("Nascimento: ");
        container.add(lNascimento,c);
        
        c.gridx = 1;
        c.gridy = 4;
        container.add(nascimento, c);
        
        //telefone
        c.gridx = 0;
        c.gridy = 5;
        lTelefone.setText("Telefone: ");
        container.add(lTelefone,c);
        
        c.gridx = 1;
        c.gridy = 5;
        container.add(telefone, c);
        
        //salario
        c.gridx = 0;
        c.gridy = 6;
        lSalario.setText("Salário: ");
        container.add(lSalario,c);
        
        c.gridx = 1;
        c.gridy = 6;
        container.add(salario, c);
        
        c.gridx = 0;
        c.gridy = 7;
        container.add(cadastrar,c);
        
        c.gridx = 1;
        c.gridy = 7;
        container.add(limparTela,c);
        
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
