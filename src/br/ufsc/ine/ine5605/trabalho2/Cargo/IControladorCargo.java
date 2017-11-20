package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public interface IControladorCargo {
    
    /**
     * Recebe os dados do cargo a ser incluído via parâmetro, inclui o cargo na
     * lista de cargos, e retorna o cargo incluído.
     * @param conteudo Conteúdo do Cargo (dados principais).
     * @param codigo Código do cargo (gerado automaticamente).
     * @return Cargo Cargo incluído.
     */
    public Cargo incluirCargo(DadosCargo conteudo);

    /**
     * Exclui o cargo da lista de cargos com base no código informado via
     * parâmetro.
     * @return True or false indicando se o cargo foi exclu[ido ou não.
     */
    public boolean excluirCargo(Cargo cargo) throws IllegalArgumentException;

    /**
     * "Varre" a lista de cargos cadastrados, buscando por um cargo que contenha
     * o nome passado como parâmetro. Retorna um Cargo nulo, caso não o
     * encontre, e o Cargo encontrado, caso o encontre.
     * @param nome Nome do cargo a ser buscado.
     * @return boolean Falso, caso não encontre, e verdadeiro, caso encontre.
     */
    public Cargo findCargoByNome(String nome);
    
    
    /**
     * Realiza a verificação de horários. Varre o ArrayList recebido como parâmetro, realizando comparações com o intervalo de horário recebido também como parâmetro, impedindo o cadastro de faixas de horário já existentes.
     * @param horarios ArrayList com os horários já cadastrados
     * @param horario1 horárioInicial da faixa de horário a ser verificado
     * @param horario2 horarioFinal  da faixa de horário a ser verificado
     * @return Verdadeiro se a checagem estiver ok, falso caso contrário
     */
    public boolean verificaHorarios(ArrayList<Calendar> horarios, Calendar horario1, Calendar horario2);
    
    
}
