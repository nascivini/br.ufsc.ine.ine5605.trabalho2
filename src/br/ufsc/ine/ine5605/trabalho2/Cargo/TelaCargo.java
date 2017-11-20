package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class TelaCargo extends JFrame{

    private final ControladorCargo controladorCargo;
    private JLabel descricaoPrincipal;
    private JButton botaoCadastro, botaoExclusao, botaoAlteracao, botaoListagem, botaoVoltar;
    private final TelaCadastroCargo telaCadastroCargo;
    private final TelaExclusaoCargo telaExclusaoCargo;
    private final TelaListagemCargo telaListagemCargo;
    private final TelaAlteracaoCargo telaAlteracaoCargo;
    Font fonte = new Font("Tahoma", Font.BOLD, 14);
    
    public TelaCargo(ControladorCargo controladorCargo) {
        super("Tela de Manutenção de Cargos");
        this.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        this.controladorCargo = controladorCargo;
        this.telaCadastroCargo = new TelaCadastroCargo(this);
        this.telaExclusaoCargo = new TelaExclusaoCargo(this);
        this.telaListagemCargo = new TelaListagemCargo(this);
        this.telaAlteracaoCargo = new TelaAlteracaoCargo(this);
        this.inicializarComponentes();
    }
    
    /**
     * Inicializa todos os componentes da Tela e Subtelas do módulo Cargo. É utilizado apenas pelo construtor da classe TelaCargo.
     * Também "mostra" a tela pela primeira vez.
     */
    private void inicializarComponentes(){
        Dimension dimensao = new Dimension(300, 70);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GerenciadorBotoesCargo gerenciador = new GerenciadorBotoesCargo();
        GridBagConstraints c = new GridBagConstraints(); 
                
        this.botaoCadastro = new JButton("Cadastrar um Cargo");
        this.botaoExclusao = new JButton("Excluir um Cargo");
        this.botaoAlteracao = new JButton("Alterar um Cargo");
        this.botaoListagem = new JButton("Listar Cargos Cadastrados");
        this.botaoVoltar = new JButton("Voltar ao Menu Principal");
        this.descricaoPrincipal = new JLabel("Clique em uma das opções:");
                  
        botaoCadastro.addActionListener(gerenciador);
        botaoExclusao.addActionListener(gerenciador);
        botaoAlteracao.addActionListener(gerenciador);
        botaoListagem.addActionListener(gerenciador);
        botaoVoltar.addActionListener(gerenciador);
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 0, 0, 0);
        container.add(descricaoPrincipal, c);
        
        c.gridx = 0;
        c.gridy = 2;
        botaoCadastro.setFont(fonte);
        botaoCadastro.setPreferredSize(dimensao);
        container.add(botaoCadastro, c);
        
        c.gridx = 0;
        c.gridy = 4;
        botaoExclusao.setFont(fonte);
        botaoExclusao.setPreferredSize(dimensao);
        container.add(botaoExclusao, c);
        
        c.gridx = 0;
        c.gridy = 6;
        botaoAlteracao.setFont(fonte);
        botaoAlteracao.setPreferredSize(dimensao);
        container.add(botaoAlteracao, c);
        
        c.gridx = 0;
        c.gridy = 8;
        botaoListagem.setFont(fonte);
        botaoListagem.setPreferredSize(dimensao);
        container.add(botaoListagem, c);
        
        c.gridx = 0;
        c.gridy = 10;
        botaoVoltar.setFont(fonte);
        botaoVoltar.setPreferredSize(dimensao);
        container.add(botaoVoltar, c);
        
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
    }
    
    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }
  
    private class GerenciadorBotoesCargo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == botaoCadastro){
                setVisible(false);
                telaCadastroCargo.updateData();
                telaCadastroCargo.setVisible(true);
            }
            else if(e.getSource() == botaoExclusao){
                setVisible(false);
                telaExclusaoCargo.updateData();
                telaExclusaoCargo.setVisible(true);
            }
            else if(e.getSource() == botaoAlteracao){
                setVisible(false);
                telaAlteracaoCargo.updateData(telaAlteracaoCargo.modelo);
                telaAlteracaoCargo.setVisible(true);
            }
            else if(e.getSource() == botaoListagem){
                setVisible(false);
                telaListagemCargo.updateData();
                telaListagemCargo.setVisible(true);
            }
            else if(e.getSource() == botaoVoltar){
                setVisible(false);
                getControladorCargo().getControladorPrincipal().getTelaPrincipal().setVisible(true);
            }
        }
        
    }
    
}
