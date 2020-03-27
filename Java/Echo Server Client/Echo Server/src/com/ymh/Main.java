package com.ymh;

import com.sun.security.ntlm.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try (ServerSocket serverSocket = new ServerSocket(5000)){


            while(true){
                new Echoer(serverSocket.accept()).start();


//                //not multi thread this way.
//                Socket socket = serverSocket.accept();
//                System.out.println("Client Connected");
//
//                //get input from client
//                BufferedReader input = new BufferedReader(
//                        new InputStreamReader(socket.getInputStream()));
//                //output to client
//                //true = autoFlush
//                PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
//
//                //get input from client
//                String echoString = input.readLine();
//
//                try{
//                    Thread.sleep(10000);
//                }catch (InterruptedException e){
//                    System.out.println("Thread interrupted");
//                }
//
//                if(echoString.equals("exit")){
//                    break;
//                }
//                //output to client
//                System.out.println("Input from client: " + echoString);
//                output.println("Echo from server JOJO:" + echoString);
            }
        }catch (IOException e){
            System.out.println("Server exception "+ e.getMessage());
        }
    }
}
