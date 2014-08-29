package Cliente;

import java.awt.List;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;

import Gerenciador.Usuario;

public class Contador {
	
	
	private ArrayList<Usuario> participantes;
	public Contador(){
		participantes = new ArrayList<Usuario>();
	}
	public void atualizarLista(){
		Timer t = new Timer();
		
		
	}
	public void imprimir(){
		for (Usuario u : participantes) {
			System.out.println("\nNome: "+u.getNome());
		}
	}
	public void adicionarParticipante(InetAddress address,int port) {
		String ip = address.getHostAddress();
		for (Usuario u : participantes) {
			System.out.println("ip param: "+address.getHostAddress()+"\t nome: "+address.getHostName());
			if(!(u.getIp().equals(ip))){
				System.out.println("addedido");
				participantes.add(new Usuario(address.getHostName(),address.getHostAddress(),port));
			}
		}
	}
	public void exibirLista(){
		for (Usuario us : participantes) {
			System.out.println("\n "+us.getNome());
		}
	}
	public void add(InetAddress address, int port){
		if(!userExistente(address)){
			participantes.add(new Usuario(address.getHostName(),address.getHostAddress(),port));
		}
	}
	public boolean userExistente(InetAddress add){
		for(Usuario us : participantes){
			if(us.getIp().equals(add.getHostAddress())){		
				return true;
			}
		}
		return false;
	}
}
