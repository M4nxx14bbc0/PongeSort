/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;
import java.util.*;
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
                ObjectInputStream sc = new ObjectInputStream(client.getInputStream());
                char[] s = (char[])(sc.readObject());
                for (char c : s) {
                    System.out.println(c);
                }
            }
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.OFF, null, ex);
        }
    }
}
