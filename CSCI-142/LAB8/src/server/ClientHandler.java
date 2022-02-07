package server;

import client.Client;
import common.ChatterboxProtocol;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ClientHandler implements Runnable, ChatterboxProtocol {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;

    public ClientHandler(Socket socket)throws IOException{
        this.socket = socket;
        this.out = new PrintWriter( socket.getOutputStream(), true );
        this.in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
    }

    public void run(){
        try {
            name = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println( "<<unknown user: " + CONNECT + SEPARATOR + name );
        System.out.println( ">>" + name + ": " + CONNECTED);

        }
//        System.out.println( scanner );
//        while (true){
//            String[] line = scanner.nextLine().split( " " );
//            if (line[0].equals( "/quit" )){
//                break;
//            }
//            printer.println( line );
//        }
//        try {
//            close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    public void close() throws IOException {
        socket.shutdownInput();
        socket.shutdownOutput();
    }
}
