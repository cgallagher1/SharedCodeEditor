package sharedCodeEditor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CodeDocumentClientProxy implements Runnable
{
	private int port;
	private CodeGUI codeGUI;
	
	public CodeDocumentClientProxy(CodeGUI gui, int portNum)
	{
		setCodeGUI(gui);
		setPort(portNum);
	}

	@Override
	public void run()
	{
		try
		{
		 //create the server socket passing the port number to listen for
        @SuppressWarnings("resource")
		ServerSocket welcomeSocket = new ServerSocket(port);

        //usually a server runs 24/7
        while(true)
        {
            //block the server until a connection is created
            //then create a new socket to transfer data
            Socket serverSocket = welcomeSocket.accept();

            //we can use inFromClient to read in to the socket
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

            //read the request from the client
            String requestFromClient = inFromClient.readLine();

            //break the string into tokens separated by the ':'
            String[] tokens = requestFromClient.split(":");

            //If update, adds text and adds the new lines and colons
            if(tokens[0].length() == 6 && tokens[0].equalsIgnoreCase("update"))
            {
            	if (tokens.length == 1) 
            	{
					codeGUI.update("");
				}
            	else
            	{
            		//Replace the colon and the new line
            		tokens[1] = tokens[1].replaceAll("@@@", ":");
            		tokens[1] = tokens[1].replaceAll("~~~", "\n");
            		codeGUI.update(tokens[1]);
            	}
            }

            //close the socket
            serverSocket.close();
        }
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CodeGUI getCodeGUI() {
		return codeGUI;
	}

	public void setCodeGUI(CodeGUI codeGUI) {
		this.codeGUI = codeGUI;
	}
}
