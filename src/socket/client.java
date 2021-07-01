package socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
public class client {
    private static int port =1237;
    public static void main(String[] args) throws IOException {
        ClientAccess();
    }
    private static  void ClientAccess() throws SocketException, IOException  {
        Socket link = null;
        link = new Socket("localhost",port);
        Scanner input = new Scanner(link.getInputStream());
       PrintWriter output = new PrintWriter(link.getOutputStream());
        int i = 0;
        Scanner user = new Scanner(System.in);
        String packag=" PCK ";
        String response;
        long startTime = System.nanoTime();
        System.out.println("paket sayısını giriniz: ");
        int mesaj = user.nextInt();
        int attempt =0;
        output.println(mesaj);
        output.flush();
        do{
             output.println(packag+ ":"+attempt);
             output.flush();
             response = input.nextLine();
             System.out.println(response);
           //  link.setSoTimeout(10000);
             attempt++;
            
            
        }while(attempt <= mesaj);
        long endTime = System.nanoTime();
        System.out.println("Time:"+(endTime-startTime));
        try{
            link.close();
            System.out.println("connect closing");
        }catch(IOException e){
            System.out.println("error to close connected");
        }
    }}
