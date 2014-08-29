package Gerenciador;

public class Usuario {
	String nome;
	String ip;
	int porta;
	public Usuario(String hostName, String hostAddress, int port) {
		nome = hostName;
		ip = hostAddress;
		porta = port;
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
	
}
