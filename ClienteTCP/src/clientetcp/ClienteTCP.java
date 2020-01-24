/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetcp;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * 
 */
public class ClienteTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
        String salir = "s";
        int puertoServicio = 7896;
        Socket s = new Socket("127.0.0.1",puertoServicio);
        DataInputStream entrada = new DataInputStream(s.getInputStream());
        DataOutputStream salida = new DataOutputStream(s.getOutputStream()); 
        System.err.println("Para terminar conexion escribe: exit ");
        do
        {
            System.out.print("Escribe mensaje: ");
            String entradaTeclado = "";
            Scanner entradaEscaner = new Scanner (System.in); 
            entradaTeclado = entradaEscaner.nextLine();
            salir = entradaTeclado;
            if(!"exit".equals(salir))
            {    
                salida.writeUTF(entradaTeclado);
                String datos = entrada.readUTF();       
                System.out.println("-Servidor: " + datos);
            }
           
        }while(!"exit".equals(salir));
        
        s.close();
                           
        }catch(UnknownHostException e)
        {System.err.println("Socket: "+ e.getMessage());}
        catch(EOFException e){
            System.err.println("EOF: " + e.getMessage() );
            System.out.println("*****El servidor ha cerrado la conexion****");
            System.out.println("*****Para conectar de nuevo volver a correr codigo CLIENTE****");
        }
        catch(IOException e)
        {
            System.err.println("IO: " + e.getMessage());
        }
    }
    
}
