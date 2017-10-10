package sharedCodeEditor;

import java.io.DataOutputStream;
import java.net.Socket;
//Class implements Observer and sends information to the CodeDocProxy
public class GUIProxy implements Observer
{
	private String ip;
	private int port;

	public GUIProxy(String ipnum, int portnum) 
	{
		setIp(ipnum);
		setPort(portnum);
	}
	
	@Override
	public void update(String text) 
	{
		String newText = text;
		
		//Socket to handle new lines and colons
		newText = newText.replaceAll("\n", "~~~");
		newText = newText.replaceAll(":", "@@@");
		
		newText = "update:" + newText;
		try 
        {
            //create a socket connection with a server on another machine
            Socket clientSocket = new Socket(ip, port);

            //in order to write to the socket we have to
            //create a stream of characters in and out

            //we can use outToCodeDoc to write to the socket
            DataOutputStream outToCodeDoc = new DataOutputStream(clientSocket.getOutputStream());

            //send a request to the server- must end with a newline
            //but the newline will not be sent
            outToCodeDoc.writeBytes(newText + "\n");
            
            outToCodeDoc.close();

            //close the socket after we are done with it
            clientSocket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}


}
