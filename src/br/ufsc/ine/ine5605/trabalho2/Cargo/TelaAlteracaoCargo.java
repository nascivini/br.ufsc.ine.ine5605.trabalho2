package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaAlteracaoCargo extends JFrame {

    private TelaCargo telaCargo;
    private JButton alterar;
    private JButton voltar;
    private JButton sair;
    private JScrollPane barraDeRolagem;
    public final DefaultTableModel modelo;
    private JTable tabelaCargos;
    private JPanel painelTabela;
    private JPanel painelBotoes;
    private TelaAlteracaoCargo telaAlteracaoCargo;

    public TelaAlteracaoCargo(TelaCargo telaCargo) {
        super("Tela de Alteração de Cargos");
        this.telaCargo = telaCargo;
        this.telaAlteracaoCargo = this;
        this.modelo = new DefaultTableModel();
        this.criaTabela();
        this.inicializarComponentes();
    }

    public TelaCargo getTelaCargo() {
        return telaCargo;
    }

    private void inicializarComponentes() {
        this.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        Dimension d = new Dimension(200, 40);
        GerenciadorBotoesAlteracaoCargos gerenciador = new GerenciadorBotoesAlteracaoCargos();

        this.barraDeRolagem = new JScrollPane(tabelaCargos);
        this.alterar = new JButton("Alterar Cargo Selecionado");
        this.painelTabela = new JPanel();
        this.painelBotoes = new JPanel(new GridBagLayout());
        this.voltar = new JButton("Voltar ao Menu de Cargos");
        this.sair = new JButton("Sair");

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        this.updateData(modelo);
        painelTabela.add(barraDeRolagem, c);

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        alterar.setPreferredSize(d);
        alterar.addActionListener(gerenciador);
        painelBotoes.add(alterar, c);

        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        voltar.setPreferredSize(d);
        voltar.addActionListener(gerenciador);
        painelBotoes.add(voltar, c);

        c.gridx = 3;
        c.gridy = 1;
        sair.setPreferredSize(d);
        sair.addActionListener(gerenciador);
        painelBotoes.add(sair, c);

        this.add(BorderLayout.PAGE_START, painelTabela);
        this.add(BorderLayout.PAGE_END, painelBotoes);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void criaTabela() {
        tabelaCargos = new JTableEditavel(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Horários");
        tabelaCargos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabelaCargos.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabelaCargos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabelaCargos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabelaCargos.setPreferredScrollableViewportSize(new Dimension(650, 200));
        tabelaCargos.setRowHeight(20);

        this.updateData(modelo);
    }

    public void updateData(DefaultTableModel modelo) {
        modelo.setNumRows(0);

        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        CargoComboBoxEditor tiposCargoEditavel = new CargoComboBoxEditor(tiposCargo);

        TableColumn tabelaTipos = tabelaCargos.getColumnModel().getColumn(2);
        tabelaTipos.setCellEditor(tiposCargoEditavel);
        tabelaTipos.setCellRenderer(new CargoComboBoxRenderer(tiposCargo));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        for (Cargo c : telaCargo.getControladorCargo().getCargos()) {
            String horarios = "";
            if (c.getHorarios() != null) {
                for (int i = 0; i < c.getHorarios().size(); i = i + 2) {
                    horarios = horarios + "De: " + sdf.format(c.getHorarios().get(i).getTime()) + "h";
                    horarios = horarios + " á: " + sdf.format(c.getHorarios().get(i + 1).getTime()) + "h;";
                }
            } else {
                horarios = "Cargo Gerencial, não possui horários.";
            }

            modelo.addRow(new Object[]{c.getCodigo(), c.getNome(), tabelaTipos.getCellRenderer().getTableCellRendererComponent(tabelaCargos, c.getTipoCargo(), true, rootPaneCheckingEnabled, 0, 0), horarios});
        }
    }

    public class CargoComboBoxRenderer extends JComboBox implements TableCellRenderer {

        public CargoComboBoxRenderer(TipoCargo[] values) {
            super(values);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            setSelectedItem(value);
            return this;
        }
    }

    public class CargoComboBoxEditor extends DefaultCellEditor {

        public CargoComboBoxEditor(TipoCargo[] items) {
            super(new JComboBox(items));
        }
    }

    public class GerenciadorBotoesAlteracaoCargos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == alterar) {

                try {
                    int objAtual = tabelaCargos.getSelectedRow();
                    int codigo = (int) tabelaCargos.getModel().getValueAt(objAtual, 0);
                    String nome = (String) tabelaCargos.getModel().getValueAt(objAtual, 1);
                    CargoComboBoxRenderer tipoRenderer = (CargoComboBoxRenderer) tabelaCargos.getCellRenderer(objAtual, 2)
                    ;
                    TipoCargo tipo = (TipoCargo)tipoRenderer.getSelectedItem();
                    Cargo alterado = telaCargo.getControladorCargo().findCargoByCodigo(codigo);
                    
                    if (!(tipo.equals(alterado.getTipoCargo()))) {
                        if ((tipo.equals(TipoCargo.GERENCIAL)) && (!(alterado.getTipoCargo().equals(TipoCargo.GERENCIAL)))) {
                            alterado.setTipoCargo(tipo);
                            alterado.getHorarios().clear();
                            JOptionPane.showMessageDialog(null, "Cargo Alterado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        } else if (tipo.equals(TipoCargo.COMUM) && (!alterado.equals(TipoCargo.COMUM))) {
                            alterado.setTipoCargo(tipo);
                            JOptionPane.showMessageDialog(null, "Você alterou o tipo de Cargo do cargo selecionado para Comum. Será necessário cadastrar horários para este Cargo.", "Aviso!", JOptionPane.WARNING_MESSAGE);
                            TelaContinuarCadastroHorarios tela = new TelaContinuarCadastroHorarios(alterado, telaAlteracaoCargo);
                            tela.adicionarHorarios.setVisible(false);
                            tela.adicionarHorarios1.setVisible(true);
                            tela.setLocationRelativeTo(null);
                            tela.updateData();
                            tela.setVisible(true);
                        } else if (tipo.equals(TipoCargo.CONVIDADO) && (!alterado.equals(TipoCargo.CONVIDADO))) {
                            alterado.setTipoCargo(tipo);
                            alterado.getHorarios().clear();
                            JOptionPane.showMessageDialog(null, "Cargo Alterado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }
                    telaCargo.getControladorCargo().findCargoByNome(nome);
                    alterado.setNome(nome);
                    updateData(modelo);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                    updateData(modelo);
                }
            } else if (e.getSource() == voltar) {
                setVisible(false);
                telaCargo.setVisible(true);
            } else if (e.getSource() == sair) {
                System.exit(0);
            }
        }

    }

}
