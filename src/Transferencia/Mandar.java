package Transferencia;

import java.io.IOException;

public class Mandar {

	public static void main(String[] args) {
		try {
			TransmissorArquivo t=new TransmissorArquivo(1234);
			String s = "/home/user/Sistemas-Distribuidos-Conceitos-e-Projeto-Livro.pdf";
			t.iniciarTransferencia(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
