package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class server { 
    private static  int port = 3333;

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        ServerAcces();
    }
    private static void ServerAcces() throws IOException  {
        Socket link= null;

        serverSocket = new ServerSocket(port);

        try{
         link = serverSocket.accept();

           System.out.println("connectd accept...");
        }catch(IOException e){
            System.out.println("unable to read data..");
        }    
        Scanner  input = new Scanner(link.getInputStream());
        PrintWriter output = new PrintWriter(link.getOutputStream());
        int attempt = 0;
        String ACK = "ack: ";
        String massage= " ";
        int masaj = input.nextInt();
        do{
                    massage = input.nextLine();
                    System.out.println(massage);
                    output.println(ACK+attempt);
                    output.flush();
                    attempt++;
           }while(attempt-1  <= masaj);
        System.out.println();
        try{
            link.close();
            serverSocket.close();
            System.out.println("connect closing");
        }catch(IOException e){
            System.out.println("connected closing error");
        }
    }
    }
