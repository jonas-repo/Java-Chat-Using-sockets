/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidortcp;
import java.net.*;
import java.io.*;
/**
 *
 * 
 */
public class ServidorTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
          int puertoServicio = 7896;
          ServerSocket escuchandoSocket = new ServerSocket(puertoServicio);
           
          while(true)
          {
           Socket socketCliente = escuchandoSocket.accept();
           Conexion c = new Conexion(socketCliente);
          }
        }catch(IOException e)
        {
            System.err.println("<<Escuchando: >>"+e.getMessage());
        }
    }
    
    
    
}
