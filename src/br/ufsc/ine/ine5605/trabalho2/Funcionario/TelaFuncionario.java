package br.ufsc.ine.ine5605.trabalho2.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaFuncionario extends JFrame {
    private final ControladorFuncionario controladorFuncionario;
    private JLabel descricao;
    private JButton botaoCadastrar, botaoExcluir, botaoAlterar, botaoListar, botaoVoltar;
    private TelaCadastroFuncionario telaCadastroFuncionario;
    private TelaExclusaoFuncionario telaExclusaoFuncionario;
    private TelaAlteracaoFuncionario telaAlteracaoFuncionario;
    private TelaListagemFuncionario telaListagemFuncionario;
    Dimension dimensao = new Dimension(300, 70);
    Font fonte = new Font("Tahoma", Font.BOLD, 14);


    public TelaFuncionario(ControladorFuncionario controladorFuncionario){
        super("Tela de Manutenção de Funcionários");
        this.controladorFuncionario = controladorFuncionario;
        inicializarComponentesFuncionario();
        this.telaCadastroFuncionario = new TelaCadastroFuncionario(this);
        this.telaListagemFuncionario = new TelaListagemFuncionario(this);
        this.telaExclusaoFuncionario = new TelaExclusaoFuncionario(this);
        this.telaAlteracaoFuncionario = new TelaAlteracaoFuncionario(this);

    }

    public ControladorFuncionario getControladorFuncionario() {
        return controladorFuncionario;
    }
    
    /**
     * Configura os botões e descrição do menu de Funcionários
     */
    private void inicializarComponentesFuncionario(){
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesFuncionario gerenciador = new GerenciadorBotoesFuncionario();
        
       
        descricao =  new JLabel("Clique em uma das opções: ");
        botaoCadastrar = new JButton("Cadastrar");
        botaoExcluir = new JButton("Excluir");
        botaoAlterar = new JButton("Alterar");
        botaoListar = new JButton("Listar");
        botaoVoltar = new JButton("Voltar ao Menu Principal");
              
        
        botaoCadastrar.addActionListener(gerenciador);
        botaoExcluir.addActionListener(gerenciador);
        botaoAlterar.addActionListener(gerenciador);
        botaoListar.addActionListener(gerenciador);
        botaoVoltar.addActionListener(gerenciador);
       
        
        c.insets = new Insets(10,0,0,0);

        c.gridx = 0;
        c.gridy = 0;
        container.add(descricao,c);
        
        c.gridx = 0;
        c.gridy = 1;
        botaoCadastrar.setFont(fonte);
        botaoCadastrar.setPreferredSize(dimensao);
        container.add(botaoCadastrar,c);
        
        c.gridx = 0;
        c.gridy = 2;
        botaoAlterar.setFont(fonte);
        botaoAlterar.setPreferredSize(dimensao);
        container.add(botaoAlterar,c);
        
        c.gridx = 0;
        c.gridy = 3;
        botaoExcluir.setFont(fonte);
        botaoExcluir.setPreferredSize(dimensao);
        container.add(botaoExcluir,c);
        
        c.gridx = 0;
        c.gridy = 4;
        botaoListar.setFont(fonte);
        botaoListar.setPreferredSize(dimensao);
        container.add(botaoListar,c);
        
        c.gridx = 0;
        c.gridy = 5;
        botaoVoltar.setFont(fonte);
        botaoVoltar.setPreferredSize(dimensao);
        container.add(botaoVoltar,c);
        
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
        /**
         * Aponta ações ao clicar em cada um dos botões
         */
      
        private class GerenciadorBotoesFuncionario implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == botaoCadastrar){
                setVisible(true);
                telaCadastroFuncionario.updateData();
                telaCadastroFuncionario.setVisible(true);
            }
            else if(e.getSource() == botaoExcluir){
                setVisible(true);
                telaExclusaoFuncionario.updateData(telaExclusaoFuncionario.modelo);
                telaExclusaoFuncionario.setVisible(true);
            }
            else if(e.getSource() == botaoAlterar){
                setVisible(true);
                telaAlteracaoFuncionario.updateData(telaAlteracaoFuncionario.modelo);
                telaAlteracaoFuncionario.setVisible(true);
            }
            else if(e.getSource() == botaoListar){
                setVisible(true);
                telaListagemFuncionario.pesquisar(telaListagemFuncionario.modelo);
                telaListagemFuncionario.setVisible(true);
            }
            else if(e.getSource() == botaoVoltar){
                setVisible(false);
                getControladorFuncionario().getControladorPrincipal().getTelaPrincipal().setVisible(true);
            }
        }
        
    }
}

