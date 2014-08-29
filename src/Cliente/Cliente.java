package Cliente;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Timer;
import java.util.TimerTask;


@SuppressWarnings("serial")
public class Cliente{

	private String nome;

	private DatagramPacket sendPacket, receivePacket;
	private MulticastSocket socket;
	private int porta = 1234;
	private InetAddress grupo;

	public Cliente(String _nome) {

		this.nome = _nome;
		try {
			socket = new MulticastSocket(porta);
			grupo = InetAddress.getByName("230.1.2.3");
			socket.joinGroup(grupo);
		} catch (IOException se) {
			se.printStackTrace();
			System.exit(1);
		}

	}


	public void waitForPackets(Window window) {
		Contador c = new Contador();
		while (true) {
			try {
				byte data[] = new byte[100];
				String msg = new String();
				receivePacket = new DatagramPacket(data, data.length);

				socket.receive(receivePacket);
				
				String nome = new String(receivePacket.getData(), 0, receivePacket.getLength());
				
				String[] pacote= nome.split(" ",2);
				
				if(pacote[0].equals("ONLINE")){
					System.out.println("online");
					c.add(receivePacket.getAddress(), receivePacket.getPort());
					
				}else{
					if(pacote[0].equals("MSG")){
						msg= "\n"+  pacote[0]+ ":" + receivePacket.getAddress() + ":" 
						+ receivePacket.getPort() + "\n" + pacote[1];
					}
				}
				
				window.recebimentoPacotes(msg);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			c.exibirLista();
		}
	}

	public synchronized void envio(ActionEvent e) {
		try {
			String texto = "MSG "+this.nome;

			String msg = e.getActionCommand();
			texto = texto.concat("@"+msg); 
			byte data[] = texto.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, 1234);
			socket.send(sendPacket);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	public void msgAutomatica(){
		try {
			String s =  "ONLINE "+nome+ "@";
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, 1234);			
			socket.send(sendPacket);			

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	public void enviarMensagem(){
		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				msgAutomatica();
			}
		};
		Timer tempo = new Timer();

		tempo.scheduleAtFixedRate(tt, 0, 2000);
		tempo = null;


	}
	public class ExecucaoMensagem implements Runnable{
		public ExecucaoMensagem(){

		}
		@Override
		public void run() {
			enviarMensagem();
		}
	}
}
