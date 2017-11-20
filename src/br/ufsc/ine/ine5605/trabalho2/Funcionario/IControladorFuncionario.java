package br.ufsc.ine.ine5605.trabalho2.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public interface IControladorFuncionario {
    
    /**
     * É utilizado pela TelaFuncionario para incluir um novo funcionário na lista de funcionários e gerar matrícula sequencial
     * para cada nova inclusão
     * @param conteudo - Conteúdo do tipo DadosFuncionario recebido na entrada do método cadastroFuncionario da TelaFuncionario
     * @return novo - Novo funcionário quando o conteudo não recebido não é vazio
     */    
    public Funcionario incluirFuncionario (DadosFuncionario conteudo);
    
    /**
     * É utilizado pela classe TelaFuncionario, no método exclusaoFuncionario().
     * Se a matricula informada no parâmetro for existente, exclui o funcionário correspondente a mesma.
     * @param matricula Matrícula do cargo a ser excluído
     * 
     */    
    public void excluirFuncionario(Funcionario funcionario);
    
    /**
     * É utilizado pela TelaFuncionario para exibir a lista de funcionários já cadastrados.
    */    
    public void listarFuncionarios();
    
    /**
     *
     * @return O número da sequência de matrícula.
     */    
    public int gerarMatriculaSequencial();
    
    /**
     * @param matricula Matrícula a ser buscada
     */    
    public void findFuncionarioByMatricula(int matricula) throws ExceptionFuncionario;
    
    /**
     * É utilizado na TelaFuncionario e garante que nenhum funcionário tenha CPF igual a outro
     * @param cpf CPF do Funcionário a ser buscado
     * @return Verdadeiro se o CPF já estiver em uso por algum funcionário já cadastrado
     * Falso caso contrário
     */    
    public void findFuncionarioByCpf(long cpf) throws ExceptionFuncionario;    
    
     /** 
     * @param matricula Matrícula a ser validada
     * @return Verdadeiro se o número de matrícula informado existir na lista de funcionários já cadastrados
     * Falso caso contrário
     */
    public boolean validaMatricula(int matricula);
}
