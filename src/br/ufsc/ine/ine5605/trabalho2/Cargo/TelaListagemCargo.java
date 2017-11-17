package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public final class TelaListagemCargo extends JFrame {

    private final TelaCargo telaCargo;
    private JPanel painelFundo;
    private JTable tabela;
    private JScrollPane barraRolagem;
    public final DefaultTableModel modelo;

    public TelaListagemCargo(TelaCargo telaCargo) {
        super("Tela de Listagem de Cargos");
        this.telaCargo = telaCargo;
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
        this.setLocation(375,150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 150);
    }

    private void criaTabela() {
        tabela = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Horários");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(80);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
        this.pesquisar(modelo);
    }

    public void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        for (Cargo c : telaCargo.getControladorCargo().getCargos()) {
            String horarios = "";
            if(c.getHorarios() != null){
            for(int i = 0; i < c.getHorarios().size(); i = i + 2){
                horarios = horarios + "De: " + sdf.format(c.getHorarios().get(i).getTime()) + "h";
                horarios = horarios + " á: " + sdf.format(c.getHorarios().get(i+1).getTime()) + "h;";
            }
            }
            modelo.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getTipoCargo(), horarios});

        }
    }
}
