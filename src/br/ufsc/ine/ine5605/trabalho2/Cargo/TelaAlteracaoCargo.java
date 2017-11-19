package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaAlteracaoCargo extends JFrame {

    private final TelaCargo telaCargo;
    private final TelaAlteracaoCargo telaAlteracaoCargo;
    private JButton alterar;
    private JButton voltar;
    private JButton sair;
    private JScrollPane barraDeRolagem;
    public final DefaultTableModel modelo;
    private JTable tabelaCargos;
    private JPanel painelTabela;
    private JPanel painelBotoes;

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
        tabelaCargos = new JTable(modelo);
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
    }

    public void updateData(DefaultTableModel modelo) {
        modelo.setNumRows(0);

        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        TableColumn tabelaTipos = tabelaCargos.getColumnModel().getColumn(2);

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
            modelo.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getTipoCargo().getDescricao(), horarios});
        }
    }

    public class GerenciadorBotoesAlteracaoCargos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == alterar) {
                if (tabelaCargos.getRowCount() > 0) {
                    int objAtual = tabelaCargos.getSelectedRow();
                    int codigo = (int) tabelaCargos.getModel().getValueAt(objAtual, 0);
                    Cargo alterado = telaCargo.getControladorCargo().findCargoByCodigo(codigo);
                    TelaAlteracaoDados telaAlteracaoDados = new TelaAlteracaoDados(alterado, telaAlteracaoCargo);
                    telaAlteracaoDados.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Ainda não há cargos cadastrados!", "Alerta", JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == voltar) {
                setVisible(false);
                telaCargo.setVisible(true);
            } else if (e.getSource() == sair) {
                System.exit(0);
            }
        }

    }

    public class TelaAlteracaoDados extends JFrame {

        private TelaAlteracaoCargo telaAlteracaoCargo;
        private Cargo cargoAlterado;
        private JLabel descricao;
        private JLabel codigo;
        private JLabel nome;
        private JTextField nomeEditavel;
        private JLabel tipo;
        private JComboBox tipoEditavel;
        private JButton salvarAlteracoes;

        public TelaAlteracaoDados(Cargo cargoAlterado, TelaAlteracaoCargo telaAlteracaoCargo) {
            super("Tela de Alteração de Dados");
            this.telaAlteracaoCargo = telaAlteracaoCargo;
            this.cargoAlterado = cargoAlterado;
            this.inicializarComponentes();
        }

        private void inicializarComponentes() {
            Container container = this.getContentPane();
            container.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            GerenciadorDadosAlteracao gerenciador = new GerenciadorDadosAlteracao();
            Dimension dimensaoBotoes = new Dimension(200, 40);
            Dimension dimensaoTextos = new Dimension(140, 20);

            this.descricao = new JLabel("Edite apenas os dados que deseja alterar.");
            c.gridx = 1;
            c.gridy = 0;
            c.insets = new Insets(20, 0, 20, 0);
            c.anchor = GridBagConstraints.CENTER;
            container.add(descricao, c);

            this.codigo = new JLabel("Código (inálterável): " + Integer.toString(cargoAlterado.getCodigo()));
            c.gridx = 1;
            c.gridy = 1;

            container.add(codigo, c);

            this.nome = new JLabel("Nome: ");
            c.gridx = 0;
            c.gridy = 2;
            container.add(nome, c);

            this.nomeEditavel = new JTextField(cargoAlterado.getNome());
            c.gridx = 1;
            c.gridy = 2;
            nomeEditavel.setPreferredSize(dimensaoTextos);
            container.add(nomeEditavel, c);

            this.tipo = new JLabel("Tipo: ");
            c.gridx = 0;
            c.gridy = 3;
            container.add(tipo, c);

            this.tipoEditavel = new JComboBox(new TipoCargo[]{TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL});
            c.gridx = 1;
            c.gridy = 3;
            tipoEditavel.setPreferredSize(dimensaoTextos);
            tipoEditavel.setSelectedItem(cargoAlterado.getTipoCargo());
            container.add(tipoEditavel, c);

            this.salvarAlteracoes = new JButton("Salvar Alterações");
            c.gridx = 1;
            c.gridy = 4;
            salvarAlteracoes.setPreferredSize(dimensaoBotoes);
            salvarAlteracoes.addActionListener(gerenciador);
            container.add(salvarAlteracoes, c);

            this.setSize(500, 400);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

        public class GerenciadorDadosAlteracao implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == salvarAlteracoes) {
                    TipoCargo tipoRegistrado = cargoAlterado.getTipoCargo();
                    TipoCargo tipoSelecionado = (TipoCargo) tipoEditavel.getSelectedItem();
                    if (tipoRegistrado != tipoSelecionado) {
                        if (tipoRegistrado.equals(TipoCargo.COMUM) && tipoSelecionado.equals(TipoCargo.GERENCIAL)) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(true);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Alterações Salvas!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else if (tipoRegistrado.equals(TipoCargo.CONVIDADO) && tipoSelecionado.equals(TipoCargo.GERENCIAL)) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(true);
                            cargoAlterado.setPermiteAcesso(true);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Alterações Salvas!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else if (tipoRegistrado.equals(TipoCargo.COMUM) && tipoSelecionado.equals(TipoCargo.CONVIDADO)) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(false);
                            cargoAlterado.setPermiteAcesso(false);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Alterações Salvas!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else if (tipoRegistrado.equals(TipoCargo.GERENCIAL) && tipoSelecionado.equals(TipoCargo.COMUM)) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(false);
                            cargoAlterado.setPermiteAcesso(true);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Você alterou o tipo de Cargo de Gerencial para Comum. Será necessário cadastrar horários para este Cargo.", "Alerta", JOptionPane.WARNING_MESSAGE);
                            TelaContinuarCadastroHorarios telaContinuar = new TelaContinuarCadastroHorarios(cargoAlterado, telaAlteracaoCargo);
                            telaContinuar.updateData();
                            telaContinuar.setLocationRelativeTo(null);
                            telaContinuar.setVisible(true);
                        } else if ((tipoRegistrado.equals(TipoCargo.CONVIDADO) && tipoSelecionado.equals(TipoCargo.COMUM))) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(false);
                            cargoAlterado.setPermiteAcesso(true);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Você alterou o tipo de Cargo de Convidado para Comum. Será necessário cadastrar horários para este Cargo.", "Alerta", JOptionPane.WARNING_MESSAGE);
                            TelaContinuarCadastroHorarios telaContinuar = new TelaContinuarCadastroHorarios(cargoAlterado, telaAlteracaoCargo);
                            telaContinuar.updateData();
                            telaContinuar.setLocationRelativeTo(null);
                            telaContinuar.setVisible(true);
                        } else if (tipoRegistrado.equals(TipoCargo.GERENCIAL) && tipoSelecionado.equals(TipoCargo.CONVIDADO)) {
                            cargoAlterado.getHorarios().clear();
                            cargoAlterado.setEhGerencial(false);
                            cargoAlterado.setPermiteAcesso(false);
                            cargoAlterado.setTipoCargo(tipoSelecionado);
                            if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                                cargoAlterado.setNome(nomeEditavel.getText());
                            }
                            JOptionPane.showMessageDialog(null, "Alterações Salvas!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (!cargoAlterado.getNome().equals(nomeEditavel.getText())) {
                        cargoAlterado.setNome(nomeEditavel.getText());
                        JOptionPane.showMessageDialog(null, "Nome Alterado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    telaCargo.getControladorCargo().getCargoDAO().persist();
                }
            }
        }
    }
}
