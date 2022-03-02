import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable{
    public String name;
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

                //BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
                message= dis.readUTF();
                System.out.println(message);
                if(message.equals("STOP"))
                {
                    islogged=false;
                    //soc.close();
                    break;
                }
                /**/
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
       /* try {
            dis.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/



    }
}
