package br.ufsc.ine.ine5605.trabalho2.Acesso;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public enum MotivoAcesso {

    OK("Acesso permitido"),
    ATRASADO("Fora do horario do seu cargo"),
    PERMISSAO("Nao possui permissao"),
    BLOQUEADO("Mais de tres acessos negados"),
    OUTRO("NAO ESPECIFICADO");

    private String descricao;

    MotivoAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}

