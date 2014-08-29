package Gerenciador;

import java.util.Timer;
import java.util.TimerTask;

public class Usuario {
	String nome;
	String ip;
	int porta;
	int tempo;
	public Usuario(String hostName, String hostAddress, int port) {
		nome = hostName;
		ip = hostAddress;
		porta = port;
		tempo = 0;
		contarTempo();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public void zerarTempo() {
		tempo = 0;
		
	}
	public int getTempo() {
		// TODO Auto-generated method stub
		return tempo;
	}
	public void contarTempo(){
		TimerTask tt = new TimerTask() {
			
			@Override
			public void run() {
				tempo++;
				//System.out.println(tempo+"\t" + nome);
				
			}
		};
		Timer temp = new Timer();
		temp.scheduleAtFixedRate(tt, 0, 1000);
	}
	
	
}
