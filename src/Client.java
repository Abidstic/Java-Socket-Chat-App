import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket=new Socket("localhost",5000);
        DataInputStream dis=new DataInputStream(clientSocket.getInputStream());
        DataOutputStream dos=new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

            Thread sendmessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message = input.readLine();
                        dos.writeUTF(message);
                        dos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

            Thread recieveMessage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String reciveMessage = dis.readUTF();
                        System.out.println(reciveMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            sendmessage.start();
            recieveMessage.start();


    }
}
