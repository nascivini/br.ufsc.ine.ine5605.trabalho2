package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class DadosCargo {
    
    public String nome;
    public boolean  permiteAcesso;
    public final boolean ehGerencial;
    public ArrayList<Calendar> horarios;
    public TipoCargo tipoCargo;
    
    /**
     * Recebe os dados de um cargo, vindos da TelaCargo, e instancia um objeto transiente para posterior uso para o cadastro de um cargo pelo ControladorCargo.
     * @param nome Nome do cargo
     * @param horarios ArrayList com os horários do cargo.
     * @param tipo Tipo do cargo.
     */
    public DadosCargo(String nome, ArrayList<Calendar> horarios, TipoCargo tipo) {
        this.nome = nome;
        if(tipo.equals(TipoCargo.GERENCIAL)){
            ehGerencial = true;
        }
        else{
            ehGerencial = false;
        }
        this.horarios = horarios;
        this.tipoCargo = tipo;
        this.permiteAcesso = tipoCargo != TipoCargo.CONVIDADO;
    }
}
