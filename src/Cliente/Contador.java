package Cliente;

import java.awt.List;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import Gerenciador.Usuario;

public class Contador {
	
	
	private LinkedList<Usuario> participantes;
	public Contador(){
		participantes = new LinkedList<Usuario>();
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
			System.out.println("\n "+us.getNome()+"\n tempo "+ us.getTempo());
		}
	}
	public void add(String nome, InetAddress address, int port){
		Timer t = new Timer();
		final int pos = userExistente(address);
		if(pos == -1){
			Usuario u = new Usuario(nome,address.getHostAddress(),port);
			participantes.add(u);
			
		}else{
			participantes.get(pos).zerarTempo();
		}
		
	}
	public void tempo(){
		
	}
	public int userExistente(InetAddress add){
		int i = 0;
		for(Usuario us : participantes){
			if(us.getIp().equals(add.getHostAddress())){		
				return i;
			}
			i++;
		}
		return -1;
	}
	public class TempoAtual implements Runnable{
		int tempo = 0;
		Timer t = new Timer();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			
			
		}
		
	}
	public void verificarTempo() {
		for (Usuario us : participantes) {
			if(us.getTempo() > 10){
				participantes.remove(us);
			}
		}
		
	}
}
