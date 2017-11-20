/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Funcionario;


import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 09108881910
 */
public class TelaListagemFuncionario extends JFrame {
    
    private final TelaFuncionario telaFuncionario;
    private Container painelFundo;
    private JTable tabela;
    private JScrollPane barraRolagem;
    public final DefaultTableModel modelo;

    
    public TelaListagemFuncionario(TelaFuncionario telaFuncionario) {
        super("Tela de Listagem de Funcionários");
        this.telaFuncionario = telaFuncionario;
        this.modelo = new DefaultTableModel();
        this.criaTabela();
        this.inicializarComponentes();
    }
    
    public void inicializarComponentes() {
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);

        getContentPane().add(painelFundo);
        setSize(800, 150);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    /**
     * Cria tabela de acordo com as configurações
     */
    private void criaTabela() {
        tabela = new JTable(modelo);
        modelo.addColumn("Matrícula");
        modelo.addColumn("CPF");
        modelo.addColumn("Nome");
        modelo.addColumn("Cargo");
        modelo.addColumn("Nascimento");
        modelo.addColumn("Telefone");
        modelo.addColumn("Salário");
        modelo.addColumn("Acessos Negados");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(50);
        this.pesquisar(modelo);
        
    }
        /**
         * Dados a serem exibidos na tabela
         * @param modelo 
         */
        public void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        
            for (Funcionario f : telaFuncionario.getControladorFuncionario().getFuncionarios()) {
                modelo.addRow(new Object[]{f.getMatricula(), f.getCpf(), f.getNome(), f.getCargo(), f.getNascimento(), f.getTelefone(), 
                    f.getSalario(), f.getnAcessosNegados()});

            }
        }
}

   