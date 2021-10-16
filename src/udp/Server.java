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

/**
 *
 * @author leone
 */
public class Server {
    
    public static void main(String [] args) throws IOException{
        
        System.out.println("----- SERVIDOR 5IV8 -----");
        
        DatagramSocket server = new DatagramSocket(54487);
        
        byte [] receiveData = new byte [1024];
        
        byte [] sendData = new byte [1024];
        
        while(true){
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            server.receive(receivePacket);
            
            String sentence = new String(receivePacket.getData());
            
            System.out.println("Mensaje recibido: " + new String(receivePacket.getData()));
            
            InetAddress ipaddress = receivePacket.getAddress();
            
            int port = receivePacket.getPort();
            String z = sentence.toUpperCase();
            
            sendData = z.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ipaddress, port);
            
            server.send(sendPacket);
        }
        
    }
    
}
