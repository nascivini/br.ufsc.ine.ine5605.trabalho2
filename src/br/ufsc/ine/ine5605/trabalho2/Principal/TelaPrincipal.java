package br.ufsc.ine.ine5605.trabalho2.Principal;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaPrincipal extends JFrame {
    private final ControladorPrincipal controladorPrincipal;
    private JLabel descricao;
    private JButton botaoCargo;
    private JButton botaoFuncionario;
    private JButton botaoAcesso;
    private JButton sair;
    
    /**
     * Recebe o controladorPrincipal como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaPrincipal
     * @param controladorPrincipal controladorPrincipal em execução no programa
     */
    public TelaPrincipal(ControladorPrincipal controladorPrincipal) {
        super("Sistema de Acesso à Porta do Financeiro");
        this.controladorPrincipal = controladorPrincipal;
        this.inicializarComponentes();
    }
    
    private void inicializarComponentes(){
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        GerenciadorBotoesPrincipal gerenciador = new GerenciadorBotoesPrincipal();
        
        descricao =  new JLabel();
        botaoCargo = new JButton();
        botaoFuncionario = new JButton();
        botaoAcesso = new JButton();
        sair = new JButton();
        
        descricao.setText("Clique nos botões para acessar os módulos.");
        botaoCargo.setText("Cargos");
        botaoFuncionario.setText("Funcionarios");
        botaoAcesso.setText("Acessos");
        sair.setText("Sair do Sistema");
        
        botaoCargo.addActionListener(gerenciador);
        botaoFuncionario.addActionListener(gerenciador);
        botaoAcesso.addActionListener(gerenciador);
        sair.addActionListener(gerenciador);
        
        container.add(descricao);
        container.add(botaoCargo);
        container.add(botaoFuncionario);
        container.add(botaoAcesso);
        container.add(sair);
        
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        this.setVisible(true);
        this.setSize(700, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public ControladorPrincipal getControladorPrincipal(){
        return this.controladorPrincipal;
    }
    
    public class GerenciadorBotoesPrincipal implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botaoCargo){
                controladorPrincipal.getControladorCargo().getTelaCargo().setVisible(true);
            }
            else if(e.getSource() == botaoFuncionario){
                controladorPrincipal.getControladorFuncionario().getTelaFuncionario().setVisible(true);
            }
            else if(e.getSource() == botaoAcesso){
                controladorPrincipal.getControladorAcesso().getTelaAcesso().setVisible(true);
            }
            else if(e.getSource() == sair){
                System.exit(0);
            }
        }
    }    
 }
