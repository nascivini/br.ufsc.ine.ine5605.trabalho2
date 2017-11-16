/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Viinicius
 */
public final class TelaListagemCargo extends JFrame {

    private final TelaCargo telaCargo;
    private JPanel painelFundo;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton voltar;
    public final DefaultTableModel modelo = new DefaultTableModel();

    public TelaListagemCargo(TelaCargo telaCargo) {
        super("Tela de Listagem de Cargos");
        this.telaCargo = telaCargo;
        this.criaTabela();
        this.criaJanela();
    }

    public void criaJanela() {
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        voltar = new JButton("voltar");
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);

        getContentPane().add(painelFundo);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 300);
    }

    private void criaTabela() {
        tabela = new JTable(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Horarios");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        this.pesquisar(modelo);
    }

    public void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        String horarios = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        for (Cargo c : telaCargo.getControladorCargo().getCargos()) {
            modelo.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getTipoCargo(), c.getHorarios()});

        }
    }
}
