package br.ufsc.ine.ine5605.trabalho2.Acesso;

import br.ufsc.ine.ine5605.trabalho2.Principal.ClassePrincipal;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.awt.im.CompositionArea;

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
    private JPanel painelAcesso, painelListagem;

    /**
     * Recebe o controlador Acesso como parametro para possibilitar a
     * comunicacao e cria um objeto da Classe TelaAcesso
     * @param controladorAcesso ControladorAcesso em execução no programa.
     */
    public TelaAcesso(ControladorAcesso controladorAcesso) {
        this.controladorAcesso = controladorAcesso;
        this.inicia();
    }

    public ControladorAcesso getControladorAcesso() {
        return this.controladorAcesso;
    }
    /**
     * Inicia a tela com o Menu das opções para o módulo de Acesso. Pode jogar exceções do tipo IllegalArgumentException e InputMismatchException.
     * @throws IllegalArgumentException Caso seja digitada uma opção inválida.
     * @throws InputMismatchException Caso seja digitado um caractere inválido.
     */
    public void inicia() {
        
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        this.painelAcesso = new JPanel(new GridBagLayout());
        this.painelListagem = new JPanel(new GridBagLayout());
        GerenciadorBotoesAcesso gerenciador = new GerenciadorBotoesAcesso();
       
        this.descricao = new JLabel("--- Clique para escolher a opcao ---");
        this.realizarA = new JButton("Realizar um acesso");
        this.listarA = new JButton("Listar Acessos Negados");
        this.voltar = new JButton("Voltar ao Menu Principal");
        
        this.realizarA.addActionListener(gerenciador);
        this.listarA.addActionListener(gerenciador);
        this.voltar.addActionListener(gerenciador);
        
        this.painelAcesso.add(this.descricao, GridBagConstraints.FIRST_LINE_START);
        this.painelAcesso.add(this.realizarA, GridBagConstraints.PAGE_START);
        this.painelAcesso.add(this.listarA, GridBagConstraints.FIRST_LINE_END);
        this.painelAcesso.add(this.voltar, GridBagConstraints.LINE_START);
        
        this.setSize(700, 100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        container.setVisible(true);
        this.painelAcesso.setVisible(true);
    }
    
  /*  
    public void inicia() throws IllegalArgumentException, InputMismatchException {
        System.out.println("--- Menu para Acesso / Controle de Acesso: ---");
        System.out.println("Escolha a opcao desejada, insira o numero e tecle enter: ---");
        System.out.println("1 - Realizar um Acesso");
        System.out.println("2 - Listar Acessos Negados");
        System.out.println("3 - Voltar ao Menu Principal");

        int opcao = teclado.nextInt();
        try {
            switch (opcao) {
                case (1):
                    this.realizarAcesso();
                    break;
                case (2):
                    this.menuAcessosNegados();
                    break;
                case (3):
                    this.getControladorAcesso().getControladorPrincipal().getTelaPrincipal().inicia();
                    break;

                default:
                    throw new IllegalArgumentException();
            }
        } 
        catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre das opções na lista.");
            String[] args = null;
            ClassePrincipal.main(args);
        }
    }
    */
    
    /**
     * Inicia a tela de listagem de acessos negados. Faz o tratamento dos dados inseridos pelo usuário e, 
     * antes da exclusão, verifica a existência do cargo utilizando se dos métodos de controladorAcesso.
     */
    private void menuAcessosNegados(){
        System.out.println("--- Menu de Listagem de Acessos Negados: ---");
        System.out.println("Escolha a opcao desejada, insira o numero e tecle enter: ---");
        System.out.println("1 - Listar todos acessos negados");
        System.out.println("2 - Listar acessos negados por matricula");
        System.out.println("3 - Listar acessos negados por motivo");
        System.out.println("4 - Voltar ao menu geral Acesso.");
        
        int opcao = 0;
        //int opcao = teclado.nextInt();
        try {
            switch (opcao) {
                case (1):
                    this.getControladorAcesso().findAcessosNegados();
                    this.menuAcessosNegados();
                    break;
                case (2):
                    System.out.println("Digite a matricula e tecle enter: ---");
                    //int matricula = teclado.nextInt();
                    int matricula = 0;
                    this.getControladorAcesso().findAcessosNegadosByMatricula(matricula);
                    this.menuAcessosNegados();
                    break;
                case (3):
                    System.out.println("Escolha o motivo, digite o respectivo numero e tecle enter: ---");
                    System.out.println("1 - OK");
                    System.out.println("2 - ATRASADO");
                    System.out.println("3 - PERMISSAO");
                    System.out.println("4 - BLOQUEADO");
                    System.out.println("5 - OUTRO");

                    int opcaoMotivo = 0;
                   // int opcaoMotivo = teclado.nextInt();
                    MotivoAcesso motivo = MotivoAcesso.OK;
                    try {
                        switch (opcaoMotivo) {
                            case (1):
                                motivo = MotivoAcesso.OK;
                                break;
                            case (2):
                                motivo = MotivoAcesso.ATRASADO;
                                break;
                            case (3):
                                motivo = MotivoAcesso.PERMISSAO;
                                break;
                            case (4):
                                motivo = MotivoAcesso.BLOQUEADO;
                                break;
                            case (5):
                                motivo = MotivoAcesso.OUTRO;
                                break;
                            default:
                                throw new IllegalArgumentException("Motivo não cadastrado. Você deve digitar opções válidas. Selecione um motivo dentre os motivos listados.");
                        }
                    } 
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        this.inicia();
                    }
                    this.getControladorAcesso().findAcessosNegadosByMotivo(motivo);
                    this.menuAcessosNegados();
                    break;
                case (4):
                    this.inicia();
                    break;
                default:
                    throw new IllegalArgumentException("Opcao Invalida! Escolha uma opcao dentre das opcoes na lista.");
            }
        } 
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }
    }

     /**
     * Inicia a tela de acesso ao sistema. Faz o tratamento dos dados inseridos pelo usuário e, 
     * realiza o acesso utilizando-se dos métodos de controladorAcesso.
     */
    private void realizarAcesso() {
		System.out.println("Digite a hora atual. Ex.: 22");
                int hora = 0;
		//int hora = teclado.nextInt();
                        
		System.out.println("Digite o minuto atual. Ex.: 53");
                int minuto = 0;
		//int minuto = teclado.nextInt();
                
        System.out.println("Agora digite a matricula na qual deseja efetuar o acesso");
        int matricula = 0;
        //int matricula = teclado.nextInt();
        
        if (this.getControladorAcesso().getControladorPrincipal().getControladorFuncionario().validaMatricula(matricula)) {
            Acesso acesso = this.getControladorAcesso().verificaAcesso(matricula, hora, minuto);
            switch (acesso.getMotivo()) {
                case OK:
                    System.out.println("Acesso realizado com sucesso.");
                    this.inicia();
                    break;
                case ATRASADO:
                    System.out.println("Acesso negado, atrasado.");
                    this.inicia();
                    break;
                case PERMISSAO:
                    System.out.println("Acesso negado, sem permissao.");
                    this.inicia();
                    break;
                case BLOQUEADO:
                    System.out.println("Acesso negado, bloqueado.");
                    this.inicia();
                    break;
                case OUTRO:
                    System.out.println("Acesso negado, motivo nao cadastrado. Consulte o administrador do sistema.");
                    this.inicia();
                    break;
            }
        } 
        else {
            System.out.println("Matricula nao encontrada.");
            this.realizarAcesso();
        }
    }

    private class GerenciadorBotoesAcesso implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == realizarA){
                setVisible(false);
                realizarAcesso();
            }
            else if(e.getSource() == listarA){
                setVisible(false);
                menuAcessosNegados();
                
            }
            else if(e.getSource() == voltar){
                setVisible(false);
                inicia();
            }
        }
    }

}
