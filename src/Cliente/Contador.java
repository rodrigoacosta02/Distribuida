package Cliente;

import java.util.Timer;

import Gerenciador.Usuario;

public class Contador {
	
	private Usuario participantes[];
	
	public void atualizarLista(){
		Timer t = new Timer();
		
		
	}
	public void imprimir(){
		for (Usuario u : participantes) {
			System.out.println("\nNome: "+u.getNome());
		}
	}
}
