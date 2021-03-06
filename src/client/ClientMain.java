/*
 * Client genera array da 10
 * Caratteri letterari maiuscoli e miniscoli
 * Client manda al Server l'array
 * Server riceve il primo carattere
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author parrarodriguez.manue
 */
public class ClientMain {
    private static Socket server;
    private static final char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            server = new Socket("127.0.0.1", 5500);
            Character[] array = new Character[10];
            for (int i = 0; i < array.length; i++) {
                char c = chars[(int)(Math.random()*25+1)];
                if ((int)(Math.random()) == 1) {
                    c = Character.toLowerCase(c);
                }
                array[i] = c;
            }
            
            char[] s;
            do {
                ObjectOutputStream writer = new ObjectOutputStream(server.getOutputStream());
                ObjectInputStream reader = new ObjectInputStream(server.getInputStream());
                
                writer.writeObject(array);
                writer.close();
                s = (char[]) reader.readObject();
                reader.close();
            } while (!s.equals(array));
            server.close();
        } catch (IOException io) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.OFF, null, io);
        }
    }
    
}
