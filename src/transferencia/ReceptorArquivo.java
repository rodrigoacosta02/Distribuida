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
        private final int portaTCP;
        /**
         * Instancia socket cliente
         * @param ip 
         * @param porta 
         * @throws UnknownHostException
         * @throws IOException 
         */
        public ReceptorArquivo(String ip, int porta) throws UnknownHostException, IOException{
		saida = null;
		stream = null;
                portaTCP = porta;
		socket = new Socket(ip, portaTCP);
	}

    public int getPortaTCP() {
        return portaTCP;
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