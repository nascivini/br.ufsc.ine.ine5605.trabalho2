package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ERROR;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public class TelaCadastroCargo extends JFrame {

    private final TelaCargo telaCargo;
    private JLabel tipo;
    private JLabel codigo;
    private JLabel nome;
    private JTextField nomeEditavel;
    private JLabel horario1;
    private JTextField horario1Editavel;
    private JLabel horario2;
    private JTextField horario2Editavel;
    private JComboBox tipoEditavel;
    private JButton validarHorarios;
    private JButton cadastrar;
    private JButton limparTela;
    private JButton voltar;
    private JPanel continuaCadastro;
    private JFrame telaContinuaCadastro;

    public TelaCadastroCargo(TelaCargo telaCargo) {
        super("Tela de Cadastro de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
        this.inicializarTelaContinuarCadastro();
    }

    private void inicializarComponentes() {
        Dimension dimensaoTextos = new Dimension(140, 20);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        
        GerenciadorJComboBox gerenciadorCaixa = new GerenciadorJComboBox();
        this.tipoEditavel = new JComboBox(tiposCargo);
        this.tipo = new JLabel();
        this.tipoEditavel.addActionListener(gerenciadorCaixa);

        this.codigo = new JLabel();
        this.nome = new JLabel();
        this.nomeEditavel = new JTextField();
        this.horario1 = new JLabel();
        this.horario1Editavel = new JTextField();
        this.horario2 = new JLabel();
        this.horario2Editavel = new JTextField();
        this.validarHorarios = new JButton("Validar Horários");
        this.cadastrar = new JButton("Cadastrar");
        this.limparTela = new JButton("Limpar Tela");
        this.voltar = new JButton("Voltar à tela de Cargos");
        
        GerenciadorTextosBotoesCargo gerenciadorBotoesCargo = new GerenciadorTextosBotoesCargo();
        this.validarHorarios.addActionListener(gerenciadorBotoesCargo);
        this.cadastrar.addActionListener(gerenciadorBotoesCargo);
        this.limparTela.addActionListener(gerenciadorBotoesCargo);
        this.voltar.addActionListener(gerenciadorBotoesCargo);

        c.gridy = 0;
        c.gridx = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 0, 0);
        container.add(codigo, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        nome.setText("Nome:  ");
        container.add(nome, c);

        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        nomeEditavel.setPreferredSize(dimensaoTextos);
        container.add(nomeEditavel, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        tipo.setText("Tipo :");
        container.add(tipo, c);

        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        tipoEditavel.setPreferredSize(dimensaoTextos);
        container.add(tipoEditavel, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        horario1.setText("Primeiro Horário:  ");
        c.gridx = 0;
        c.gridy = 3;
        container.add(horario1, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        horario1Editavel.setPreferredSize(dimensaoTextos);
        c.gridx = 1;
        c.gridy = 3;
        container.add(horario1Editavel, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        horario2.setText("Segundo Horário:  ");
        c.gridx = 0;
        c.gridy = 4;
        container.add(horario2, c);

        c.gridx = 1;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        horario2Editavel.setPreferredSize(dimensaoTextos);
        container.add(horario2Editavel, c);

        c.gridy = 5;
        c.gridx = 0;
        container.add(validarHorarios, c);

        c.gridy = 5;
        c.gridx = 1;
        container.add(cadastrar, c);

        c.gridy = 5;
        c.gridx = 2;
        container.add(limparTela, c);
        
        c.gridy = 6;
        c.gridx = 1;
        container.add(voltar, c);

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void inicializarTelaContinuarCadastro(){
        this.continuaCadastro = new JPanel(new GridBagLayout());
        this.telaContinuaCadastro = new JFrame();
        
        Container container = telaContinuaCadastro.getContentPane();
        container.add(continuaCadastro);
        
        
    }
    
    public void updateData(){
        this.codigo.setText("Código do novo Cargo (gerado automaticamente):  " + telaCargo.getControladorCargo().geraSequencialCargo() + " .");
        this.nomeEditavel.setText("");
        this.horario1Editavel.setText("");
        this.horario2Editavel.setText("");
    }

    public class GerenciadorTextosBotoesCargo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == validarHorarios) {
                Calendar h1 = Calendar.getInstance();
                Calendar h2 = Calendar.getInstance();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    h1.setTime(sdf.parse(horario1Editavel.getText()));
                    h2.setTime(sdf.parse(horario2Editavel.getText()));

                    if (telaCargo.getControladorCargo().verificaHorarios(new ArrayList<Calendar>(), h1, h2)) {
                        JOptionPane.showConfirmDialog(null, "Horários estão OK!");
                    }

                } 
                catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Horário inválido! Respeite o padrão HH:MM");
                } 
                catch (IllegalArgumentException exc) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, exc);
                    JOptionPane.showMessageDialog(rootPane, exc.getMessage());
                }
            } 
            
            else if (event.getSource() == cadastrar) {
                if (tipoEditavel.getSelectedItem() != TipoCargo.GERENCIAL) {
                    ArrayList<Calendar> horarios = new ArrayList<>();
                    Calendar h1 = Calendar.getInstance();
                    Calendar h2 = Calendar.getInstance();
                    horarios.add(h1);
                    horarios.add(h2);
                    try {
                        ArrayList<Calendar> temp = new ArrayList<>();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                        h1.setTime(sdf.parse(horario1Editavel.getText()));
                        h2.setTime(sdf.parse(horario2Editavel.getText()));
                        

                        telaCargo.getControladorCargo().verificaHorarios(new ArrayList<Calendar>(), h1, h2);
                        telaCargo.getControladorCargo().findCargoByNome(nomeEditavel.getText());

                        DadosCargo cargoNovo = new DadosCargo(nomeEditavel.getText(), horarios, (TipoCargo) tipoEditavel.getSelectedItem());
                        telaCargo.getControladorCargo().incluirCargo(cargoNovo);
                        
                        JOptionPane.showMessageDialog(null, "Cargo cadastrado com sucesso!", "Sucesso!", JOptionPane.CLOSED_OPTION);
                        updateData();
                        setVisible(true);
                    } 
                    catch (ParseException ex) {
                        Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(null, "", "Erro!", ERROR);
                    } 
                    catch (IllegalArgumentException e) {
                        Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, e);
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", ERROR);
                        
                    }
                } 
                else {
                    try {
                        telaCargo.getControladorCargo().findCargoByNome(nomeEditavel.getText());
                        
                        DadosCargo cargoNovo = new DadosCargo(nomeEditavel.getText(), null, (TipoCargo) tipoEditavel.getSelectedItem());
                        telaCargo.getControladorCargo().incluirCargo(cargoNovo);

                        JOptionPane.showMessageDialog(null, "Cargo cadastrado com sucesso! Deseja cadastrar mais cargos?", "Sucesso!", JOptionPane.CLOSED_OPTION);
                        setVisible(false);
                        telaCargo.setVisible(true);
                    } 
                    catch (IllegalArgumentException e) {
                        Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, e);
                        JOptionPane.showConfirmDialog(null, e.getMessage(), "", JOptionPane.OK_CANCEL_OPTION);
                    }
                }
            }
            
            else if(event.getSource() == continuaCadastro){
                telaContinuaCadastro.setVisible(true);
            }
            
            else if(event.getSource() == voltar){
                setVisible(false);
                telaCargo.setVisible(true);
            }
        }

    }
    
    public class GerenciadorJComboBox implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == tipoEditavel){
                if(tipoEditavel.getSelectedItem() == TipoCargo.GERENCIAL){
                    horario1Editavel.setVisible(false);
                    horario2Editavel.setVisible(false);
                }
                else if(tipoEditavel.getSelectedItem() != TipoCargo.GERENCIAL){
                    horario1Editavel.setVisible(true);
                    horario2Editavel.setVisible(true);
                }
            }
        }
    }
}



