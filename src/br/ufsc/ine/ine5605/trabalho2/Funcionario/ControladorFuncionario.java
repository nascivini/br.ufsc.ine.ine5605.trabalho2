package br.ufsc.ine.ine5605.trabalho2.Funcionario;
/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import br.ufsc.ine.ine5605.trabalho2.Principal.ControladorPrincipal;
import java.util.ArrayList;
import java.util.Collection;

public class ControladorFuncionario implements IControladorFuncionario {

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ControladorPrincipal controladorPrincipal;
    private final TelaFuncionario telaFuncionario;
    private int matriculaSequencial;
 
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

    public Collection<Funcionario> getFuncionarios() {
        return funcionarioDAO.getList();
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
    
    public Funcionario alterarFuncionario(int matricula, DadosFuncionario conteudo) {
        if (validaMatricula(matricula)) {
            if (conteudo != null) {
                if (conteudo.cpf != 0) {
                    this.findFuncionarioByMatricula(matricula).setCpf(conteudo.cpf);
                }
                if (!conteudo.nome.equals("0")) {
                    this.findFuncionarioByMatricula(matricula).setNome(conteudo.nome);
                }
                if (!conteudo.cargo.equals(0)) {
                    this.findFuncionarioByMatricula(matricula).setCargo(conteudo.cargo);
                }
                if (!conteudo.nascimento.equals(00/00/00)) {
                    this.findFuncionarioByMatricula(matricula).setNascimento(conteudo.nascimento);
                }
                if (conteudo.telefone != 0) {
                    this.findFuncionarioByMatricula(matricula).setTelefone(conteudo.telefone);
                }
                if (conteudo.salario != 0) {
                    this.findFuncionarioByMatricula(matricula).setSalario(conteudo.salario);
                }              
            } else {
                this.findFuncionarioByMatricula(matricula);
            }
        } else {
            return null;
        }
        return null;
    }
    
    @Override
    public void listarFuncionarios() {
        for(Funcionario funcionario: getFuncionarios()){
            System.out.println("Matrícula: " + funcionario.getMatricula() + " | Nome: " + funcionario.getNome());
            System.out.println("Telefone :" + funcionario.getTelefone() + "|Salário :" + funcionario.getSalario() + " |Cargo :" + funcionario.getCargo().getNome());
            System.out.println("");
        }
    }
    
    @Override
    public int gerarMatriculaSequencial() {
        this.matriculaSequencial++;
        return this.matriculaSequencial;
    }
    
    @Override
    public Funcionario findFuncionarioByMatricula(int matricula) {
            return funcionarioDAO.get(matricula);
    }
    
    @Override
    public boolean findFuncionarioByCpf(long cpf) {
        for (Funcionario funcionario : getFuncionarios()) {
            if (funcionario.getCpf() == cpf) {
                return true;
            }
        }
        return false;
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

    @Override
    public boolean excluirFuncionario(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
