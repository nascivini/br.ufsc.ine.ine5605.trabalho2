/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import br.ufsc.ine.ine5605.trabalho2.Cargo.Cargo;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author 09108881910
 */
public class TelaAlteracaoFuncionario extends JFrame {

    private final TelaFuncionario telaFuncionario;
    private final TelaAlteracaoFuncionario telaAlteracaoFuncionario;
    private JTable tabelaFuncionarios;
    private JScrollPane barraRolagem;
    public final DefaultTableModel modelo;
    private JButton alterar, voltar;
    private JPanel painelBotoes, painelTabela;
    Dimension dimensaoTabela = new Dimension(600, 200);
    
    
    
    public TelaAlteracaoFuncionario(TelaFuncionario telaFuncionario) {
        super("Tela de Alteração de Funcionário");
        this.telaFuncionario = telaFuncionario;
        this.telaAlteracaoFuncionario = this;
        this.modelo = new DefaultTableModel();
        this.criaTabela();
        this.inicializarComponentes();
    }
    
    /**
     * Configura os botões e labels da página
     */
    private void inicializarComponentes() {
        this.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        Dimension d = new Dimension(200, 40);

        GerenciadorBotoesAlteracaoFuncionario gerenciador = new GerenciadorBotoesAlteracaoFuncionario();
        
        this.barraRolagem = new JScrollPane(tabelaFuncionarios);
        this.alterar = new JButton("Alterar funcionário");
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
        alterar.setPreferredSize(d);
        alterar.addActionListener(gerenciador);
        painelBotoes.add(alterar, c);

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
    
    /**
     * Método para criar a tabela com os dados dos funcionários
     */
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
    
    /**
     * Dados da tabela
     * @param modelo 
     */
    
    public void updateData(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        
        for (Funcionario f : telaFuncionario.getControladorFuncionario().getFuncionarios()) {
                modelo.addRow(new Object[]{f.getMatricula(), f.getCpf(), f.getNome(), f.getCargo(), f.getNascimento(), f.getTelefone(), 
                    f.getSalario(), f.getnAcessosNegados()});

        }
    }
    

    /**
     * Aponta ações ao clicar em cada um dos botões da tela
     */
    public class GerenciadorBotoesAlteracaoFuncionario implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == alterar){
                        if (tabelaFuncionarios.getRowCount() > 0 && tabelaFuncionarios.getSelectedRow() >=0) {
                            int funcAtual = tabelaFuncionarios.getSelectedRow();
                            int matricula = (int) tabelaFuncionarios.getModel().getValueAt(funcAtual, 0);
                            Funcionario selecionado = telaFuncionario.getControladorFuncionario().retornaFuncionarioByMatricula(matricula);
                            TelaAlteracaoDadosFunc telaAlteracaoDadosFunc = new TelaAlteracaoDadosFunc(selecionado, telaAlteracaoFuncionario);
                            telaAlteracaoDadosFunc.setVisible(true);
                        }
                        else if (!(tabelaFuncionarios.getSelectedRow() >=0)) {
                            JOptionPane.showMessageDialog(null, "Selecione um funcionário para poder alterá-lo.", "Alerta", JOptionPane.WARNING_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Ainda não há funcionários cadastrados!", "Alerta", JOptionPane.WARNING_MESSAGE);
                        }                   
            } else if (e.getSource() == voltar) {
                setVisible(false);
                telaFuncionario.setVisible(true);
            }   
    }
    }
    
    /**
     * Tela que abre quando o usuário clica em alterar
     */
    public class TelaAlteracaoDadosFunc extends JFrame {
        private TelaAlteracaoFuncionario telaAlteracaoFuncionario;
        private Funcionario funcAlterado;
        private JLabel matricula, lMatricula, lCpf, lNome, lNascimento, lTelefone, lSalario, lCargo;
        private JTextField nome, salario;
        private JFormattedTextField cpf, nascimento, telefone;
        private JComboBox cargo;
        private JButton salvar, cancelar;
        private Dimension dBotao = new Dimension(130, 30);
        

        public TelaAlteracaoDadosFunc(Funcionario funcAlterado, TelaAlteracaoFuncionario telaAlteracaoFuncionario) {
            super("Tela de Alteração dos Dados do Funcionário");
            this.telaAlteracaoFuncionario = telaAlteracaoFuncionario;
            this.funcAlterado = funcAlterado;
            this.inicializarComponentes();
        }
        
        /**
         * Configura os botões e labels da tela
         */
        private void inicializarComponentes() {
            Container container = this.getContentPane();
            container.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            Dimension dimensaoTextos = new Dimension(200, 30);

            this.matricula = new JLabel(Integer.toString(funcAlterado.getMatricula()));
            MaskFormatter mascaraCpf = null;
            this.nome = new JTextField(funcAlterado.getNome());
            this.cargo = new JComboBox();
            MaskFormatter mascaraNascimento = null;
            MaskFormatter mascaraTelefone = null;
            this.salario = new JTextField(Float.toString(funcAlterado.getSalario()));
            this.lMatricula = new JLabel();
            this.lCpf = new JLabel();
            this.lNome = new JLabel();
            this.lCargo = new JLabel();
            this.lNascimento = new JLabel();
            this.lTelefone = new JLabel();
            this.lSalario = new JLabel();
            salvar = new JButton("Salvar");
            cancelar = new JButton("Cancelar");
        
        
        
        try {
            mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraNascimento = new MaskFormatter("##/##/####");
            mascaraTelefone = new MaskFormatter("(##)#####-####");
        }
        catch (ParseException erro) {
            
        }
        
        this.cpf = new JFormattedTextField(Long.toString(funcAlterado.getCpf()));
        this.nascimento = new JFormattedTextField(funcAlterado.getNascimento());
        this.telefone = new JFormattedTextField(Long.toString(funcAlterado.getTelefone()));

        GerenciadorBotoesAlteracaoDadosFunc gerenciador = new GerenciadorBotoesAlteracaoDadosFunc();
        salvar.addActionListener(gerenciador);
        cancelar.addActionListener(gerenciador);
       
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
        
        for (Cargo cargoAtual : telaFuncionario.getControladorFuncionario().getControladorPrincipal().getControladorCargo().getCargos()) {
            cargo.addItem(cargoAtual);
        }
        cargo.setSelectedItem(funcAlterado.getCargo());

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
        salvar.setPreferredSize(dBotao);
        container.add(salvar, c);

        c.gridx = 1;
        c.gridy = 7;
        cancelar.setPreferredSize(dBotao);
        container.add(cancelar, c);

        c.gridx = 1;
        c.gridy = 8;

        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Dados que aparecem nos campos JTextField e JComboBox, relacionados ao funcionário selecionado
     */
    public void updateData() {
        this.matricula.setText(telaFuncionario.getControladorFuncionario().gerarMatriculaSequencial() + " (gerado automaticamente)");
        this.cpf.setText(Long.toString(funcAlterado.getCpf()));
        this.nome.setText(funcAlterado.getNome());
        for (Cargo cargoAtual : telaFuncionario.getControladorFuncionario().getControladorPrincipal().getControladorCargo().getCargos()) {
            cargo.addItem(cargoAtual);
        }
        this.cargo.setSelectedItem(funcAlterado.getCargo());
        this.nascimento.setText(funcAlterado.getNascimento());
        this.telefone.setText(Long.toString(funcAlterado.getTelefone()));
        this.salario.setText(Float.toString(funcAlterado.getSalario()));
    }
    
    
    /**
     * Aponta ações ao clicar em cada um dos botões da tela
     */
    private class GerenciadorBotoesAlteracaoDadosFunc implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == salvar) {

                try {
               
                    funcAlterado.setCpf(Long.parseLong(cpf.getText()));
                    funcAlterado.setNome(nome.getText());
                    funcAlterado.setCargo((Cargo)cargo.getSelectedItem());
                    funcAlterado.setNascimento(nascimento.getText());
                    funcAlterado.setTelefone(Long.parseLong(telefone.getText()));
                    funcAlterado.setSalario(Float.parseFloat(salario.getText()));
                   
                    telaFuncionario.getControladorFuncionario().getFuncionarioDAO().persist();
      
                    JOptionPane.showMessageDialog(null, "Funcionário alterado com sucesso!", "Alterado!", JOptionPane.DEFAULT_OPTION);
                    telaAlteracaoFuncionario.updateData(modelo);
                    setVisible(false);   
                }
                
                catch (IllegalArgumentException erro) {
                        Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, erro);
                        JOptionPane.showConfirmDialog(null, erro.getMessage(), "Funcionário não alterado", JOptionPane.OK_CANCEL_OPTION);
                        
                    }

            } 
            else if(e.getSource() == cancelar) {
                setVisible(false);
            }
        }
        }
    }
}
    

    
   
