package Transferencia;

import java.io.IOException;


public class Receber {

	public static void main(String[] args) {
		ReceptorArquivo r;
		try {
			r = new ReceptorArquivo(1234,"192.168.1.102");
			String path = "/home/user/aeq";
			r.receberArquivo(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
