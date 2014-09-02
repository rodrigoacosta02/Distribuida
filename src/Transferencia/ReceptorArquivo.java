package Transferencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ReceptorArquivo {
	private Socket server;
	private FileOutputStream saida;
	private InputStream stream;
	public ReceptorArquivo(int porta,String ip) throws UnknownHostException, IOException{
		saida = null;
		stream = null;
		server = new Socket(ip, porta);
	}
	public void receberArquivo(String path_name) throws IOException{
		saida = new FileOutputStream(path_name);
		stream = server.getInputStream();
		byte receber[] = new byte[1024];
		int read = stream.read(receber);
		System.out.println("iniciando recepcao");
		while(read != -1){			
			saida.write(receber,0,read);			
			saida.flush();
			read = stream.read(receber);
                        System.out.println(".");
		}
		System.out.println("fim da transferencia");
	}
	
}
