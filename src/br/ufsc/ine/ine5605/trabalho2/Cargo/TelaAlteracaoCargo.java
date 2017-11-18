/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Viinicius
 */
public class TelaAlteracaoCargo extends JFrame{
    private TelaCargo telaCargo;
    private JTable tabelaCargos;
    private JScrollPane barraDeRolagem;
    private JButton listarTodos;
    private JTextField campoDeBusca;
    private JButton buscar;
    
    public TelaAlteracaoCargo(TelaCargo telaCargo){
        super("Tela de Alteração de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
    }
    
    private void inicializarComponentes(){
        Container container = this.getContentPane();
        
        this.tabelaCargos = new JTable();
        this.barraDeRolagem = new JScrollPane();
        this.buscar = new JButton("Buscar");
        this.campoDeBusca = new JTextField();
        this.listarTodos = new JButton("Listar Todos os Cargos");
        
        //container.add
    }
    
    private void updateData(){
        
    }
    
    /**
    private void inicializarComponentes(){
            
     * Inicia a tela de alteração de cargos. Permite apenas a alteração de um
     * dado do cargo por vez. Utiliza se dos métodos findCargoByCodigo,
     * findcargoByNome e alterarCargo do controladorCargo.
     */
       /*     
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
    */
    }
}
