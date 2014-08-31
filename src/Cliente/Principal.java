package Cliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Principal {

    public static void main(String[] args) throws IOException {

        Cliente app = new Cliente("Rodrigo");
        Janela janela = new Janela(app);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
//                    Window windowCliente = new Window(app);
//                    windowCliente.addWindowListener(
//                       new WindowAdapter() {
//                          public void windowClosing( WindowEvent e )
//                          {
//                             System.exit( 0 );
//                          }
//                       }
        //        );
        //        Window windowServer = new Window("server e usuarios", app);

        app.enviarMensagem();
        app.waitForPackets(janela);

    }
}
