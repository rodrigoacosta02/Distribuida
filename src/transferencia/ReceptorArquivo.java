package transferencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Cliente Socket Stream
 * @author 
 */
public class ReceptorArquivo {
	private final Socket socket;
	private FileOutputStream saida;
	private InputStream stream;

        /**
         * Instancia socket cliente
         * @param porta
         * @param ip 
         * @throws UnknownHostException
         * @throws IOException 
         */
        public ReceptorArquivo(int porta,String ip) throws UnknownHostException, IOException{
		saida = null;
		stream = null;
		socket = new Socket(ip, porta);
	}
	
        /**
         * recebe arquivo enviado
         * 
         * @param path_name caminho do diretorio a ser salvo
         * @throws IOException 
         */
        public void receberArquivo(String path_name) throws IOException{
		saida = new FileOutputStream(path_name);
		stream = socket.getInputStream();
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