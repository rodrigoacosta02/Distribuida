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
		byte dado[]=new byte[1024];
		
		DatagramPacket p= new DatagramPacket(dado, dado.length);
		
		m.receive(p);		
		System.out.println(p.getLength()+" nome: "+p.getAddress() +" dado: "+p.getData());
		m.leaveGroup(InetAddress.getByName(grupo));
		m.close();
		

	}

}
