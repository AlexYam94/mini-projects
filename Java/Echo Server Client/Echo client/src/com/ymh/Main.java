package com.ymh;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.logging.SocketHandler;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try (Socket socket = new Socket("localhost",5000)){

            socket.setSoTimeout(5000);

            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(),true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;
            do{
                System.out.println("Enter String to be echoed: ");
                echoString = scanner.nextLine();

                //send string to server
                stringToEcho.println(echoString);

                if(!echoString.equals("exit")){
                    //read data coming back from server
                    response = echoes.readLine();
                    System.out.println(response);
                }
            }while(!echoString.equals("exit"));
        }catch (SocketTimeoutException e) {
            System.out.println("Client Error: " + e.getMessage());
        }catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
