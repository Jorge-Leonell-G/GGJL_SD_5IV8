/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author leone
 */
public class Client {
    
    public static void main (String [] args) throws SocketException, UnknownHostException, IOException{
        
        System.out.println("----- CLIENTE 5IV8 -----");
        
        Scanner sc = new Scanner(System.in);
        DatagramSocket client = new DatagramSocket();
        //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        int puerto = 50838;
        InetAddress ipaddress = InetAddress.getByName("localhost");
        
        while(true){
            byte [] sendBuffer = new byte [1024];
            byte [] receiveBuffer = new byte [1024];
            System.out.println("\nCliente: ");
            String clientData = sc.nextLine();
            sendBuffer = clientData.getBytes();
            
            //creacion del DP
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, ipaddress, puerto);
            client.send(sendPacket);
            
            //a traves de esta condicion, estableceremos el cierre del socket con la palabra magica 'bye'
            if(clientData.equalsIgnoreCase("bye")){
                System.out.println("Conexión terminada por el cliente");
                break;
            }
            
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            client.receive(receivePacket);
            
            //cadena de texto para el contenido transferido (data) a lo largo del server
            String serverData = new String(receivePacket.getData());
            
            System.out.println("\nServidor: " + serverData);
        }
            //cerramos el flujo del socket
            client.close();
         
    }
    
}
