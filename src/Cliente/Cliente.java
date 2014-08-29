
package Cliente;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Cliente extends JFrame implements ActionListener {
	private JTextField enter;
	private JTextArea display;
	private String nome;

	private DatagramPacket sendPacket, receivePacket;
	private MulticastSocket socket;
	private int porta = 1234;
	private InetAddress grupo;

	public Cliente(String _nome)
	{

		enter = new JTextField();
		this.nome = _nome;
		enter.addActionListener( this );
		getContentPane().add( enter, BorderLayout.NORTH );
		display = new JTextArea();
		getContentPane().add( new JScrollPane( display ),
				BorderLayout.CENTER );
		setSize( 400, 300 );
		setVisible(true);

		try {
			socket = new MulticastSocket(porta);
			grupo = InetAddress.getByName("230.1.2.3");
			socket.joinGroup(grupo);
		}
		catch( IOException se ) {
			se.printStackTrace();
			System.exit( 1 );
		}

	}

	public void waitForPackets()
	{
		while ( true ) {
			try {
				// set up packet
				byte data[] = new byte[ 100 ];
				receivePacket =	new DatagramPacket( data, data.length );
				// wait for packet
				socket.receive( receivePacket );
				// process packet
				/*display.append( "\nPacket received:" +
	               "\nFrom host: " + receivePacket.getAddress() +
	               "\nHost port: " + receivePacket.getPort() +
	               "\nLength: " + receivePacket.getLength() +
	               "\nContaining:\n\t" +
	               new String( receivePacket.getData(), 0,
	                           receivePacket.getLength() ) );*/
				display.append( "\n <ONLINE>"+nome +":"+receivePacket.getAddress()+":"+receivePacket.getPort()+"\n"
						+new String(receivePacket.getData(),0,receivePacket.getLength()) );
				display.setCaretPosition(
						display.getText().length() );
			}
			catch( IOException exception ) {
				display.append( exception.toString() + "\n" );
				exception.printStackTrace();
			}
		}
	}

	public synchronized void actionPerformed( ActionEvent e )
	{
		try {
			//display.append( "\n <ONLINE>"+nome +":"+sendPacket.getAddress()+":"+sendPacket.getPort()+"\n" );

			String s = e.getActionCommand();
			byte data[] = s.getBytes();
			sendPacket = new DatagramPacket( data, data.length,
					grupo, 1234 );
			socket.send( sendPacket );
			display.append( "Packet sent\n" );
			display.setCaretPosition(
					display.getText().length() );


		}
		catch ( IOException exception ) {
			display.append( exception.toString() + "\n" );
			exception.printStackTrace();
		}
	}
}




