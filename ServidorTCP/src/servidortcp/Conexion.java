/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortcp;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Leo
 */
public class Conexion extends Thread {
    DataInputStream entrada;
    DataOutputStream salida;
    Socket socketCliente;
    public Conexion(Socket unSocketDeCliente)
    {
      try{
        socketCliente = unSocketDeCliente;
        entrada = new DataInputStream(socketCliente.getInputStream());
        salida = new DataOutputStream(socketCliente.getOutputStream());
        this.start();
      
      }catch(IOException e)
      {
          System.out.println("Conexion: "+e.getMessage());
      }
      
    }
    
    public void run()
    {
     try{
        String salir = "s";
        System.err.println("Para terminar conexion escribe: exit ");
        do{
            String datos = entrada.readUTF();       
            System.out.println("-Cliente: "+ datos);
            System.out.print("Escribe mensaje: ");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); 
            entradaTeclado = entradaEscaner.nextLine();
            salir = entradaTeclado;
            if(!"exit".equals(salir))
            {
             salida.writeUTF(entradaTeclado);
            }                                  
        }while(!"exit".equals(salir));                               
        socketCliente.close();
       
        
     }catch(EOFException e)
     {
         System.out.println("EOF: " + e.getMessage());
         System.out.println("*****El cliente ha abandonado la conversacion****");
         System.out.println("*****Esperando a un nuevo cliente****");
     }catch(IOException e)
     {
         System.out.println("IO: " + e.getMessage());
     }
    }
}
