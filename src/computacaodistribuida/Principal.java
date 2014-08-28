package computacaodistribuida;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Principal {

	public static void main(String[] args) throws IOException {
		int port = 1234;
		String grupo = "230.1.2.3";
		//criar um multicast com a porta
		MulticastSocket m = new MulticastSocket(port);
		//entrar num grupo
		m.joinGroup(InetAddress.getByName(grupo));
		//receber dados
		byte dado[]=new byte[4];
		
		DatagramPacket p= new DatagramPacket(dado, 4);		
		m.receive(p);		
		System.out.println(p.getLength());

	}

}
