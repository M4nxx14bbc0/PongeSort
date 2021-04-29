/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parrarodriguez.manue
 */
public class ServerMain {
    private static ServerSocket socket;
    
    /*
    * @param args gfcvyhv
    */
    public static void main (String[] args){
        try {
            socket = new ServerSocket(5500);
            Socket client = socket.accept();
            System.out.println(client.getInetAddress());
            while (true) {
                ObjectInputStream reader = new ObjectInputStream(client.getInputStream());
                ObjectOutputStream writer = new ObjectOutputStream(client.getOutputStream());
                
                char[] s = (char[])(reader.readObject());
                Character[] array = new Character[10];
                for (int i = 0; i < array.length; i++) {
                    array[i] = s[(int)(Math.random()*9+1)];
                }
                writer.writeObject(array);
                writer.flush();
                writer.close();
                reader.close();
                if (client.isClosed()) {
                    break;
                }
            }
            socket.close();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.OFF, null, ex);
        }
    }
}
