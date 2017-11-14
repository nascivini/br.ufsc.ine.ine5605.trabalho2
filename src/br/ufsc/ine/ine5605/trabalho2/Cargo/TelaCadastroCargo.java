/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
 * @author 47895159828
 */
public class TelaCadastroCargo extends JFrame {
    
    private final TelaCargo telaCargo;        
    private JComboBox tipo;
    private JTextField codigo;
    private JTextField nome;
    private JTextField horario1;
    private JTextField horario2;
    private JButton validarHorarios;
    private JButton cadastrar;
    private JButton limparTela;

    public TelaCadastroCargo(TelaCargo telaCargo){
        super("Tela de Cadastro de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
    }

    private void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 1));
        GridBagConstraints c = new GridBagConstraints();
        
        TipoCargo[] tiposCargo = {TipoCargo.COMUM, TipoCargo.CONVIDADO, TipoCargo.GERENCIAL};
        
        this.tipo = new JComboBox(tiposCargo);
        
        this.codigo = new JTextField(this.telaCargo.getControladorCargo().geraSequencialCargo());
        this.nome = new JTextField();
        this.horario1 = new JTextField();
        this.horario2 = new JTextField();
        this.validarHorarios = new JButton("Validar Horarios");
        this.cadastrar = new JButton("Cadastrar");
        this.limparTela = new JButton("Limpar Tela");
        
        GerenciadorCaixaDeTexto gerenciadorCaixaDeTexto = new GerenciadorCaixaDeTexto();
        
        this.codigo.addActionListener(gerenciadorCaixaDeTexto);
        this.horario1.addActionListener(gerenciadorCaixaDeTexto);
        this.horario2.addActionListener(gerenciadorCaixaDeTexto);
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, WIDTH, WIDTH, WIDTH);
        codigo.setText("Código do novo Cargo: " + telaCargo.getControladorCargo().geraSequencialCargo() + ".");
        container.add(codigo, c);
        
        container.add(tipo, c);
        c.gridx = 0;
        c.gridy = 1;        
        container.add(nome, c);
        
        c.gridx = 0;
        c.gridy = 2;
        container.add(horario1, c);
        
        c.gridx = 0;
        c.gridy = 3;
        container.add(horario2, c);
        
        container.add(validarHorarios);
        container.add(cadastrar);
        container.add(limparTela);
        
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public class GerenciadorCaixaDeTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent event) {
            if(event.getSource() == nome){
                try{
                    telaCargo.getControladorCargo().findCargoByNome(nome.getText());
                }
                catch(IllegalArgumentException excep){
                    JOptionPane.showMessageDialog(null, excep.getMessage());
                }
            }
            else if(event.getSource() == validarHorarios){
                Calendar h1 = Calendar.getInstance();
                Calendar h2 = Calendar.getInstance();
		
                try{                    
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    h1.setTime(sdf.parse(horario1.getText()));
                    h2.setTime(sdf.parse(horario2.getText()));
                
                    if(telaCargo.getControladorCargo().verificaHorarios(new ArrayList<Calendar>(), h1, h2)){
                        JOptionPane.showConfirmDialog(null, "Horários estão OK!");
                    }
                
                }
                catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Horário inválido! Respeite o padrão HH:MM");
                }
                catch(IllegalArgumentException exc){
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, exc);
                    JOptionPane.showMessageDialog(rootPane, exc.getMessage());
                }
            }
            else if(event.getSource() == cadastrar){              
                Calendar h1 = Calendar.getInstance();
                Calendar h2 = Calendar.getInstance();
                ArrayList<Calendar> horarios = new ArrayList<>();
                horarios.add(h1);
                horarios.add(h2);
                
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    h1.setTime(sdf.parse(horario1.getText()));
                    h2.setTime(sdf.parse(horario2.getText()));
               
                    telaCargo.getControladorCargo().verificaHorarios(new ArrayList<Calendar>(), h1, h2);  
                    telaCargo.getControladorCargo().findCargoByNome(nome.getText());
                    
                    DadosCargo cargoNovo = new DadosCargo(nome.getText(), horarios, (TipoCargo)tipo.getSelectedItem());
                    telaCargo.getControladorCargo().incluirCargo(cargoNovo);
                } 
                
                catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", ERROR);
                }
                catch(IllegalArgumentException e){
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", ERROR);
                }
            }
            
        }
    
    }
        
    }
    
    /**
     * Inicia o módulo de cadastro de Cargos no sistema. Faz o tratamento dos
     * dados inseridos e efetiva o cadastro de um cargo, se utilizando do
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

