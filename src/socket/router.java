package socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class router {
    private static int port =3333;
    private static int port2 =1237;
    private static ServerSocket serverSocket = null;
    static ArrayList <Integer> list = null;
    static Random random;
    private static int dropSize = 0;
    private static Socket link2 = null;
    public static void main(String[] args) {
        random = new Random();
        dropSize = Integer.valueOf(10) * 14/100;
        dropSize += random.nextInt(2);
        list= new ArrayList<>();
        for (int index = 0; index < dropSize; index++) {
                    if (index == 0)
                        list.add(random.nextInt(Integer.valueOf(10)));
                }
         try{
        serverSocket = new ServerSocket(port2);
         link2= new Socket("localhost",port);
        
        } catch (IOException ex) {
            System.out.println("port no connected..");
        }
        handleClient();
    }
    public static void handleClient(){
        Socket link = null;
        try{
        link = serverSocket.accept();
        Scanner scan = new Scanner(System.in);
        Scanner input2 = new Scanner(link2.getInputStream());
        PrintWriter output2 = new PrintWriter(link2.getOutputStream(),true);
        Scanner input = new Scanner(link.getInputStream());
        PrintWriter output = new PrintWriter(link.getOutputStream(),true);
        String massage2;
        String massage=" ";
        int attempt = 0;
        int mesaj =input.nextInt();
        output2.println(mesaj);
        output2.flush();
        do{
                massage = input.nextLine();
                output2.println(massage);
                output2.flush();
                System.out.print("recieved massage: "+massage+"\t");
                massage2 = input2.nextLine();
                output.println(massage2);
                output.flush(); 
                System.out.println("sender massage: "+massage2);
                attempt++;
        } while(attempt <= mesaj);
            System.out.println();
        } catch (IOException ex) {
            System.out.println("unable to conneceted");
        }
         try{
        link.close();
        link2.close();
        System.out.println("connect closing");
        } catch (IOException ex) {
            System.out.println("unable to disconnected");
        }
    }
   
}