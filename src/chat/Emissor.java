package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Socket Datagrama - envia pacotes datagrama
 * @author
 */
public class Emissor {
	private byte buffer[];
	private final int porta;
	private DatagramSocket server;
	
        public Emissor() {
		this.porta = new Random().nextInt(9000) + 50000;
                System.out.println("porta -> " + this.porta);
		buffer = new byte[1024];
            try {
                this.server = new DatagramSocket();
            } catch (SocketException ex) {
                Logger.getLogger(Emissor.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

        public int getPorta() {
            return porta;
        }
        
        /**
         * Metodo que envia a msg especifica para o endereco ip e a porta informados
         * nos campos
         * 
         * @param ip
         * @param porta
         * @param msg
         * @param chat
         * @throws IOException 
         */
	public void comunicar(String ip, String porta, String msg, gui.Chat chat) throws IOException{
                InetAddress ip_destino = InetAddress.getByName(ip);
		int portaDestino = Integer.parseInt(porta);

                buffer = msg.getBytes();
                DatagramPacket mandar = new DatagramPacket(buffer, buffer.length, 
                                        ip_destino, portaDestino);
                server.send(mandar);
 
                chat.imprimirMsg(msg);
	}

}