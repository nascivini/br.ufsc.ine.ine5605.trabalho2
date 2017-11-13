package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.CardLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private TelaCadastroCargo telaCadastroCargo;
    
    public TelaCargo(ControladorCargo controladorCargo) {
        super("Tela de Manutenção de Cargos");
        this.controladorCargo = controladorCargo;
        this.telaCadastroCargo = new TelaCadastroCargo(this);
        this.inicializarComponentes();
    }
    
    /**
     * Inicializa todos os componentes da Tela e Subtelas do módulo Cargo. É utilizado apenas pelo construtor da classe TelaCargo.
     * Também "mostra" a tela pela primeira vez.
     */
    private void inicializarComponentes(){
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GerenciadorBotoesCargo gerenciador = new GerenciadorBotoesCargo();
        GridBagConstraints c = new GridBagConstraints(); 
                
        this.botaoCadastro = new JButton("Cadastrar um Cargo");
        this.botaoExclusao = new JButton("Excluir um Cargo");
        this.botaoAlteracao = new JButton("Alterar um Cargo");
        this.botaoListagem = new JButton("Listar Cargos Cadastrados");
        this.botaoVoltar = new JButton("Voltar ao Menu Principal");
        this.descricaoPrincipal = new JLabel("Clique nos botões para acessar os módulos.");
                  
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
        container.add(botaoCadastro, c);
        
        c.gridx = 0;
        c.gridy = 4;        
        container.add(botaoExclusao, c);
        
        c.gridx = 0;
        c.gridy = 6;        
        container.add(botaoAlteracao, c);
        
        c.gridx = 0;
        c.gridy = 8;        
        container.add(botaoListagem, c);
        
        c.gridx = 0;
        c.gridy = 10;        
        container.add(botaoVoltar, c);
        
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);   
    }
    
    public ControladorCargo getControladorCargo() {
        return controladorCargo;
    }

    /**
     * Inicia o módulo de cadastro de Cargos no sistema. Faz o tratamento dos
     * dados inseridos e efetiva o cadastro de um cargo, se utilizando do
     * controladorCargo(atributo). Utiliza os métodos incluirCargo,
     * geraSequencialCargo, reduzSequencialCargo e findCargoByNome do
     * controladorCargo.
     */
    private void inicializarComponentesCadastro() {
        
        this.painelCadastro.setToolTipText("Cadastro de Cargos");
        this.painelCadastro = new JPanel(new GridLayout(4, 2));
        
        this.nomeCadastro = new JTextField();
        this.codigoCadastroCargo = new JLabel("Código do novo cargo(gerado automaticamente):" + this.getControladorCargo().geraSequencialCargo()+ ".");
        this.horario1 = new JTextField();
        this.horario2 = new JTextField();
        this.horario3 = new JTextField();
        this.botaoConfirmarCadastro = new JButton("Cadastrar");
        this.botaoLimparCadastro = new JButton("Limpar Dados");
        
        this.painelCadastro.add(nomeCadastro, 1, 0);
        this.painelCadastro.add(horario1, 2, 0);
        this.painelCadastro.add(horario2, 2, 1);
        this.painelCadastro.add(horario3, 2, 2);
        this.painelCadastro.add(botaoConfirmarCadastro, 4, 1);
        this.painelCadastro.add(botaoLimparCadastro, 4, 2);

        try {
            if (this.getControladorCargo().findCargoByNome(nome)) {
                this.getControladorCargo().reduzSequencialCargo();
                throw new IllegalArgumentException("Já existe um cargo com este nome no sistema. O cargo não foi cadastrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }

        System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
        System.out.println("1 - Gerencial");
        System.out.println("2 - Comum");
        System.out.println("3 - Convidado");

        int opcaoCargo = teclado.nextInt();

        boolean ehGerencial = false;
        String tipoCargo = "";
        boolean tipo = false;
        try {
            switch (opcaoCargo) {
                case (1):
                    tipo = true;
                    tipoCargo = "GERENCIAL";
                    ehGerencial = true;
                    break;
                case (2):
                    tipoCargo = "COMUM";
                    tipo = true;
                    break;
                case (3):
                    tipo = false;
                    tipoCargo = "CONVIDADO";
                    break;
                default:
                    throw new IllegalArgumentException("Cargo não cadastrado. Você deve digitar opções válidas. Selecione um tipo dentre os tipos listados.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }

        ArrayList<Calendar> horarios = new ArrayList<>();

        switch (tipoCargo) {
            case "GERENCIAL": {
                DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
            }
            case "CONVIDADO": {
                DadosCargo cargoNovo = new DadosCargo(nome, tipo, false, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
            }
            default:
                boolean continuaCadastro = true;

                while (continuaCadastro) {

                    if (horarios.isEmpty()) {
                        
                        Calendar horario1 = Calendar.getInstance();
                        Calendar horario2 = Calendar.getInstance();                        
                        teclado.nextLine();
                        boolean cadastroInvalido = true;
                        while(cadastroInvalido){    
                            System.out.println("Digite o horário inicial em que o acesso é permitido.(Hora)");
                            int horaInicial = teclado.nextInt();
                            System.out.println("Digite o minuto inicial.");
                            int minInicial = teclado.nextInt();

                            try {
                                if (horaInicial >= 0 && horaInicial <= 23 && minInicial >= 0 && minInicial <= 59) {
                                    horario1.set(0, 0, 0, horaInicial, minInicial);
                                    cadastroInvalido = false;
                            }   
                                else {
                                    this.getControladorCargo().reduzSequencialCargo();
                                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                                }
                            }   
                            catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                cadastroInvalido = true;
                            }
                        }        
                        
                        boolean cadastro2Invalido = true;
                        while(cadastro2Invalido){
                            System.out.println("Digite o horário final em que o acesso é permitido.(Hora)");
                            int horaFinal = teclado.nextInt();
                            System.out.println("Digite o minuto final.");
                            int minFinal = teclado.nextInt();
                            try {
                                if (horaFinal >= 0 && horaFinal <= 23 && minFinal >= 0 && minFinal <= 59) {
                                    horario2.set(0, 0, 0, horaFinal, minFinal);
                                    cadastro2Invalido = false;
                                }   
                                else {
                                    this.getControladorCargo().reduzSequencialCargo();
                                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                cadastro2Invalido = true;
                            }
                        }
                        try {
                            if (this.getControladorCargo().verificaHorarios(horarios, horario1, horario2)) {
                                horarios.add(horario1);
                                horarios.add(horario2);
                                System.out.println("Horários cadastrados com sucesso!");
                            } else {
                                throw new IllegalArgumentException("Horários são iguais!! Cadastro não realizado.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            this.inicia();
                        }

                        teclado.nextLine();

                        System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } else {
                            continuaCadastro = false;
                        }

                    } else {
                        Calendar horario3 = Calendar.getInstance();
                        Calendar horario4 = Calendar.getInstance();
                        
                        boolean cadastroInvalido = true;
                        while(cadastroInvalido){
                            System.out.println("Digite o horário inicial em que o acesso é permitido.(Hora)");
                            int horaInicial = teclado.nextInt();
                            System.out.println("Digite os minutos do horário inicial.");
                            int minInicial = teclado.nextInt();
                            teclado.nextLine();

                            try {
                                if (horaInicial >= 0 && horaInicial <= 23 && minInicial >= 0 && minInicial <= 59) {
                                    horario3.set(0, 0, 0, horaInicial, minInicial);
                                    cadastroInvalido = false;
                                } 
                                else {
                                    throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                cadastroInvalido = true;
                            }
                        }
                        
                        boolean cadastro2Invalido = true;
                        while(cadastro2Invalido){
                            System.out.println("Digite o horário final em que o acesso é permitido.(Hora)");
                            int horaFinal = teclado.nextInt();
                            System.out.println("Digite os minutos do horário final.");
                            int minFinal = teclado.nextInt();
                            teclado.nextLine();

                            try {
                                if (horaFinal >= 0 && horaFinal <= 23 && minFinal >= 0 && minFinal <= 59) {
                                    horario4.set(0, 0, 0, horaFinal, minFinal);
                                    cadastro2Invalido = false;
                                } 
                                else {
                                    throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                cadastro2Invalido = true;
                            }
                        }    
                        
                        try {
                            this.getControladorCargo().verificaHorarios(horarios, horario3, horario4);
                            horarios.add(horario3);
                            horarios.add(horario4);
                            System.out.println("Horarios cadastrados com sucesso!");
                        }   
                        catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        
                        System.out.println("Deseja continuar o cadastro de horários? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } else {
                            continuaCadastro = false;
                        }

                    }
                }

                DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
        }
        this.inicia();
    }

    /**
     * Inicia a tela de exclusão de cargos, faz o tratamento dos dados inseridos
     * pelo usuário e, antes da exclusão, verifica a existência do cargo
     * utilizando se do método findCargoByCodigo, do controladorCargo. Utiliza o
     * método excluirCargo, também do controladorCargo.
     */
    private void exclusaoCargos() {
        System.out.println("Para excluir um cargo do sistema, digite o código do mesmo.");
        int codigo = teclado.nextInt();

        if (this.getControladorCargo().findCargoByCodigo(codigo) != null) {
            this.getControladorCargo().excluirCargo(codigo);
            System.out.println("Cargo excluído com sucesso!");
            this.inicia();
        } else {
            System.out.println("O código informado não pertence a nenhum cargo cadastrado.");
            System.out.println("Deseja tentar novamente? Digite Y ou N");
            String opcaoExclusao = teclado.nextLine();
            if (opcaoExclusao.equals("Y") || opcaoExclusao.equals("y")) {
                this.exclusaoCargos();
            } else {
                this.inicia();
            }
        }
    }

    /**
     * Inicia a tela de alteração de cargos. Permite apenas a alteração de um
     * dado do cargo por vez. Utiliza se dos métodos findCargoByCodigo,
     * findcargoByNome e alterarCargo do controladorCargo.
     */
    private void alteracaoCargos() {
        System.out.println("Bem vindo à tela de alteração dos Cargos.");
        System.out.println("Só é possível alterar um dado por vez. Digite o código do cargo a ser alterado, e selecione qual dado deseja alterar. Horários não podem ser alterados, caso deseje realizar alterações, exclua o cargo e realize o cadastro novamente.");

        int codigo = teclado.nextInt();
        if (this.getControladorCargo().findCargoByCodigo(codigo) == null) {
            System.out.println("Cargo não encontrado. Digite um código válido.");
            this.alteracaoCargos();
        } else {
            System.out.println("---------------------------------------------------------");
            System.out.println("1 - Alterar nome do cargo.");
            System.out.println("2 - Alterar tipo do cargo.");
            System.out.println("3 - Alterar permissão de acesso do cargo");

            int opcao = teclado.nextInt();
            teclado.nextLine();
            
            try{
            switch (opcao) {
               
                case (1):
                    System.out.println("Digite o novo nome para o cargo. Não é possível cadastrar dois cargos com o mesmo nome no sistema.");
                    String novoNome = teclado.nextLine();
                    if (!this.getControladorCargo().findCargoByNome(novoNome)) {
                        DadosCargo novosDados = new DadosCargo();
                        novosDados.nome = novoNome;
                        this.getControladorCargo().alterarCargo(novosDados, codigo);
                        System.out.println("Nome alterado com sucesso!");
                        this.inicia();
                        break;
                    } else {
                        throw new IllegalArgumentException("Nome inválido! Já existe um cargo cadastrado com este nome no sistema. Verifique o nome e tente novamente.");
                    }
                case (2):
                    switch (this.getControladorCargo().findCargoByCodigo(codigo).getTipoCargo().getDescricao()) {
                        case "Cargo Gerencial":
                            System.out.println("Este cargo é gerencial. Cargos gerenciais não podem ser alterados. Se deseja alterar os dados de um cargo gerencial, faça a exclusão do mesmo e o cadastre novamente.");
                            this.inicia();
                            break;
                        case "Cargo Comum":
                            System.out.println("Este cargo é comum.");
                            break;
                        case "Cargo Convidado":
                            System.out.println("Este cargo é convidado.");
                            break;
                        default:
                            break;
                    }
                    System.out.println("Selecione um dos tipos de cargo abaixo para alterá-lo.");
                    System.out.println("1 - Gerencial");
                    System.out.println("2 - Comum");
                    System.out.println("3 - Convidado");
                    
                    int opcaoTipo = teclado.nextInt();
                    
                    DadosCargo dados = new DadosCargo();
                    switch (opcaoTipo){
                        case 1:
                            dados.tipoCargo = TipoCargo.GERENCIAL;
                            dados.horarios = null;
                            this.getControladorCargo().alterarCargo(dados, codigo);
                            System.out.println("Tipo Alterado com Sucesso!");
                            this.inicia();
                            break;
                        case 2:
                            dados.tipoCargo = TipoCargo.COMUM;
                            this.getControladorCargo().alterarCargo(dados, codigo);
                            System.out.println("Tipo Alterado com Sucesso!");
                            this.inicia();
                            break;
                        case 3:   
                            dados.tipoCargo = TipoCargo.CONVIDADO;
                            this.getControladorCargo().alterarCargo(dados, codigo);
                            System.out.println("Tipo Alterado com Sucesso!");
                            this.inicia();
                            break;
                            
                        default:
                            throw new IllegalArgumentException("Opção inválida! Cargo não alterado.");
                    }

                case (3):
                    if (this.getControladorCargo().findCargoByCodigo(codigo).isEhGerencial()) {
                        System.out.println("Este cargo é gerencial. Cargos gerenciais possuem acesso sem restrição à porta do financeiro. Só é possível revogar permissões de acesso para os demais tipos de cargo.");
                        this.inicia();
                    }
                    else{
                        if(this.getControladorCargo().findCargoByCodigo(codigo).isPermiteAcesso()){
                            System.out.println("Este cargo possui acesso. Deseja revogar suas permissões? Digite Y caso sim e qualquer caractere caso não.");
                            String continuar = teclado.nextLine();
                            
                            if(continuar.equals("Y") || continuar.equals("Y")){
                                dados = new DadosCargo();
                                dados.permiteAcesso = false;
                                this.getControladorCargo().alterarCargo(dados, codigo);
                                System.out.println("Permissão revogada!");
                                this.inicia();
                            }
                        }
                        
                        else{
                            System.out.println("Este cargo não possui acesso. Deseja permitir? Digite Y caso sim e qualquer caractere caso não.");
                            String continuar = teclado.nextLine();
                            
                            if(continuar.equals("Y") || continuar.equals("Y")){
                                dados = new DadosCargo();
                                dados.permiteAcesso = true;
                                this.getControladorCargo().alterarCargo(dados, codigo);
                                System.out.println("Permissão concedida!");
                                this.inicia();
                            }                            
                        }
                    }
            }
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                this.inicia();
            }
        }
    }


    /**
     * Chama o método listarCargos do controladorCargo para listar os cargos já
     * cadastrados no sistema.
     */
    private void listarCargos() {
        this.getControladorCargo().listarCargos();
        this.inicia();
    }
    
    private class GerenciadorBotoesCargo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == botaoCadastro){
                setVisible(false);
                telaCadastroCargo.setVisible(true);
            }
            else if(e.getSource() == botaoExclusao){
                setVisible(false);
                telaExclusaoCargo.setVisible(true);
            }
            else if(e.getSource() == botaoAlteracao){
                setVisible(false);
                telaAlteracaoCargo.setVisible(true);
            }
            else if(e.getSource() == botaoListagem){
                setVisible(false);
                telaListagemCargo.setVisible(true);
            }
            else if(e.getSource() == botaoVoltar){
                setVisible(false);
            }
        }
        
    }
    
}
