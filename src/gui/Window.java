package gui;

import Cliente.Cliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {

    private JTextField enter;
    private JTextArea display;
    private Cliente cliente;

    public Window(Cliente cli) {
        cliente = cli;
        enter = new JTextField();
        enter.addActionListener(this);
        getContentPane().add(enter, BorderLayout.NORTH);
        display = new JTextArea();
        getContentPane().add(new JScrollPane(display),
                BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public Window(String s, Cliente cli) {
        cliente = cli;
        enter = new JTextField(s);
        enter.setEditable(false);
        getContentPane().add(enter, BorderLayout.NORTH);
        display = new JTextArea();
        getContentPane().add(new JScrollPane(display),
                BorderLayout.CENTER);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * envia msg de usuario
     * 
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        cliente.envio(ae);
    }
    
    /**
     * recebe msg e mostra na tela
     * 
     * @param msg 
     */
    public void recebimentoPacotes(String msg) {
        display.append(msg);
        display.setCaretPosition(
                display.getText().length());
    }
}
