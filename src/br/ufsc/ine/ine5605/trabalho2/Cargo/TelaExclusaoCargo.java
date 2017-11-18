package br.ufsc.ine.ine5605.trabalho2.Cargo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Vinicius Cerqueira Nascimento
 * @author Marina Ribeiro Kodama
 * @author Marco Aurélio Geremias
 */
public class TelaExclusaoCargo extends JFrame {
    private final TelaCargo telaCargo;
    private JComboBox cargoEditavel;
    private JLabel cargo;
    private JButton voltar;
    private JButton excluir;
    private JButton encerrar;
    
    public TelaExclusaoCargo(TelaCargo telaCargo){
        super("Tela de Exclusão de Cargos");
        this.telaCargo = telaCargo;
        this.inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        Dimension dimensaoTextos = new Dimension(200, 30);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.cargoEditavel = new JComboBox();
        this.cargo = new JLabel();

        this.voltar = new JButton("Voltar ao Menu de Cargos");
        this.excluir = new JButton("Excluir o Cargo Selecionado");
        this.encerrar = new JButton("Sair");

        GerenciadorBotoesExclusao gerenciadorBotoesExclusao = new GerenciadorBotoesExclusao();
        this.voltar.addActionListener(gerenciadorBotoesExclusao);
        this.excluir.addActionListener(gerenciadorBotoesExclusao);
        this.encerrar.addActionListener(gerenciadorBotoesExclusao);

        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(50, 0, 0, 20);
        cargo.setText("Selecione o Cargo:  ");
        container.add(cargo, c);

        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.EAST;
        container.add(cargoEditavel, c);

        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.CENTER;
        c.anchor = GridBagConstraints.CENTER;
        excluir.setPreferredSize(dimensaoTextos);
        container.add(excluir, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        voltar.setPreferredSize(dimensaoTextos);
        container.add(voltar, c);

        c.gridx = 2;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;
        encerrar.setPreferredSize(dimensaoTextos);
        container.add(encerrar, c);

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void updateData(){
        cargoEditavel.removeAllItems();
        for(int i = 0; i < this.telaCargo.getControladorCargo().getCargos().size(); i++){
            cargoEditavel.addItem(this.telaCargo.getControladorCargo().getCargos().get(i));
        }
    }
    
    private class GerenciadorBotoesExclusao implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Falta verificar também se existem funcionarios com o cargo a ser excluído antes da exclusão
            //Sugestão: implementar o método findFuncionarioByCargo no ControladorFuncionario para realizar a busca
            if(e.getSource() == excluir){
                if(cargoEditavel.getSelectedItem() != null){
                    try{
                        //telaCargo.getControladorCargo().getControladorPrincipal().getControladorFuncionario().findFuncionarioByCargo((Cargo)cargoEditavel.getSelectedItem());
                        telaCargo.getControladorCargo().excluirCargo((Cargo)cargoEditavel.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Cargo excluído!");
                    }
                    catch(IllegalArgumentException ex){
                        JOptionPane.showMessageDialog(null, "Erro desconhecido. Contate o administrador do sistema.");
                    }
                }
                updateData();
            }
            else if(e.getSource() == voltar){
                setVisible(false);
                telaCargo.setVisible(true);
            }
            else if(e.getSource() == encerrar){
                System.exit(0);
            }
        }
        
    }
}
