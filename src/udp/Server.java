/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author leone
 */
public class Server {
    
    public static void main(String [] args) throws IOException{
        
        System.out.println("----- SERVIDOR 5IV8 -----");
        
        DatagramSocket server = new DatagramSocket(50838);
        Scanner sc = new Scanner(System.in);
        
        while(true){
            
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            InetAddress ipaddress = receivePacket.getAddress();
            
            int puerto = receivePacket.getPort();
            
            //ahora interpretamos el contenido (mensajes) enviados por parte del cliente
            String clientData = new String(receivePacket.getData());
            
            System.out.println("\nCliente: " + clientData);
            System.out.println("\nServidor: ");
            
            String serverData = sc.nextLine();
            sendData = serverData.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipaddress, puerto);
            server.send(sendPacket);
            
            if(serverData.equalsIgnoreCase("bye")){
                System.out.println("Conexión terminada por el servidor");
                break;
            }
            
        }
        server.close();
    }

    
}
