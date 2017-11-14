/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import javax.swing.JFrame;

/**
 *
 * @author Viinicius
 */
public class TelaExclusaoCargo extends JFrame {
    private final TelaCargo telaCargo;
    
    public TelaExclusaoCargo(TelaCargo telaCargo){
        super("Tela de Exclusão de Cargos");
        this.telaCargo = telaCargo;
    }
    
     /**
     * Inicia a tela de exclusão de cargos, faz o tratamento dos dados inseridos
     * pelo usuário e, antes da exclusão, verifica a existência do cargo
     * utilizando se do método findCargoByCodigo, do controladorCargo. Utiliza o
     * método excluirCargo, também do controladorCargo.
     */
    private void inicializarComponentes() {
        /*
         * System.out.println("Para excluir um cargo do sistema, digite o código do mesmo.");
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
        */
    }
}
