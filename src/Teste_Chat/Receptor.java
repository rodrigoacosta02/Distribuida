package Teste_Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class Receptor extends Thread {
	private int port;
	private byte buffer[] = new byte[1024];
	private DatagramSocket socket;
	private DatagramPacket pact;
        private gui.Chat chat;
     
        public Receptor(int _port, gui.Chat chat){
		port = _port;
		pact = new DatagramPacket(buffer, buffer.length);
		iniciarSocket();
                this.chat = chat;
	}

        private void iniciarSocket(){
		try {
			socket = new DatagramSocket(port);
			System.out.println("servidor iniciado");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

    @Override
    public void run() {
		
		while (true) {
			try {
				socket.receive(pact);
                                String msg = "\n" + pact.getAddress()+ ":" + pact.getPort() + "\n"
                                        +  new String(pact.getData(), 0 , pact.getLength());
                                chat.imprimirMsg(msg);
                                
			} catch (IOException e) {
				e.printStackTrace();
			}
		}    
    }
	
}
