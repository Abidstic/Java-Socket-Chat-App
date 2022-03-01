import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable{
    private String name;
    boolean islogged=true;
    Socket soc;
    final DataOutputStream dos;
    final DataInputStream dis;

    public ClientHandler(Socket soc,String name,DataInputStream dis,DataOutputStream dos)
    {
        this.soc=soc;
        this.name=name;
        this.dis=dis;
        this.dos=dos;

    }

    @Override
    public void run() {

        while(true)
        {
            String message;
            try {

                BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
                message= dis.readUTF();
                System.out.println(message);
                if(message.equals("STOP"))
                {
                    islogged=false;
                    soc.close();
                    break;
                }
                String messageCode= input.readLine();

                StringTokenizer str=new StringTokenizer(messageCode,"/");
                String messageToShow=str.nextToken();
                String recipient=str.nextToken();
                for(ClientHandler client : Server.clientList)
                {
                    if(client.name.equals(recipient)&& client.islogged==true)
                    {
                        client.dos.writeUTF("Server :" +messageToShow);
                        client.dos.flush();
                        break;

                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
