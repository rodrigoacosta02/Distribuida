
package Cliente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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

        app.waitForPackets(win);
        
                
    }
}
