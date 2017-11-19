package br.ufsc.ine.ine5605.trabalho2.Acesso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public class TelaListagemAcessosNegados extends JFrame {

    private TelaAcesso telaAcesso;
    private JPanel painelTabela;
    private JPanel painelBotoesOpcoes;
    private JLabel opcaoAcessar;
    private JCheckBox listarTodos;
    private JCheckBox listarMatricula;
    private JCheckBox listarMotivo;
    private JTextField dadosAcesso;
    private JButton buscar;
    private JTable tabelaListagem;
    private JScrollPane barraDeRolagem;
    private DefaultTableModel modelo;
    private JButton voltar;
    private JButton sair;
    private JComboBox motivosAcesso;

    public TelaListagemAcessosNegados(TelaAcesso telaAcesso) {
        this.telaAcesso = telaAcesso;
        this.modelo = new DefaultTableModel();
        this.criaTabela(modelo);
        this.inicializarComponentes();

    }

    private void inicializarComponentes() {
        this.setLayout(new BorderLayout());
        barraDeRolagem = new JScrollPane(tabelaListagem);
        painelTabela = new JPanel();
        painelTabela.add(BorderLayout.CENTER, barraDeRolagem);
        Dimension dimensaoBotoes = new Dimension(200, 40);

        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesListagem gerenciador = new GerenciadorBotoesListagem();
        painelBotoesOpcoes = new JPanel(new GridBagLayout());

        this.opcaoAcessar = new JLabel("Selecione uma das opções disponíveis para listar os acessos negados.");
        this.listarTodos = new JCheckBox("Listar Todos");
        this.listarMatricula = new JCheckBox("Listar por Matrícula");
        this.listarMotivo = new JCheckBox("Listar por Motivo");
        this.buscar = new JButton("Buscar");
        
        MotivoAcesso [] motivos = {MotivoAcesso.ATRASADO, MotivoAcesso.BLOQUEADO, MotivoAcesso.OK, MotivoAcesso.OUTRO, MotivoAcesso.PERMISSAO};
        this.motivosAcesso = new JComboBox(motivos);
        this.dadosAcesso = new JTextField();
        
        c.gridx = 0;
        c.gridy = 1;
        listarTodos.addItemListener(gerenciador);
        painelBotoesOpcoes.add(listarTodos, c);

        c.gridx = 1;
        c.gridy = 1;
        listarMatricula.addItemListener(gerenciador);
        painelBotoesOpcoes.add(listarMatricula, c);

        c.gridx = 2;
        c.gridy = 1;
        listarMotivo.addItemListener(gerenciador);
        painelBotoesOpcoes.add(listarMotivo, c);

        c.insets = new Insets(10, 10, 10, 10);
        voltar = new JButton("Voltar ao Menu De Acessos");
        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.WEST;
        voltar.addActionListener(gerenciador);
        voltar.setPreferredSize(dimensaoBotoes);
        painelBotoesOpcoes.add(voltar, c);
        
        c.gridy = 2;
        c.gridx = 1;
        dadosAcesso.setPreferredSize(new Dimension(150,30));
        c.anchor = GridBagConstraints.CENTER;
        painelBotoesOpcoes.add(dadosAcesso, c);
        
        sair = new JButton("Sair");
        c.gridy = 2;
        c.gridx = 2;
        c.anchor = GridBagConstraints.EAST;
        sair.setPreferredSize(dimensaoBotoes);
        sair.addActionListener(gerenciador);
        painelBotoesOpcoes.add(sair, c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridwidth = 2;
        painelBotoesOpcoes.add(opcaoAcessar, c);
        
        c.gridx = 2;
        c.gridy = 0;
        buscar.addActionListener(gerenciador);
        painelBotoesOpcoes.add(buscar, c);

        this.add(BorderLayout.PAGE_START, painelTabela);
        this.add(BorderLayout.PAGE_END, painelBotoesOpcoes);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void criaTabela(DefaultTableModel modelo) {
        tabelaListagem = new JTable(modelo);
        modelo.addColumn("Funcionário");
        modelo.addColumn("Descrição Acesso");
        modelo.addColumn("Horário");
        tabelaListagem.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabelaListagem.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabelaListagem.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabelaListagem.setPreferredScrollableViewportSize(new Dimension(650, 180));
        tabelaListagem.setRowHeight(20);
    }

    private void updateData(ArrayList<Acesso> acessos) {
        modelo.setNumRows(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (Acesso a : acessos) {
            String horario = "";
            if (a.getHorario() != null) {
                horario = ("Acesso negado às: " + (sdf.format(a.getHorario().getTime())));
            }
            modelo.addRow(new Object[]{a.getMatricula(), a.getMotivo().getDescricao(), horario});
        }
    }

    public class GerenciadorBotoesListagem implements ItemListener, ActionListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == listarTodos) {
                if (listarTodos.isSelected()) {
                    listarMatricula.setVisible(false);
                    listarMotivo.setVisible(false);
                    dadosAcesso.setVisible(false);
                    motivosAcesso.setVisible(false);
                }
                else{
                    listarMatricula.setVisible(true);
                    listarMotivo.setVisible(true);
                    dadosAcesso.setVisible(true);
                    motivosAcesso.setVisible(false);
                }
            } else if (e.getSource() == listarMatricula) {
                if (listarMatricula.isSelected()) {
                    listarMotivo.setVisible(false);
                    listarTodos.setVisible(false);
                    motivosAcesso.setVisible(false);
                }
                else{
                    listarMotivo.setVisible(true);
                    listarTodos.setVisible(true);
                    motivosAcesso.setVisible(true);                
                }
            } else if (e.getSource() == listarMotivo) {
                if (listarMotivo.isSelected()) {
                    listarTodos.setVisible(false);
                    listarMatricula.setVisible(false);
                    motivosAcesso.setVisible(true);
                }
                else{
                    listarTodos.setVisible(true);
                    listarMatricula.setVisible(true);
                    motivosAcesso.setVisible(false);
                }
                }
            }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buscar) {
                if (listarTodos.isSelected()) {
                    updateData(telaAcesso.getControladorAcesso().findAcessosNegados());
                    listarMatricula.setVisible(true);
                    listarMotivo.setVisible(true);
                    dadosAcesso.setVisible(true);
                    motivosAcesso.setVisible(false);
                }

                if (listarMatricula.isSelected()) {
                    try {
                        int matricula = Integer.parseInt(dadosAcesso.getText());
                        telaAcesso.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().findFuncionarioByMatricula(matricula);
                        updateData(telaAcesso.getControladorAcesso().findAcessosNegadosByMatricula(matricula));
                        listarMotivo.setVisible(true);
                        listarTodos.setVisible(true);
                        motivosAcesso.setVisible(false);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "A matrícula deve ser um número inteiro, e deve ser de um funcionário cadastrado. Tente novamente", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (listarMotivo.isSelected()) {
                    MotivoAcesso motivo = (MotivoAcesso)motivosAcesso.getSelectedItem();
                    updateData(telaAcesso.getControladorAcesso().findAcessosNegadosByMotivo(motivo));
                    listarMotivo.setVisible(true);
                    listarTodos.setVisible(true);
                    motivosAcesso.setVisible(false); 
                }

            }
        }

    }
}
