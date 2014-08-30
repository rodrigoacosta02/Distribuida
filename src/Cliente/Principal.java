
package Cliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


public class Principal {

    public static void main(String[] args) throws IOException {

        Cliente app = new Cliente("Rod");
        Window windowCliente = new Window(app);
        windowCliente.addWindowListener(
           new WindowAdapter() {
              public void windowClosing( WindowEvent e )
              {
                 System.exit( 0 );
              }
           }
        );
        Window windowServer = new Window("server e usuarios", app);
       
        //Thread envio = new Thread(app.new ExecucaoMensagem());
        app.enviarMensagem();
        app.waitForPackets(windowCliente, windowServer);
       // app.receberMsgAutomatica();
        
        
        
        
        
                
    }
}
