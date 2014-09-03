package cliente;

import gui.Janela;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Socket Multicast
 * 
 * @author 
 */
@SuppressWarnings("serial")
public class Cliente{

        private DatagramPacket sendPacket, receivePacket;
	private MulticastSocket socket;
	private InetAddress grupo;
	private final String nome;
	private final int portaMulticast = 12347;
        private final int portaDatagramSocket;
        
	public Cliente(String _nome, int portaDatagramSocket) {

		this.nome = _nome;
                this.portaDatagramSocket = portaDatagramSocket;
		try {
			socket = new MulticastSocket(portaMulticast);
			grupo = InetAddress.getByName("230.1.2.3");
			socket.joinGroup(grupo);
		} catch (IOException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}

    public String getNome() {
        return nome;
    }

        /**
         * recebe pacotes multicast e os trata
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
				String[] pacote= nome.split(" ",4); 
                        // [0]-TIPO ## [1]- nome ## [2] - portaDatagram ## [3] - mensagem
				
                            switch (pacote[0]) {
                                case "ONLINE":
                                    contador.adicionarParticipante(pacote[1], receivePacket.getAddress(), Integer.parseInt(pacote[2].trim()));
                                    if(contador.isNovoParticipante()){
                                        imprimirJanelaCliente = false;
                                        janela.recebimentoPacotes(contador.imprimirParticipantes(), imprimirJanelaCliente);
                                    }
                                    break;
                                case "MSG":
                                    msg=  "\n" + pacote[0]+ ":" + pacote[1] + ":"
                                            + "\n" + pacote[3] + "\n";
                                    imprimirJanelaCliente = true;
                                    janela.recebimentoPacotes(msg, imprimirJanelaCliente);
                                    
                                    break;
                                case "OFFLINE":
                                    contador.removerParticipante(receivePacket.getAddress(), Integer.parseInt(pacote[2]));
                                    msg=  "\n" + pacote[0] + pacote[1]+ " : " + receivePacket.getAddress() + " : "
                                            + pacote[2] + "\n"; 
                                    imprimirJanelaCliente = false;
                                    janela.recebimentoPacotes(msg, imprimirJanelaCliente);
                                    janela.recebimentoPacotes(contador.imprimirParticipantes(), imprimirJanelaCliente);
                                    break;
                            }
				contador.verificarTempo();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

        /**
         * reestrutura msg de envio e a envia
         * 
         * @param e recebe acao da Window
         */
	public synchronized void envio(ActionEvent e) {
		try {
			String texto = "MSG " + this.nome + " " 
                                + portaDatagramSocket+ " " ;

			String msg = e.getActionCommand();
			texto = texto.concat(msg); 
			byte data[] = texto.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, portaMulticast);
			socket.send(sendPacket);

		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
        
        /**
         * Define msg automatica e envia msg
         */
	private void msgAutomatica(){
		try {
			String s =  "ONLINE "+this.nome 
                                + " " + this.portaDatagramSocket;
		;
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, portaMulticast);
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
			String s =  "OFFLINE "+nome + " " + portaDatagramSocket;
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket(data, data.length,
					grupo, portaMulticast);
			socket.send(sendPacket);			

		} catch (IOException exception) {
			exception.printStackTrace();
		}
        }
}