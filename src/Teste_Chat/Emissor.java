package Teste_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Emissor {
	byte buffer[];
	int porta;
	DatagramPacket msg;
	DatagramSocket server;
	InetAddress host;
	public Emissor(int _p) throws Exception{
		this.porta = _p;
		buffer = new byte[1024];
		this.msg = new DatagramPacket(buffer, buffer.length);
		this.server = new DatagramSocket();
		this.host = InetAddress.getLocalHost();
	}
	public void comunicar() throws IOException{
		int i = 0;
		while(true){
			BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));			
			String outMessage = stdin.readLine();
			i++;
			buffer = outMessage.getBytes();
			DatagramPacket mandar = new DatagramPacket(buffer, buffer.length, host,porta);
			server.send(mandar);
			//esperar um pacote
			server.receive(msg);
			
			
			System.out.println("msg numero "+i+new String(msg.getData()));
			
		
		}
			
	}

}
