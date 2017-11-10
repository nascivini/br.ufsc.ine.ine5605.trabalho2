package br.ufsc.ine.ine5605.trabalho2.Cargo;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public enum TipoCargo {
    GERENCIAL("Cargo Gerencial", true),
    COMUM("Cargo Comum", true),
    CONVIDADO("Cargo Convidado", false);
    private final String descricao;
    private final boolean permiteAcesso;
    
    TipoCargo(String descricao, boolean permiteAcesso){
        this.descricao = descricao;
        this.permiteAcesso = permiteAcesso;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public boolean getPermiteAcesso(){
        return this.permiteAcesso;
    }
}
