
package computacaodistribuida;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        
        int port = 1234;
        String group = "230.1.2.3";
        
        int tll = 1;
        
        MulticastSocket s = new MulticastSocket();
        
        
        
     
        

        DatagramPacket p;
        String s1="ola";
        byte buffer[] = s1.getBytes();
        
        
        p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(group), port);
        
        
        s.send(p);
        
        s.close();
                
    }
}
