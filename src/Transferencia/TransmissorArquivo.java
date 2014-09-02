package Transferencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TransmissorArquivo {
	private OutputStream saida;
	private FileInputStream arq;
	private ServerSocket sv;
		public TransmissorArquivo(int porta) throws IOException{
			saida = null;
			arq = null;
			sv = new ServerSocket(porta);
			
		}
		public void iniciarTransferencia(String path_name) throws IOException{
			Socket socket = sv.accept();
			System.out.println("iniciando transferencia");
			int read = 0;
			byte buff[] = new byte[1024];
			
			File f = new File(path_name);
			arq = new FileInputStream(f);
			saida = socket.getOutputStream();
			System.out.println("mandando");
			read = arq.read(buff);
				while(read != -1){					
					saida.write(buff,0,read);
					read = arq.read(buff);
					saida.flush();					
				}
				System.out.println("fim da tf");
		}
		
}
