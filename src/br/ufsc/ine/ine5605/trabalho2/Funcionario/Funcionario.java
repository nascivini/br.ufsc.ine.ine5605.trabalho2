package br.ufsc.ine.ine5605.trabalho2.Funcionario;

import br.ufsc.ine.ine5605.trabalho2.Cargo.Cargo;
import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class Funcionario implements Serializable {
    private int matricula;
    private long cpf;
    private String nome;
    private Cargo cargo;
    private String nascimento;
    private long telefone;
    private float salario;
    private int nAcessosNegados;

    public Funcionario(int matricula, DadosFuncionario conteudo) {
        this.matricula = matricula;
        this.cpf = conteudo.cpf;
        this.nome = conteudo.nome;
        this.cargo = conteudo.cargo;
        this.nascimento = conteudo.nascimento;
        this.telefone = conteudo.telefone;
        this.salario = conteudo.salario;
        this.setnAcessosNegados(0);
    }
    
    /**
     * 
     * @return A matr�cula do funcion�rio.
     */
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    /**
     * 
     * @return O nome do funcion�rio.
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * 
     * @return O cargo do funcion�rio. 
     */
    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    /**
     * 
     * @return A data de nascimento do funcion�rio.
     */
    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * 
     * @return O n�mero de telefone do funcion�rio.
     */
    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    /**
     * 
     * @return O sal�rio do funcion�rio.
     */
    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * 
     * @return O CPF do funcion�rio.
     */
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    /**
     * 
     * @return O n�mero de acessos negados do funcion�rio.
     */
	public int getnAcessosNegados() {
		return nAcessosNegados;
	}

	public void setnAcessosNegados(int nAcessosNegados) {
		this.nAcessosNegados = nAcessosNegados;
	}        
}
