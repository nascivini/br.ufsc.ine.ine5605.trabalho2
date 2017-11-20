package br.ufsc.ine.ine5605.trabalho2.Funcionario;
/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import br.ufsc.ine.ine5605.trabalho2.Cargo.Cargo;
import br.ufsc.ine.ine5605.trabalho2.Principal.ControladorPrincipal;
import java.util.ArrayList;

public class ControladorFuncionario implements IControladorFuncionario {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ControladorPrincipal controladorPrincipal;
    private final TelaFuncionario telaFuncionario;
    private int matriculaSequencial;
    private TelaCadastroFuncionario telaCadastroFuncionario;
 
    /**
     * Inicia a classe ControladorFuncionario
     * @param controladorPrincipal ControladorPrincipal criado pelo main
     */
    public ControladorFuncionario(ControladorPrincipal controladorPrincipal) {
        this.matriculaSequencial = 0;
        this.controladorPrincipal = controladorPrincipal;
        this.telaFuncionario = new TelaFuncionario(this);
    }

    public TelaFuncionario getTelaFuncionario() {
        return telaFuncionario; 
    }

    public TelaCadastroFuncionario getTelaCadastroFuncionario() {
        return telaCadastroFuncionario;
    }
    

    public ArrayList<Funcionario> getFuncionarios() {
        return new ArrayList<Funcionario>(funcionarioDAO.getList());
    }
    
        public ControladorPrincipal getControladorPrincipal() {
        return this.controladorPrincipal;
    }

    public Funcionario incluirFuncionario(DadosFuncionario conteudo) {
        if (conteudo != null) {
            Funcionario novo = new Funcionario(this.gerarMatriculaSequencial(), conteudo);
            funcionarioDAO.put(novo); 
            return novo;
        }
        return null;
    }

    public void excluirFuncionario(Funcionario funcionario) {
        funcionarioDAO.remove(funcionario);
    }
    
    @Override
    public void listarFuncionarios() {
        for(Funcionario funcionario: getFuncionarios()){
            System.out.println("Matrícula: " + funcionario.getMatricula() + " | Nome: " + funcionario.getNome());
            System.out.println("Telefone :" + funcionario.getTelefone() + "| Salário :" + funcionario.getSalario() + " | Cargo :" + funcionario.getCargo().getNome());
            System.out.println("");
        }
    }
    
    @Override
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial = this.getFuncionarios().size();
        return this.matriculaSequencial + 1;
    }
    
    @Override
    public void findFuncionarioByMatricula(int matricula) throws ExceptionFuncionario {
        for(Funcionario funcionario : getFuncionarios()){
            if (funcionario.getMatricula() == matricula) {
                throw new ExceptionFuncionario("Matrícula já existente!");
            }
        }
        
    }
    
    public Funcionario retornaFuncionarioByMatricula(int matricula) {
            return funcionarioDAO.get(matricula);
    }
           
    
    @Override
    public void findFuncionarioByCpf(long cpf) throws ExceptionFuncionario {
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getCpf() == cpf) {
                throw new ExceptionFuncionario("CPF já está cadastrado.");
            }
        }
    }
    
    public void findFuncionarioByCargo(Cargo cargo) throws ExceptionFuncionario{
        for(Funcionario funcionarioAtual : this.getFuncionarios()){
            if(funcionarioAtual.getCargo().getCodigo() == cargo.getCodigo()){
            }
        }
        throw new ExceptionFuncionario("");
    }
    
    @Override
    public boolean validaMatricula(int matricula) {
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getMatricula() == matricula) {
                return true;
            }
        }
        return false;
    }

    public FuncionarioDAO getFuncionarioDAO() {
        return funcionarioDAO;
    }





}
