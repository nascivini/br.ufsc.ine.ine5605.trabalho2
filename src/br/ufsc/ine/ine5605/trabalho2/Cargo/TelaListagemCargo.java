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
    private JPanel painelTabela;
    private JTable tabela;
    private JScrollPane barraRolagem;
    public final DefaultTableModel modelo;
    private JPanel painelBotoes;
    private JButton voltar;
    private JButton sair;

    public TelaListagemCargo(TelaCargo telaCargo) {
        super("Tela de Listagem de Cargos");
        this.telaCargo = telaCargo;
        this.modelo = new DefaultTableModel();
        this.criaTabela();
        this.inicializarComponentes();
    }

    public void inicializarComponentes() {
        barraRolagem = new JScrollPane(tabela);
        painelTabela = new JPanel();
        painelTabela.setLayout(new BorderLayout());
        painelTabela.add(BorderLayout.CENTER, barraRolagem);
        
        GerenciadorBotoesListagem gerenciador = new GerenciadorBotoesListagem();
        painelBotoes = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,0,0,0);
        
        voltar = new JButton("Voltar ao Menu De Cargos");
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        voltar.addActionListener(gerenciador);
        painelBotoes.add(voltar, c);
        
        sair = new JButton("Sair");
        c.gridy = 0;
        c.gridx = 2;
        c.anchor = GridBagConstraints.CENTER;
        sair.addActionListener(gerenciador);
        painelBotoes.add(sair, c);
        
        
        this.add(BorderLayout.PAGE_START, painelTabela);
        this.add(BorderLayout.PAGE_END, painelBotoes);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void criaTabela() {
        tabela = new JTable(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Tipo");
        modelo.addColumn("Horários");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabela.setPreferredScrollableViewportSize(new Dimension(650, 200));
        this.pesquisar(modelo);
    }

    public void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (Cargo c : telaCargo.getControladorCargo().getCargos()) {
            String horarios = "";
            if(c.getHorarios() != null){
                for(int i = 0; i < c.getHorarios().size(); i = i + 2){
                    horarios = horarios + "De: " + sdf.format(c.getHorarios().get(i).getTime()) + "h";
                    horarios = horarios + " á: " + sdf.format(c.getHorarios().get(i+1).getTime()) + "h; ";
                }
            }
            else{
                horarios = "Cargo Gerencial, não possui horários.";
            }
            modelo.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getTipoCargo().getDescricao(), horarios});
            }
           
        }
    
    public class GerenciadorBotoesListagem implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == voltar){
                setVisible(false);
                telaCargo.setVisible(true);
            }
            else if(e.getSource() == sair){
                System.exit(0);
            }
        }
    
    }
    }
