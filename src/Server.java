import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

public static ArrayList<ClientHandler> clientList=new ArrayList<ClientHandler>();
static int clientNo=1;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(5000);
        System.out.println("Waiting for the client");

        while (true)
        {
            Socket soc=serverSocket.accept();
            System.out.println("Client "+clientNo+" is connected");
            DataInputStream dis = new DataInputStream(soc.getInputStream());
            DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
            ClientHandler clientHandler=new ClientHandler(soc,"client"+clientNo,dis,dos);
            Thread thread=new Thread(clientHandler);
            clientList.add(clientHandler);
            thread.start();
            clientNo++;
        }

    }






}
