package br.ufsc.ine.ine5605.trabalho2.Acesso;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public enum MotivoAcesso {

    OK("Permitido. Acesso realizado."),
    ATRASADO("Atrasado! Fora do horário do seu cargo."),
    PERMISSAO("Nao possui permissão de acesso."),
    BLOQUEADO("Bloqueado! Mais de três acessos negados."),
    OUTRO("Não Especificado."),
    EXCLUIDO("Registro Excluído");

    private String descricao;

    MotivoAcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }
}

