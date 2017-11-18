package br.ufsc.ine.ine5605.trabalho2.Cargo;

import br.ufsc.ine.ine5605.trabalho2.Principal.ControladorPrincipal;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurelio Geremias
 */
public class ControladorCargo implements IControladorCargo {

    private final ControladorPrincipal controladorPrincipal;
    private CargoDAO cargoDAO;
    private final TelaCargo telaCargo;
    private int sequencialCargo;

    public ControladorCargo(ControladorPrincipal controladorPrincipal) {
        this.cargoDAO = new CargoDAO();
        this.controladorPrincipal = controladorPrincipal;
        this.sequencialCargo = 0;
        this.telaCargo = new TelaCargo(this);
    }

    public CargoDAO getCargoDAO() {
        return cargoDAO;
    }
    
    

    public ArrayList<Cargo> getCargos() {
        return new ArrayList<Cargo>(cargoDAO.getList());
    }

    public TelaCargo getTelaCargo() {
        return telaCargo;
    }

    public ControladorPrincipal getControladorPrincipal() {
        return this.controladorPrincipal;
    }

    @Override
    public Cargo incluirCargo (DadosCargo conteudo) throws IllegalArgumentException{
        if(this.findCargoByNome(conteudo.nome) != null){
            throw new IllegalArgumentException("Já existe um cargo com este nome no sistema.");
        }
        Cargo novo = new Cargo(conteudo, this.geraSequencialCargo());
        cargoDAO.put(novo);
        return novo;
    }

    @Override
    public boolean excluirCargo(Cargo cargo) throws IllegalArgumentException{
        if(this.findCargoByCodigo(cargo.getCodigo()) != null){
            this.cargoDAO.remove(this.cargoDAO.get(cargo.getCodigo()));
            return true;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Cargo alterarCargo(DadosCargo conteudo, int codigo) {
        Cargo alterado = findCargoByCodigo(codigo);
        if (conteudo != null) {
            if (conteudo.horarios == null) {
                alterado.setHorarios(new ArrayList<Calendar>());
            }
            if (conteudo.tipoCargo != null) {
                alterado.setTipoCargo(conteudo.tipoCargo);
                return alterado;
            } else if (conteudo.nome != null) {
                alterado.setNome(conteudo.nome);
                return alterado;
            } else if (conteudo.permiteAcesso == false) {
                alterado.setPermiteAcesso(false);
                return alterado;
            } else if (conteudo.permiteAcesso) {
                alterado.setPermiteAcesso(true);
                return alterado;
            } else {
                throw new IllegalArgumentException("Dado inválido! O cargo não foi alterado.");
            }
        }

        return null;
    }

    @Override
    public Cargo findCargoByNome(String nome) {
        for (Cargo cargoAtual : this.cargoDAO.getList()) {
            if (cargoAtual.getNome().equals(nome)) {
                return cargoAtual;
            }
        }
        return null;
    }
    
    public Cargo findCargoByCodigo(int codigo){
        for(Cargo cargoLista : this.getCargos()){
            if(cargoLista.getCodigo() == codigo){
                return cargoLista;
            }
        }
        return null;
    }

    /**
     * Gera um número sequencial para uso da TelaCargo no cadastro de um novo
     * cargo, permitindo a consistência dos códigos de cargos novos a serem
     * cadastrados no sistema.
     *
     * @return Sequencial gerado.
     */
    public int geraSequencialCargo() {
        this.sequencialCargo = this.getCargos().size();
        return this.sequencialCargo + 1;
    }

    @Override
    public boolean verificaHorarios (ArrayList<Calendar> horarios, Calendar horario1, Calendar horario2) throws IllegalArgumentException{

        if (horarios.isEmpty()) {
            if (horario1.compareTo(horario2) != 0) {
                return true;
            }
        } else {
            boolean horario1OK = false;
            boolean horario2OK = false;

            for (int i = 0; i < horarios.size(); i = i + 2) {
                if (horarios.get(i).after(horarios.get(i + 1))) {
                    if (horario1.before(horarios.get(i)) && horario1.after(horarios.get(i + 1))) {
                        horario1OK = true;
                    } else {
                        horario1OK = false;
                        throw new IllegalArgumentException("Horário inicial está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente.");
                    }
                } else {
                    if ((!(horario1.after(horarios.get(i)) && horario1.before(horarios.get(i + 1))))) {
                        horario1OK = true;
                    } else {
                        horario1OK = false;
                        throw new IllegalArgumentException("Horário inicial está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente.");
                    }
                }
            }
            for (int i = 0; i < horarios.size(); i = i + 2) {
                if (horarios.get(i).after(horarios.get(i + 1))) {
                    if ((horario2.after(horario1)) && (horario2.before(horarios.get(i)))) {
                        horario2OK = true;
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("Horário final está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente.");
                    }
                } else {
                    if (horario1.after(horario2) && horario1.after(horarios.get(i + 1)) && horario2.before(horarios.get(i))) {
                        horario2OK = true;
                    } else if (!(horario2.after(horarios.get(i)) && ((horario2.before(horarios.get(i + 1))))) && (horario2.before(horarios.get(i)) || (horario1.after(horarios.get(i + 1)) && horario2.after(horarios.get(i + 1))))) {
                        if (horario2.after(horarios.get(i))) {
                            horario2OK = false;
                        } else {
                            horario2OK = true;
                        }
                    } else {
                        horario2OK = false;
                        throw new IllegalArgumentException("Horário final está dentro de uma faixa de horários já cadastrada ou é nulo. Verifique o mesmo e tente novamente.");
                    }
                }
            }
            if (horario1OK && horario2OK) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
