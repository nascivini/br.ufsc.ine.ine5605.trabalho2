package br.ufsc.ine.ine5605.trabalho2.Principal;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
    Dimension d = new Dimension(300, 70);
    Font fonte = new Font("Tahoma", Font.BOLD, 14);
    
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
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesPrincipal gerenciador = new GerenciadorBotoesPrincipal();
        
        
        descricao =  new JLabel();
        botaoCargo = new JButton();
        botaoFuncionario = new JButton();
        botaoAcesso = new JButton();
        sair = new JButton();
        
        descricao.setText("Clique nos botões para acessar os módulos:");
        botaoCargo.setText("Cargos");
        botaoFuncionario.setText("Funcionários");
        botaoAcesso.setText("Acessos");
        sair.setText("Sair do Sistema");
        
        botaoCargo.addActionListener(gerenciador);
        botaoFuncionario.addActionListener(gerenciador);
        botaoAcesso.addActionListener(gerenciador);
        sair.addActionListener(gerenciador);
        
        botaoCargo.setPreferredSize(d);
        botaoFuncionario.setPreferredSize(d);
        botaoAcesso.setPreferredSize(d);
        sair.setPreferredSize(d);
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, WIDTH, WIDTH, WIDTH);
        container.add(descricao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        botaoCargo.setFont(fonte);
        container.add(botaoCargo, c);
        
        c.gridx = 0;
        c.gridy = 2;
        botaoFuncionario.setFont(fonte);
        container.add(botaoFuncionario, c);
        
        c.gridx = 0;
        c.gridy = 3;
        botaoAcesso.setFont(fonte);
        container.add(botaoAcesso, c);
        
        c.gridx = 0;
        c.gridy = 4;
        sair.setFont(fonte);
        container.add(sair, c);
        
        this.setVisible(true);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public ControladorPrincipal getControladorPrincipal(){
        return this.controladorPrincipal;
    }
    
    public class GerenciadorBotoesPrincipal implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botaoCargo){
                setVisible(true);
                controladorPrincipal.getControladorCargo().getTelaCargo().setVisible(true);
            }
            else if(e.getSource() == botaoFuncionario){
                setVisible(true);
                controladorPrincipal.getControladorFuncionario().getTelaFuncionario().setVisible(true);
            }
            else if(e.getSource() == botaoAcesso){
                setVisible(true);
                controladorPrincipal.getControladorAcesso().getTelaAcesso().setVisible(true);
            }
            else if(e.getSource() == sair){
                System.exit(0);
            }
        }
    }    
 }
