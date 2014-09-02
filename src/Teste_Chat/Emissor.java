package Teste_Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class Emissor {
	byte buffer[];
	int porta;
	DatagramPacket msg;
	DatagramSocket server;
	InetAddress host;
	
        public Emissor(int _p) throws Exception{
//		this.porta = _p;
		this.porta = new Random().nextInt(9000) + 1000;
                System.out.println("porta -> " + this.porta);
		buffer = new byte[1024];
		this.msg = new DatagramPacket(buffer, buffer.length);
		this.server = new DatagramSocket();
		this.host = InetAddress.getLocalHost();
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
                
                
                //windows host.getHostAddress() == ip da maquina
                //linux host.getHostAddress() == 127.0.0.1
                msg = "\n" + host.getHostAddress()+ ":" + porta + "\n" 
                           +  new String(mandar.getData(), 0, mandar.getLength());
                chat.imprimirMsg(msg);
	}

}
