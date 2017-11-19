/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Viinicius
 */
public class TelaContinuarCadastroHorarios extends JFrame {

    private final Cargo cargo;
    public JButton adicionarHorarios;
    public JButton adicionarHorarios1;
    private JLabel horario1;
    private JTextField horario1Editavel;
    private JLabel horario2;
    private JTextField horario2Editavel;
    private TelaCadastroCargo telaCadastroCargo;
    private TelaAlteracaoCargo telaAlteracaoCargo;
    private JLabel horariosJaCadastrados;

    public TelaContinuarCadastroHorarios(Cargo cargo, TelaCadastroCargo telaCadastroCargo) {
        super("Adicionar Mais Horarios");
        this.cargo = cargo;
        this.telaCadastroCargo = telaCadastroCargo;
        this.telaAlteracaoCargo = null;
        this.inicializarComponentes();
        this.adicionarHorarios1.setVisible(false);
    }

    public TelaContinuarCadastroHorarios(Cargo cargo, TelaAlteracaoCargo telaAlteracaoCargo) {
        super("Adicionar Mais Horarios");
        this.cargo = cargo;
        this.telaCadastroCargo = null;
        this.telaAlteracaoCargo = telaAlteracaoCargo;
        this.inicializarComponentes();
        this.adicionarHorarios.setVisible(false);
        this.adicionarHorarios1.setVisible(true);
    }

    public void inicializarComponentes() {
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        Dimension dimensaoTextos = new Dimension(140, 20);
        GridBagConstraints c = new GridBagConstraints();
        GerenciadorBotoesHorario gerenciador = new GerenciadorBotoesHorario();

        this.horario1 = new JLabel();
        this.horario1Editavel = new JTextField();
        this.horario2 = new JLabel();
        this.horario2Editavel = new JTextField();
        this.adicionarHorarios = new JButton("Adicionar Horários");
        this.horariosJaCadastrados = new JLabel();
        this.adicionarHorarios1 = new JButton("Adicionar Horários");

        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        container.add(horariosJaCadastrados, c);

        c.insets = new Insets(10, 0, 10, 0);
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        horario1.setText("Primeiro Horário(HH:mm):  ");
        c.gridx = 0;
        c.gridy = 2;
        container.add(horario1, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        horario1Editavel.setPreferredSize(dimensaoTextos);
        c.gridx = 1;
        c.gridy = 2;
        container.add(horario1Editavel, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        horario2.setText("Segundo Horário(HH:mm):  ");
        c.gridx = 0;
        c.gridy = 3;
        container.add(horario2, c);

        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        horario2Editavel.setPreferredSize(dimensaoTextos);
        container.add(horario2Editavel, c);

        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(20, 0, 20, 0);
        adicionarHorarios.setPreferredSize(new Dimension(175, 40));
        adicionarHorarios.addActionListener(gerenciador);
        container.add(adicionarHorarios, c);
        
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(20, 0, 20, 0);
        adicionarHorarios1.setPreferredSize(new Dimension(175, 40));
        adicionarHorarios1.addActionListener(gerenciador);
        container.add(adicionarHorarios1, c);
        adicionarHorarios1.setVisible(false);
        
        this.setSize(500, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.updateData();

    }

    public void chamadaInicial() {
        this.updateData();
    }

    public void updateData() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horarios = "Horários já cadastrados: ";
        if (cargo != null && !cargo.getHorarios().isEmpty()) {
            for (int i = 0; i < cargo.getHorarios().size(); i = i + 2) {
                String h1 = sdf.format(cargo.getHorarios().get(i).getTime());
                String h2 = sdf.format(cargo.getHorarios().get(i + 1).getTime());

                horarios = horarios + "De: " + h1 + "h";
                horarios = horarios + " á: " + h2 + "; ";

            }
            this.horariosJaCadastrados.setText(horarios);
            this.horario1Editavel.setText("");
            this.horario2Editavel.setText("");
        }
    }

    public class GerenciadorBotoesHorario implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == adicionarHorarios) {
                Calendar h1 = Calendar.getInstance();
                Calendar h2 = Calendar.getInstance();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    h1.setTime(sdf.parse(horario1Editavel.getText()));
                    h2.setTime(sdf.parse(horario2Editavel.getText()));
                    telaCadastroCargo.getTelaCargo().getControladorCargo().verificaHorarios(cargo.getHorarios(), h1, h2);

                    cargo.getHorarios().add(h1);
                    cargo.getHorarios().add(h2);
                    telaCadastroCargo.getTelaCargo().getControladorCargo().getCargoDAO().persist();

                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais horários?", "Sucesso!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (opcao == 0) {
                        updateData();
                        setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Horários Adicionados!", "Sucesso!", JOptionPane.YES_OPTION);
                        setVisible(false);
                        updateData();
                        telaCadastroCargo.setVisible(true);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TelaContinuarCadastroHorarios.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Respeite o Formato HH:mm", "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException exc) {
                    Logger.getLogger(TelaContinuarCadastroHorarios.class.getName()).log(Level.SEVERE, null, exc);
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == adicionarHorarios1) {
                Calendar h1 = Calendar.getInstance();
                Calendar h2 = Calendar.getInstance();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    h1.setTime(sdf.parse(horario1Editavel.getText()));
                    h2.setTime(sdf.parse(horario2Editavel.getText()));
                    telaAlteracaoCargo.getTelaCargo().getControladorCargo().verificaHorarios(cargo.getHorarios(), h1, h2);

                    cargo.getHorarios().add(h1);
                    cargo.getHorarios().add(h2);
                    telaAlteracaoCargo.getTelaCargo().getControladorCargo().getCargoDAO().persist();

                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais horários?", "Sucesso!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (opcao == 0) {
                        updateData();
                        setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Horários Adicionados!", "Sucesso!", JOptionPane.YES_OPTION);
                        JOptionPane.showMessageDialog(null, "Cargo Alterado com Sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        updateData();
                        telaAlteracaoCargo.setVisible(true);
                        telaAlteracaoCargo.updateData(telaAlteracaoCargo.modelo);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TelaContinuarCadastroHorarios.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Respeite o Formato HH:mm", "Erro!", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException exc) {
                    Logger.getLogger(TelaContinuarCadastroHorarios.class.getName()).log(Level.SEVERE, null, exc);
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }
}
