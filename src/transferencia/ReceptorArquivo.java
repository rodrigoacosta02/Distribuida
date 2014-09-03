package transferencia;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente Socket Stream
 * @author 
 */
public class ReceptorArquivo {
	private  Socket socket;
	private FileOutputStream saida;
	private InputStream stream;
        private final int portaTCP;
        /**
         * Instancia socket cliente
         * @param ip 
         * @param porta 
         */
        public ReceptorArquivo(String ip, int porta){
		saida = null;
		stream = null;
                portaTCP = porta;
            try {
                socket = new Socket(ip, portaTCP);
            } catch (IOException ex) {
                Logger.getLogger(ReceptorArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
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
		int qntidadeLida;
		
                System.out.println("iniciando recepcao");
                while((qntidadeLida = stream.read(receber)) != -1){			
			saida.write(receber,0,qntidadeLida);			
			saida.flush();
		}
                socket.close();
                saida.close();
                stream.close();
		System.out.println("fim da transferencia");
	}
	
}