/**
 * Inicia o módulo de cadastro de Cargos no sistema. Faz o tratamento dos dados
 * inseridos e efetiva o cadastro de um cargo, se utilizando do
 * controladorCargo(atributo). Utiliza os métodos incluirCargo,
 * geraSequencialCargo, reduzSequencialCargo e findCargoByNome do
 * controladorCargo.
 */
/*
    private void inicializarComponentesCadastro() {
        
        this.painelCadastro.setToolTipText("Cadastro de Cargos");
        this.painelCadastro = new JPanel(new GridLayout(4, 2));
        
        this.nomeCadastro = new JTextField();
        this.codigoCadastroCargo = new JLabel("Código do novo cargo(gerado automaticamente):" + this.getControladorCargo().geraSequencialCargo()+ ".");
        this.horario1 = new JTextField();
        this.horario2 = new JTextField();
        this.horario3 = new JTextField();
        this.botaoConfirmarCadastro = new JButton("Cadastrar");
        this.botaoLimparCadastro = new JButton("Limpar Dados");
        
        this.painelCadastro.add(nomeCadastro, 1, 0);
        this.painelCadastro.add(horario1, 2, 0);
        this.painelCadastro.add(horario2, 2, 1);
        this.painelCadastro.add(horario3, 2, 2);
        this.painelCadastro.add(botaoConfirmarCadastro, 4, 1);
        this.painelCadastro.add(botaoLimparCadastro, 4, 2);

        try {
            if (this.getControladorCargo().findCargoByNome(nome)) {
                this.getControladorCargo().reduzSequencialCargo();
                throw new IllegalArgumentException("Já existe um cargo com este nome no sistema. O cargo não foi cadastrado.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }

        System.out.println("Escolha o tipo de cargo dentre os listados abaixo. Digite o respectivo número e tecle enter para selecionar. ");
        System.out.println("1 - Gerencial");
        System.out.println("2 - Comum");
        System.out.println("3 - Convidado");

        int opcaoCargo = teclado.nextInt();

        boolean ehGerencial = false;
        String tipoCargo = "";
        boolean tipo = false;
        try {
            switch (opcaoCargo) {
                case (1):
                    tipo = true;
                    tipoCargo = "GERENCIAL";
                    ehGerencial = true;
                    break;
                case (2):
                    tipoCargo = "COMUM";
                    tipo = true;
                    break;
                case (3):
                    tipo = false;
                    tipoCargo = "CONVIDADO";
                    break;
                default:
                    throw new IllegalArgumentException("Cargo não cadastrado. Você deve digitar opções válidas. Selecione um tipo dentre os tipos listados.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.inicia();
        }

        ArrayList<Calendar> horarios = new ArrayList<>();

        switch (tipoCargo) {
            case "GERENCIAL": {
                DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
            }
            case "CONVIDADO": {
                DadosCargo cargoNovo = new DadosCargo(nome, tipo, false, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
            }
            default:
                boolean continuaCadastro = true;

                while (continuaCadastro) {

                    if (horarios.isEmpty()) {
                        
                        Calendar horario1 = Calendar.getInstance();
                        Calendar horario2 = Calendar.getInstance();                        
                        teclado.nextLine();
                        boolean cadastroInvalido = true;
                        while(cadastroInvalido){    
                            System.out.println("Digite o horário inicial em que o acesso é permitido.(Hora)");
                            int horaInicial = teclado.nextInt();
                            System.out.println("Digite o minuto inicial.");
                            int minInicial = teclado.nextInt();

                            try {
                                if (horaInicial >= 0 && horaInicial <= 23 && minInicial >= 0 && minInicial <= 59) {
                                    horario1.set(0, 0, 0, horaInicial, minInicial);
                                    cadastroInvalido = false;
                            }   
                                else {
                                    this.getControladorCargo().reduzSequencialCargo();
                                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                                }
                            }   
                            catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                cadastroInvalido = true;
                            }
                        }        
                        
                        boolean cadastro2Invalido = true;
                        while(cadastro2Invalido){
                            System.out.println("Digite o horário final em que o acesso é permitido.(Hora)");
                            int horaFinal = teclado.nextInt();
                            System.out.println("Digite o minuto final.");
                            int minFinal = teclado.nextInt();
                            try {
                                if (horaFinal >= 0 && horaFinal <= 23 && minFinal >= 0 && minFinal <= 59) {
                                    horario2.set(0, 0, 0, horaFinal, minFinal);
                                    cadastro2Invalido = false;
                                }   
                                else {
                                    this.getControladorCargo().reduzSequencialCargo();
                                    throw new IllegalArgumentException("Digite um horário válido! Entre 00:00 e 23:59. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                                cadastro2Invalido = true;
                            }
                        }
                        try {
                            if (this.getControladorCargo().verificaHorarios(horarios, horario1, horario2)) {
                                horarios.add(horario1);
                                horarios.add(horario2);
                                System.out.println("Horários cadastrados com sucesso!");
                            } else {
                                throw new IllegalArgumentException("Horários são iguais!! Cadastro não realizado.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            this.inicia();
                        }

                        teclado.nextLine();

                        System.out.println("Deseja cadastrar mais horários de acesso? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } else {
                            continuaCadastro = false;
                        }

                    } else {
                        Calendar horario3 = Calendar.getInstance();
                        Calendar horario4 = Calendar.getInstance();
                        
                        boolean cadastroInvalido = true;
                        while(cadastroInvalido){
                            System.out.println("Digite o horário inicial em que o acesso é permitido.(Hora)");
                            int horaInicial = teclado.nextInt();
                            System.out.println("Digite os minutos do horário inicial.");
                            int minInicial = teclado.nextInt();
                            teclado.nextLine();

                            try {
                                if (horaInicial >= 0 && horaInicial <= 23 && minInicial >= 0 && minInicial <= 59) {
                                    horario3.set(0, 0, 0, horaInicial, minInicial);
                                    cadastroInvalido = false;
                                } 
                                else {
                                    throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                cadastroInvalido = true;
                            }
                        }
                        
                        boolean cadastro2Invalido = true;
                        while(cadastro2Invalido){
                            System.out.println("Digite o horário final em que o acesso é permitido.(Hora)");
                            int horaFinal = teclado.nextInt();
                            System.out.println("Digite os minutos do horário final.");
                            int minFinal = teclado.nextInt();
                            teclado.nextLine();

                            try {
                                if (horaFinal >= 0 && horaFinal <= 23 && minFinal >= 0 && minFinal <= 59) {
                                    horario4.set(0, 0, 0, horaFinal, minFinal);
                                    cadastro2Invalido = false;
                                } 
                                else {
                                    throw new IllegalArgumentException("Hora ou minuto são inválidos e não estão no formato válido de hora. Verifique os mesmos e tente novamente. O cargo não foi cadastrado.");
                                }
                            } 
                            catch (IllegalArgumentException e) {
                                this.getControladorCargo().reduzSequencialCargo();
                                System.out.println(e.getMessage());
                                cadastro2Invalido = true;
                            }
                        }    
                        
                        try {
                            this.getControladorCargo().verificaHorarios(horarios, horario3, horario4);
                            horarios.add(horario3);
                            horarios.add(horario4);
                            System.out.println("Horarios cadastrados com sucesso!");
                        }   
                        catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        
                        System.out.println("Deseja continuar o cadastro de horários? Digite Y caso sim, ou digite qualquer caractere para finalizar o cadastro do cargo.");
                        String continuar = teclado.nextLine();

                        if (continuar.equals("Y") || continuar.equals("y")) {
                            continuaCadastro = true;
                        } else {
                            continuaCadastro = false;
                        }

                    }
                }

                DadosCargo cargoNovo = new DadosCargo(nome, tipo, ehGerencial, horarios, tipoCargo);
                this.getControladorCargo().incluirCargo(cargoNovo, codigo);
                System.out.println("Cargo cadastrado com sucesso!");
                break;
        }
        this.inicia();
    }
    * */
