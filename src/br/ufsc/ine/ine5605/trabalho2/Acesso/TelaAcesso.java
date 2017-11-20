package br.ufsc.ine.ine5605.trabalho2.Acesso;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaAcesso extends JFrame {

    private final ControladorAcesso controladorAcesso;
    private JButton realizarA, listarA, voltar;
    private JLabel descricao;
    private TelaRealizarAcesso telaRealizarAcesso;
    private TelaListagemAcessosNegados telaListagemAcessosNegados;
    Font fonte = new Font("Tahoma", Font.BOLD, 14);

    /**
     * Recebe o controlador Acesso como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaAcesso
     * @param controladorAcesso ControladorAcesso em execução no programa.
     */
    public TelaAcesso(ControladorAcesso controladorAcesso) {
        super("Tela de Manutenção de Acessos");
        this.controladorAcesso = controladorAcesso;
        this.telaRealizarAcesso = new TelaRealizarAcesso(this);
        this.telaListagemAcessosNegados = new TelaListagemAcessosNegados(this);
        this.inicializarComponentes();
    }

    public ControladorAcesso getControladorAcesso() {
        return this.controladorAcesso;
    }
    /**
     * Inicia a tela com o Menu das opções para o módulo de Acesso. Pode jogar exceções do tipo IllegalArgumentException e InputMismatchException.
     * @throws IllegalArgumentException Caso seja digitada uma opção inválida.
     * @throws InputMismatchException Caso seja digitado um caractere inválido.
     */
    public void inicializarComponentes() {
        Dimension dimensaoBotoes = new Dimension(300, 70);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        
        GerenciadorBotoesAcesso gerenciador = new GerenciadorBotoesAcesso();
        GridBagConstraints c = new GridBagConstraints();
        
        this.descricao = new JLabel("Clique em uma das opções:");
        this.realizarA = new JButton("Realizar um Acesso");
        this.listarA = new JButton("Listar Acessos Negados");
        this.voltar = new JButton("Voltar ao Menu Principal");
        
        
        this.realizarA.addActionListener(gerenciador);
        this.listarA.addActionListener(gerenciador);
        this.voltar.addActionListener(gerenciador);
       
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,0,0,0);
        container.add(this.descricao, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        realizarA.setFont(fonte);
        realizarA.setPreferredSize(dimensaoBotoes);        
        container.add(this.realizarA, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        listarA.setFont(fonte);
        listarA.setPreferredSize(dimensaoBotoes);        
        container.add(this.listarA, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        voltar.setFont(fonte);
        voltar.setPreferredSize(dimensaoBotoes);        
        container.add(this.voltar, c);
        
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private class GerenciadorBotoesAcesso implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == realizarA){
                setVisible(false);
                telaRealizarAcesso.updateData();
                telaRealizarAcesso.setVisible(true);
            }
            else if(e.getSource() == listarA){
                setVisible(false);
                //telaListagemAcessosNegados.updateData();
                telaListagemAcessosNegados.setVisible(true);
                
            }
            else if(e.getSource() == voltar){
                setVisible(false);
                controladorAcesso.getControladorPrincipal().getTelaPrincipal().setVisible(true);
            }
        }
    }
}
