/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import br.ufsc.ine.ine5605.trabalho2.Cargo.Cargo;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Marina
 */
public class TelaCadastroFuncionario extends JFrame {

    private final TelaFuncionario telaFuncionario;
    private JTextField nome, salario;
    private JFormattedTextField cpf, nascimento, telefone;
    private JComboBox cargo;
    private JButton cadastrar, limparTela, voltar;
    private JLabel matricula, lMatricula, lCpf, lNome, lNascimento, lTelefone, lSalario, lCargo;
    private Image imagem;
    private Dimension dBotao = new Dimension(130, 30);

    //GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
    public TelaCadastroFuncionario(TelaFuncionario telaFuncionario) {
        super("Cadastrar Funcionário");
        this.telaFuncionario = telaFuncionario;
        this.inicializarComponentes();
    }

    /**
     * Configura os botões e labels da página
     */
    private void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        Dimension dimensaoTextos = new Dimension(200, 30);

        this.matricula = new JLabel();
        MaskFormatter mascaraCpf = null;
        this.nome = new JTextField();
        this.cargo = new JComboBox();
        MaskFormatter mascaraNascimento = null;
        MaskFormatter mascaraTelefone = null;
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
        voltar = new JButton("Voltar");
        
        
        
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraNascimento = new MaskFormatter("##/##/####");
            mascaraTelefone = new MaskFormatter("(##)#####-####");
        }
        catch (ParseException erro) {
            
        }
        
        this.cpf = new JFormattedTextField(mascaraCpf);
        this.nascimento = new JFormattedTextField(mascaraNascimento);
        this.telefone = new JFormattedTextField(mascaraTelefone);

        GerenciadorBotoesCadastroFuncionario gerenciador = new GerenciadorBotoesCadastroFuncionario();
        cadastrar.addActionListener(gerenciador);
        limparTela.addActionListener(gerenciador);
        voltar.addActionListener(gerenciador);

        nome.setBounds(10, 40, 100, 50);
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        lMatricula.setText("Matrícula: ");
        container.add(lMatricula, c);

        c.gridx = 1;
        c.gridy = 0;
        container.add(matricula, c);

        //cpf
        c.gridx = 0;
        c.gridy = 1;
        lCpf.setText("CPF: ");
        container.add(lCpf, c);

        c.gridx = 1;
        c.gridy = 1;
        cpf.setSize(200, 200);
        cpf.setPreferredSize(dimensaoTextos);
        container.add(cpf, c);

        //nome
        c.gridx = 0;
        c.gridy = 2;
        lNome.setText("Nome: ");
        container.add(lNome, c);

        c.gridx = 1;
        c.gridy = 2;
        nome.setPreferredSize(dimensaoTextos);
        container.add(nome, c);

        //cargo
        c.gridx = 0;
        c.gridy = 3;
        lCargo.setText("Cargo: ");
        container.add(lCargo, c);

        c.gridx = 1;
        c.gridy = 3;
        cargo.setPreferredSize(dimensaoTextos);
        container.add(cargo, c);

        //nascimento
        c.gridx = 0;
        c.gridy = 4;
        lNascimento.setText("Nascimento: ");
        container.add(lNascimento, c);

        c.gridx = 1;
        c.gridy = 4;
        nascimento.setPreferredSize(dimensaoTextos);
        container.add(nascimento, c);

        //telefone
        c.gridx = 0;
        c.gridy = 5;
        lTelefone.setText("Telefone: ");
        container.add(lTelefone, c);

        c.gridx = 1;
        c.gridy = 5;
        telefone.setPreferredSize(dimensaoTextos);
        container.add(telefone, c);

        //salario
        c.gridx = 0;
        c.gridy = 6;
        lSalario.setText("Salário: ");
        container.add(lSalario, c);

        c.gridx = 1;
        c.gridy = 6;
        salario.setPreferredSize(dimensaoTextos);
        container.add(salario, c);

        c.gridx = 0;
        c.gridy = 7;
        cadastrar.setPreferredSize(dBotao);
        container.add(cadastrar, c);
        
        c.gridx = 0;
        c.gridy = 8;
        voltar.setPreferredSize(dBotao);
        container.add(voltar, c);

        c.gridx = 1;
        c.gridy = 7;
        limparTela.setPreferredSize(dBotao);
        container.add(limparTela, c);

        c.gridx = 1;
        c.gridy = 8;

        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * Limpa a tela
     */
    public void updateData() {
        this.matricula.setText(telaFuncionario.getControladorFuncionario().gerarMatriculaSequencial() + " (gerado automaticamente)");
        this.cpf.setText("");
        this.nome.setText("");
        this.cargo.removeAllItems();
        this.nascimento.setText("");
        this.telefone.setText("");
        this.salario.setText("");

        for (Cargo cargoAtual : telaFuncionario.getControladorFuncionario().getControladorPrincipal().getControladorCargo().getCargos()) {
            cargo.addItem(cargoAtual);
        }
    }
    
    /**
     * Aponta ações ao clicar em cada um dos botões da tela
     */
    private class GerenciadorBotoesCadastroFuncionario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cadastrar) {

                try {

                    telaFuncionario.getControladorFuncionario().findFuncionarioByCpf(Long.parseLong(cpf.getText().replaceAll("[.-]", "")));
                    DadosFuncionario novoFuncionario = new DadosFuncionario(Long.parseLong(cpf.getText().replaceAll("[.-]", "")), 
                            nome.getText(), (Cargo)cargo.getSelectedItem(), nascimento.getText(), Long.parseLong(telefone.getText().replaceAll("[()-]","")), 
                            Float.parseFloat(salario.getText()));
                    
                    telaFuncionario.getControladorFuncionario().incluirFuncionario(novoFuncionario);
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", "Cadastrado!", JOptionPane.DEFAULT_OPTION);
                    updateData();
                    setVisible(true);
                  
                }
                
                catch (IllegalArgumentException erro) {
                        Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, erro);
                        JOptionPane.showConfirmDialog(null, erro.getMessage(), "Funcionário não cadastrado", JOptionPane.OK_CANCEL_OPTION);
                        
                    } catch (ExceptionFuncionario ex) {
                    Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }

            } 
            else if(e.getSource() == limparTela) {
                updateData();
            } 
            else if(e.getSource() == voltar) {
                setVisible(false);
            }
        }

    }
}
