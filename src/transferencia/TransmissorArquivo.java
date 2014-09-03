package transferencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor Socket Stream
 * @author 
 */
public class TransmissorArquivo {
	private OutputStream saida;
	private FileInputStream arq;
	private final ServerSocket sever;
        
        /**
         * Instancia server para porta especificada
         * 
         * @param porta porta para a qual sera enviado o arquivo
         * @throws IOException 
         */
        public TransmissorArquivo(int porta) throws IOException{
                saida = null;
                arq = null;
                sever = new ServerSocket(porta,10);
        }
        
        /**
         * aceita a conexao de socket
         * inicia transferencia de arquivo entre os sockets
         * 
         * @param path_name caminho do arquivo para envio
         * @throws IOException 
         */
        public void iniciarTransferencia(String path_name) throws IOException{
                Socket socket = sever.accept();
                System.out.println("iniciando transferencia");
                int read;
                byte buff[] = new byte[1024];

                File f = new File(path_name);
                arq = new FileInputStream(f);
                saida = socket.getOutputStream();
                System.out.println("enviando");
               
                while ((read = arq.read(buff)) != -1) {
                    saida.write(buff, 0, read);
                    saida.flush();
                }
                sever.close();
                saida.close();
                arq.close();
                System.out.println("fim da transferencia");
        }
}