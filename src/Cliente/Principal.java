
package Cliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import Cliente.Cliente.ExecucaoMensagem;

public class Principal {

    public static void main(String[] args) throws IOException {

        Cliente app = new Cliente("Rodrigo");
        Window win = new Window(app);
        win.addWindowListener(
           new WindowAdapter() {
              public void windowClosing( WindowEvent e )
              {
                 System.exit( 0 );
              }
           }
        );
        Thread envio = new Thread(app.new ExecucaoMensagem());
        //app.enviarMensagem();
        //app.waitForPackets(win);
        app.receberMsgAutomatica();
        
        
        
        
        
                
    }
}
