package principal;

import Cliente.Cliente;
import gui.Janela;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Principal {

    public static void main(String[] args) throws IOException {

        final Cliente app = new Cliente("Rodrigo");
        Janela janela = new Janela(app);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                app.fechandoJanela();
                System.exit(0);
            }
        });

        app.enviarMensagem();
        app.waitForPackets(janela);

    }
}
