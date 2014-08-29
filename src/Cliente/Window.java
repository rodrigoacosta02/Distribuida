package Cliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
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
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        cliente.envio(ae);
    }

    public void recebimentoPacotes(String nome, DatagramPacket receivePacket) {
        System.out.println("entrou " + receivePacket.getPort());

        display.append("\n <ONLINE>" + nome + ":" + receivePacket.getAddress() + ":" + receivePacket.getPort() + "\n"
                + new String(receivePacket.getData(), 0, receivePacket.getLength()));
        display.setCaretPosition(
                display.getText().length());
        System.out.println("saiu");
    }
}
