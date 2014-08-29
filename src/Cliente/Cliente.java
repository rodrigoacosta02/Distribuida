package Cliente;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


@SuppressWarnings("serial")
public class Cliente{

    private String nome;

    private DatagramPacket sendPacket, receivePacket;
    private MulticastSocket socket;
    private int porta = 1234;
    private InetAddress grupo;
                
    public Cliente(String _nome) {

        this.nome = _nome;
        try {
            socket = new MulticastSocket(porta);
            grupo = InetAddress.getByName("230.1.2.3");
            socket.joinGroup(grupo);
        } catch (IOException se) {
            se.printStackTrace();
            System.exit(1);
        }

    }

    public void waitForPackets(Window window) {
        
        while (true) {
            try {
                // set up packet
                byte data[] = new byte[100];
                receivePacket = new DatagramPacket(data, data.length);
                // wait for packet
                socket.receive(receivePacket);
				// process packet
//                janela.recebimentoPacotes(nome, receivePacket);
                //MOSTRAR NOME DE QUEM ENVIOU O PACOTE
                String nome = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] pacote= nome.split("@");                
                String msg= "\n <ONLINE>" +  pacote[0]+ ":" + receivePacket.getAddress() + ":" 
                + receivePacket.getPort() + "\n"
                + pacote[1];
                window.recebimentoPacotes(msg);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public synchronized void envio(ActionEvent e) {
        try {

            String texto = this.nome;
            
        	String msg = e.getActionCommand();
        	texto = texto.concat("@"+msg); 
            byte data[] = texto.getBytes();
            sendPacket = new DatagramPacket(data, data.length,
                    grupo, 1234);
            socket.send(sendPacket);

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
