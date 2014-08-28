package Gerenciador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMultiCast {

	public static void main(String[] args) throws IOException {
		GerenciadorDoGrupo m = new GerenciadorDoGrupo(1234, "230.1.2.3");
		m.iniciarGrupo();
		
		

	}

}
