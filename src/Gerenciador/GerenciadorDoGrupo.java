package Gerenciador;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GerenciadorDoGrupo {
	int porta;
	String grupo;
	DatagramPacket pacote;
	Usuario participantes[];
	
	public GerenciadorDoGrupo(int _port, String _grupo){
		this.porta = _port;
		this.grupo = _grupo;
		
	}
	@SuppressWarnings("resource")
	void iniciarGrupo(){
		MulticastSocket m;
		try {
			m = new MulticastSocket(porta);			
			m.joinGroup(InetAddress.getByName(grupo));
			System.out.println("Iniciando grupo!");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		while(true){
			
		}
		
	}	
	
	void fecharGrupo(){
		
	}
	void retirarUsuario(Usuario u){
		/*retirar usuario apos 60 segundos sem responder*/
	}
	
	
	

}
