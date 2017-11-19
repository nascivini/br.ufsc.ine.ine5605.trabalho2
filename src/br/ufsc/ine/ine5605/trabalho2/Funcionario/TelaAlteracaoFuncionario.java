/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 09108881910
 */
public class TelaAlteracaoFuncionario extends JFrame {
    
    private final TelaFuncionario telaFuncionario;
    
    
    public TelaAlteracaoFuncionario(TelaFuncionario telaFuncionario) {
        super("Tela de Exclusão de Funcionário");
        this.telaFuncionario = telaFuncionario;
        this.modelo = new DefaultTableModel();
        this.criaTabela();
        this.inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        this.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        Dimension d = new Dimension(200, 40);

        GerenciadorBotoesExclusaoFuncionario gerenciador = new GerenciadorBotoesExclusaoFuncionario();
        
        this.barraRolagem = new JScrollPane(tabelaFuncionarios);
        this.excluir = new JButton("Excluir funcionário");
        this.painelTabela = new JPanel();
        this.painelBotoes = new JPanel(new GridBagLayout());
        this.voltar = new JButton("Voltar");
        
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.updateData(modelo);
        painelTabela.add(barraRolagem, c);

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        excluir.setPreferredSize(d);
        excluir.addActionListener(gerenciador);
        painelBotoes.add(excluir, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        voltar.setPreferredSize(d);
        voltar.addActionListener(gerenciador);
        painelBotoes.add(voltar, c);

        this.add(BorderLayout.PAGE_START, painelTabela);
        this.add(BorderLayout.PAGE_END, painelBotoes);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void criaTabela() {
        tabelaFuncionarios = new JTable(modelo);
        tabelaFuncionarios.setPreferredScrollableViewportSize(dimensaoTabela);
        modelo.addColumn("Matrícula");
        modelo.addColumn("CPF");
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("Nascimento");
        modelo.addColumn("Telefone");
        modelo.addColumn("Salário");
        modelo.addColumn("Acessos Negados");
        tabelaFuncionarios.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabelaFuncionarios.getColumnModel().getColumn(7).setPreferredWidth(50);
        this.updateData(modelo);
        
    }
    
    public void updateData(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        
        for (Funcionario f : telaFuncionario.getControladorFuncionario().getFuncionarios()) {
                modelo.addRow(new Object[]{f.getMatricula(), f.getCpf(), f.getNome(), f.getCargo(), f.getNascimento(), f.getTelefone(), 
                    f.getSalario(), f.getnAcessosNegados()});

        }
    }
    

    
    public class GerenciadorBotoesExclusaoFuncionario implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == excluir){
                try {
                
                    int funcAtual = tabelaFuncionarios.getSelectedRow();
                    int matricula = (int) tabelaFuncionarios.getModel().getValueAt(funcAtual, 0);
                        if (funcAtual >= 0) {
                            telaFuncionario.getControladorFuncionario().excluirFuncionario(telaFuncionario.getControladorFuncionario().retornaFuncionarioByMatricula(matricula));
                                JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso!");
                                updateData(modelo);
                                setVisible(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Selecione um funcionário.");
                        }
                }
                catch(IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(null, "Erro desconhecido. Contate o administrador do sistema.");
                }
            
            }      
            else if(e.getSource() == voltar) {
                setVisible(false);
            }
        }
        
    }
    
}
    
    
}
