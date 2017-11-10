package br.ufsc.ine.ine5605.trabalho2.Funcionario;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
import br.ufsc.ine.ine5605.trabalho2.Cargo.Cargo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TelaFuncionario {
    private final Scanner teclado;
    private final ControladorFuncionario controladorFuncionario;

    public TelaFuncionario(ControladorFuncionario controladorFuncionario){
        this.controladorFuncionario = controladorFuncionario;
        this.teclado = new Scanner(System.in);
    }
    
    /**
     * Inicia a tela com o Menu das opções para o módulo Funcionário. Pode jogar
     * exceções do tipo IllegalArgumentException e InputMismatchException.
     *
     * @throws IllegalArgumentException Caso seja digitada uma opção inválida.
     * @throws InputMismatchException Caso seja digitado um caracter inválido.
     */
    public void inicia() throws IllegalArgumentException, InputMismatchException {
        System.out.println("---Menu de Cadastro de Funcionários---");
        System.out.println("Escolha a opção desejada, insira o número correspondente e tecle enter:");
        System.out.println("1 - Incluir Funcionario");
        System.out.println("2 - Excluir Funcionario");
        System.out.println("3 - Alterar Dados de um Funcionario");
        System.out.println("4 - Listar Funcionários Cadastrados");
        System.out.println("5 - Voltar ao Menu Principal");
    
    try {
        int opcao = teclado.nextInt();
        teclado.nextLine();
            switch (opcao) {
                case (1):
                    this.cadastroFuncionario();
                    break;
                case (2):
                    this.exclusaoFuncionario();
                    break;
                case (3):
                    this.alteracaoFuncionario();
                    break;
                case (4):
                    this.listarFuncionarios();
                    break;
                case (5):
                    controladorFuncionario.getControladorPrincipal().getTelaPrincipal().inicia();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } 
    
    catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Opção Inválida! Escolha uma opção dentre as opções na lista.");
            this.controladorFuncionario.getTelaFuncionario().inicia();
        }
    }
    
    /**
     * Inicia o módulo de cadastro de Funcionários no sistema. Faz o tratamento dos
     * dados inseridos e efetiva o cadastro de um Funcionario, se utilizando do
     * controladorFuncionario(atributo). Utiliza os métodos findFuncionarioByCpf,
     * e incluiFuncionario.
     * Utiliza também o método findCargoByCodigo do controladorCargo em execução no programa, passando pelo controladorPrincipal.
     */
        private void cadastroFuncionario() {
        System.out.println("Cadastro de Funcionário");
        System.out.println("Insira os dados requisitados. Após a inserção de todos os dados, o funcionário será cadastrado no sistema.");

        System.out.println("CPF: ");
        long cpf = teclado.nextLong();
        
        try {
            if (this.controladorFuncionario.findFuncionarioByCpf(cpf)) {
                throw new IllegalArgumentException("Este CPF já está em uso.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }
        
        teclado.nextLine();
        
        System.out.println("Nome: ");
        String nome = teclado.nextLine();

        System.out.println("Código do cargo: ");
        Cargo cargo = null;
        try {    
            int codigo = teclado.nextInt(); 
            cargo = this.controladorFuncionario.getControladorPrincipal().getControladorCargo().findCargoByCodigo(codigo);
        } 
        catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }
        
        teclado.nextLine();
        
        System.out.println("Nascimento: ");
        System.out.println("O formato dia/mês/ano deve ser respeitado. Exemplo: 15/02/1994");
        String data = teclado.nextLine();
        
        Calendar nascimento = Calendar.getInstance();
        try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            nascimento.setTime(formatador.parse(data));
        }
        
        catch (ParseException erro) {
            System.out.println("Essa não é uma data válida.");
            this.controladorFuncionario.getTelaFuncionario().inicia();
        }
        
        
        System.out.println("Telefone: ");
        long telefone = teclado.nextLong();
        
        System.out.println("Salário: ");
        float salario = teclado.nextFloat();
        
        DadosFuncionario novoFuncionario = new DadosFuncionario(cpf, nome, cargo, nascimento, telefone, salario);
        Funcionario incluido = this.controladorFuncionario.incluirFuncionario(novoFuncionario);
        System.out.println("Funcionário cadastrado com sucesso!" + " | Matricula gerada: " + incluido.getMatricula());
        this.inicia();
        
    }

     /**
     * Inicia a tela de exclusão de funcionários, faz o tratamento dos dados inseridos
     * pelo usuário e, antes da exclusão, verifica a existência do funcionário (retorno true or false).
     * Utiliza o método excluirFuncionario, do controladorFuncionario(atributo).
     */
    private void exclusaoFuncionario() {
        System.out.println("Para excluir um funcionário do sistema, digite a matrícula do mesmo.");
        int matricula = teclado.nextInt();

        if (this.controladorFuncionario.excluirFuncionario(matricula)) {
            System.out.println("Funcionário excluído com sucesso!");
            this.inicia();
        } else {
            System.out.println("A matrícula informada não pertence a nenhum funcionário cadastrado.");
            System.out.println("Deseja tentar novamente? Digite Y ou N");
            String opcaoExclusao = teclado.nextLine();
            if (opcaoExclusao.equals("Y") || opcaoExclusao.equals("y")) {
                this.exclusaoFuncionario();
            }
            else{
                this.inicia();
            }
        }
    }
    
    /**
     * Inicia a tela de alteração de dados cadastrais dos funcionários, faz o tratamento dos dados inseridos
     * pelo usuário
     * Utiliza o método alterarFuncionario, do ControladorFuncionario
     */

    private void alteracaoFuncionario() {
        System.out.println("Bem-vindo à tela de alteração de dados dos funcionários.");
        System.out.println("Só é possível alterar um dado por vez. Digite a matrícula a ser alterada, e selecione qual dado deseja alterar.");
        
        int matricula = teclado.nextInt();
        teclado.nextLine();
        if(this.controladorFuncionario.findFuncionarioByMatricula(matricula) == null){
            System.out.println("Funcionário não encontrado. Digite uma matrícula válida.");
            this.alteracaoFuncionario();
        }
        else {
            System.out.println("--------------------------------------");
            System.out.println("ATENÇÃO: Digite 0 nos campos que você não quiser alterar.");
           
            System.out.println("CPF: ");
            long cpf = teclado.nextLong();
            teclado.nextLine();
            
        try {
            if (this.controladorFuncionario.findFuncionarioByCpf(cpf)) {
                throw new IllegalArgumentException("Este CPF já está em uso.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }
            
            System.out.println("Nome: ");
            String nome = teclado.nextLine();
            
            System.out.println("Codigo do cargo: ");
            int codigo = teclado.nextInt();
            Cargo cargo = this.controladorFuncionario.getControladorPrincipal().getControladorCargo().findCargoByCodigo(codigo);
           
            System.out.println("Nascimento:");
            System.out.println("O formato dia/mês/ano deve ser respeitado. Exemplo: 15/02/1994");
            System.out.println("Se não quiser alterar o Nascimento, digite 00/00/00");
                Calendar nascimento = Calendar.getInstance();
                try {
                SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
                String data = teclado.nextLine();
                nascimento.setTime(formatador.parse(data));
                }
        
                catch (ParseException erro) {
                System.out.println("Essa não é uma data válida.");
                this.controladorFuncionario.getTelaFuncionario().inicia();
                }
            
            System.out.println("Telefone ");
            long telefone = teclado.nextLong();
            
            System.out.println("Salário: ");
            float salario = teclado.nextFloat();
        
            DadosFuncionario novosDados = new DadosFuncionario(cpf, nome, cargo, nascimento, telefone, salario);
            this.controladorFuncionario.alterarFuncionario(matricula, novosDados);
            
        }
    }
    
    /**
     * Chama o método listarFuncionarios do controladorFuncionarios
     * para listar os funcionários já cadastrados
     */
    private void listarFuncionarios() {
        this.controladorFuncionario.listarFuncionarios();
        this.inicia();
    }
}

