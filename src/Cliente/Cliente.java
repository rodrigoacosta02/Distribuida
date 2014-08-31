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

        /**
         * recebe pacotes e os trata
         * 
         * @param janela
         */
	public void waitForPackets(Janela janela) {
		Contador contador = new Contador();
                boolean imprimirJanelaCliente = false;
		while (true) {
			try {
				byte data[] = new byte[100];
				String msg = new String();
				receivePacket = new DatagramPacket(data, data.length);

				socket.receive(receivePacket);
				
				String nome = new String(receivePacket.getData(), 0, receivePacket.getLength());
				
				String[] pacote= nome.split(" ",2);
				
                            switch (pacote[0]) {
                                case "ONLINE":
                                    //pacote[1] contem nome do usuario
                                    contador.adicionarParticipante(pacote[1], receivePacket.getAddress(), receivePacket.getPort());
                                    if(contador.isNovoParticipante()){
                                        imprimirJanelaCliente = false;
                                        janela.recebimentoPacotes(contador.imprimirParticipantes(), imprimirJanelaCliente);
                                    }
                                    break;
                                case "MSG":
                                    pacote = nome.split("@", 2);
                                    msg=  "\n" + pacote[0]+ ":" + receivePacket.getAddress() + ":"
                                            + receivePacket.getPort() + "\n" + pacote[1] + "\n";
                                    imprimirJanelaCliente = true;
                                    janela.recebimentoPacotes(msg, imprimirJanelaCliente);
                                    
                                    break;
                                case "OFFLINE":
                                    contador.removerParticipante(receivePacket.getAddress());
                                    msg=  "\n" + nome+ ":" + receivePacket.getAddress() + ":"
                                            + receivePacket.getPort() + "\n"; 
                                    imprimirJanelaCliente = false;
                                    janela.recebimentoPacotes(msg, imprimirJanelaCliente);
                                    janela.recebimentoPacotes(contador.imprimirParticipantes(), imprimirJanelaCliente);

                                    break;
                                case "FILE":
                                    break;
                                case "OK":
                                    break;
                            }
				contador.verificarTempo();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			contador.exibirLista();
		}
	}

        /**
         * reestrutura msg de envio e a envia
         * 
         * @param e recebe acao da Window
         */
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
        
        /**
         * Define msg automatica e envia msg
         */
	public void msgAutomatica(){
		try {
			String s =  "ONLINE "+nome;
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, 1234);
			socket.send(sendPacket);			

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
        
        /**
         * chama metodo msgAutomatica a cada intervalo de tempo
         */
	public void enviarMensagem(){
		final int intervaloTempo = 2000;
            
                TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				msgAutomatica();
			}
		};
		Timer tempo = new Timer();

		tempo.scheduleAtFixedRate(tt, 0, intervaloTempo);
		tempo = null;
	}

        /**
         * Metodo ao fechar janela envia pacote como OFFLINE
         */
        public void fechandoJanela() {
                try {
			String s =  "OFFLINE "+nome;
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, 1234);
			socket.send(sendPacket);			

		} catch (IOException exception) {
			exception.printStackTrace();
		}
        }
}
