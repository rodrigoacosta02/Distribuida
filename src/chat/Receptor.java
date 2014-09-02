package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Socket Datagrama Receptor - recebe pacotes datagrama
 * @author
 */
public class Receptor extends Thread {

    private final int port;
    private byte buffer[] = new byte[1024];
    private DatagramSocket socket;
    private DatagramPacket pacote;
    private gui.Chat chat;

    public Receptor(int _port, gui.Chat chat) {
        port = _port;
        pacote = new DatagramPacket(buffer, buffer.length);
        iniciarSocket();
        this.chat = chat;
    }
    
    private void iniciarSocket() {
        try {
            socket = new DatagramSocket(port);
            System.out.println("servidor iniciado");
        } catch (SocketException ex) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                socket.receive(pacote);
                String msgPacote = new String(pacote.getData(), 0, pacote.getLength());
                
                String msg1 = "\n" + pacote.getAddress() + ":" + pacote.getPort() + "\n"
                        + msgPacote;
                chat.imprimirMsg(msg1);
                
                String partesString [] = msgPacote.split(" ");
                switch (partesString[0]) {
                    case "FILE":
                        chat.receberArquivo(pacote.getAddress(), partesString[3]);
                        break;
                    case "OK":
                        chat.enviarArquivo(partesString[3].trim());
                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
