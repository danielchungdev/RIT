package client;

import common.ChatterboxProtocol;
import server.Server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements ChatterboxProtocol{

    public void connect(String username){
        System.out.println( CONNECT + SEPARATOR + username );
    }

    public synchronized static void main(String[] args) throws IOException {
        final String HOST = "localhost";
        Socket socket = new Socket( HOST, PORT );
        boolean condition = true;

        PrintWriter out = new PrintWriter( socket.getOutputStream(), true );
        BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

        System.out.println( "Chatterbox Server host: " + HOST );
        System.out.println( "Chatterbox server port: " + PORT );
        System.out.print( "Username: ");
        Scanner username = new Scanner( System.in );
        out.println( username.nextLine() );

        System.out.println( "Welcome to Chatterbox! type '/help' to see a list of commands.");
        Scanner prompt = new Scanner( System.in );

        while (true){
            String[] text = prompt.nextLine().split( " " );
            if (text[0].equals( "/quit" )){
                System.out.print( "Are you sure (y/n): " );
                Scanner option = new Scanner( System.in );
                if (option.nextLine().equals( "y" )){
                    System.out.println( "Goodbye!" );
                    break;
                }else if (option.nextLine().equals( "n" )){
                    continue;
                }
            }else if (text[0].equals( "/help" )){
                System.out.println( "/help - display this message" );
                System.out.println( "/quit - quit Chatterbox" );
                System.out.println( "/c <message> - send a message to all currently connected users" );
                System.out.println( "/w <recipient> <message> - send a private message tot he recipient" );
                System.out.println( "/list - display a list of currently connected users" );
            }else if (text[0].equals("/c")){
                out.println( text[1] );
            }
        }

        socket.shutdownInput();
        socket.shutdownOutput();


    }
}
