package sharedCodeEditor;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CodeDocumentProxy implements CodeDocumentInterface
{
	private String ipNum;
	private int port;
	
	public CodeDocumentProxy(String ip, int portNum) 
	{
		setIpNum(ip);
		setPort(portNum);
	}
	
	@Override
	public void attach(Observer o) 
	{
		
		try 
        {
			String request = "attach:" + port;
            //create a socket connection with a server on another machine
            Socket clientSocket = new Socket(ipNum, 54321);

            //we can use outToServer to write to the socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //send a request to the server- must end with a newline
            //but the newline will not be sent
            outToServer.writeBytes(request + "\n");

            //close the socket after we are done with it
            clientSocket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	
	}

	@Override
	public void detach(Observer o) 
	{
		try 
        {
			String request = "detach:" + port;
            //create a socket connection with a server on another machine
            Socket clientSocket = new Socket(ipNum, 54321);

            //we can use outToServer to write to the socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            //send a request to the server- must end with a newline
            //but the newline will not be sent
            outToServer.writeBytes(request + "\n");

            //close the socket after we are done with it
            outToServer.close();
            clientSocket.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	}

	@Override
	public void notifyAllObservers(String text) 
	{
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SetText(String t) 
	{
		try
		{
			
			String theText = t;
			theText = theText.replaceAll(":","@@@");
			theText = "setText:" + theText;
			Socket clientSocket = new Socket(ipNum, 54321);
			
			//Writes text to string
			DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
			
			//Handles new line and colons
			theText = theText.replaceAll("\n","~~~");

			outToClient.writeBytes(theText + "\n");
			
			outToClient.close();
			clientSocket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getIpNum() {
		return ipNum;
	}

	public void setIpNum(String ipNum) {
		this.ipNum = ipNum;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
