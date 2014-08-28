
package Cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Cliente {

    public static void main(String[] args) throws IOException {
        
        int port = 1234;
        String group = "230.1.2.3";
        
        int tll = 1;
        
        MulticastSocket s = new MulticastSocket(port);
        s.joinGroup(InetAddress.getByName(group));
        DatagramPacket p;
        String s1="ola";
        byte buffer[] = s1.getBytes();
        
        
        p = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(group), port);
        
        
        s.send(p);
        System.out.println("Esperando mensagem ");
        while(true){
        	s.receive(p);
        	System.out.println("nome: "+p.getAddress()+"Dado: "+new String(p.getData()));
        }
        
        
        
                
    }
}
