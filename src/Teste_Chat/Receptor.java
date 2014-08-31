package Teste_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class Receptor {
	int port;
	byte buffer[] = new byte[1024];
	DatagramSocket socket;
	DatagramPacket pact;
	public Receptor(int _port){
		port = _port;
		pact = new DatagramPacket(buffer, buffer.length);
		iniciarSocket();

	}
	public void iniciarSocket(){
		try {
			socket = new DatagramSocket(port);
			System.out.println("servidor iniciado");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void aguardaMensagem(){
		int i = 0;
		while (true) {
			try {
				socket.receive(pact);
				String s = new String(pact.getData());
				System.out.println(s);
				BufferedReader m = new BufferedReader(new InputStreamReader(System.in));
				String saida = m.readLine();
				i++;
				buffer = ("Server :" + saida).getBytes();
				DatagramPacket enviar = new DatagramPacket(buffer, buffer.length,pact.getAddress(),pact.getPort());
				socket.send(enviar);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	

}
