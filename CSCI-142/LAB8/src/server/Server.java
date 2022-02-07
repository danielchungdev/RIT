package server;

import client.Client;
import common.ChatterboxProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

//NAME: DANIEL CHUNG
//LAB: MY ATTEMPT
//DATE: 3/27/2018
//*NOTE: Might not be my best lab as of today!, but I'll do well on next one.

public class Server implements ChatterboxProtocol{

    public static void main (String[] args) throws IOException {
        HashMap mapUsers = new HashMap(  );

        ServerSocket chatServer = new ServerSocket( PORT );
        while (true){
            System.out.println( "Waiting for connections on port " + PORT );
            Socket chatClient = chatServer.accept();
            System.out.println( "ChatterboxClient connection received from " + chatServer.getLocalPort());
            ClientHandler handler = new ClientHandler(chatClient);
            new Thread(handler).start();
        }
    }
}
