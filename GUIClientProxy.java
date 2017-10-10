package sharedCodeEditor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GUIClientProxy implements Runnable 
{
	private CodeDocumentInterface codeDoc;
	
	public GUIClientProxy(CodeDocumentInterface doc) 
	{
		setCodeDoc(doc);
	}
	
	
	@Override
	public void run() 
	{	try
		{
		 //create the server socket passing the port number to listen for
		@SuppressWarnings("resource")
		ServerSocket welcomeSocket = new ServerSocket(54321);

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
            
            //enforce a protocol- there must be three values in the
            //request string and the first must be the word 'add'
            if(tokens[0].length() == 7 && tokens[0].equalsIgnoreCase("setText"))
            {
            	//If the text area is cleared
            	if (tokens.length == 1) 
            	{
					codeDoc.SetText("");
				}
            	
            	//Takes care of the new line and colon case
            	else
            	{
            		tokens[1] = tokens[1].replaceAll("~~~","\n");
            	
            		tokens[1] = tokens[1].replaceAll("@@@",":");
            	
                	codeDoc.SetText(tokens[1]);
            	}
            }
            
            //When a new codeGui has been started attach an observer
            else if (tokens[0].length() == 6 && tokens[0].equalsIgnoreCase("attach")) 
            {
				codeDoc.attach(new GUIProxy(serverSocket.getInetAddress().getHostName(), Integer.parseInt(tokens[1])));
			}
            //When a CodeGui has been closed, detach observer
            else if(tokens[0].length()== 6 && tokens[0].equalsIgnoreCase("detach"))
            {
				codeDoc.detach(new GUIProxy(serverSocket.getInetAddress().getHostName(), Integer.parseInt(tokens[1])));
			}

            //close the socket
            serverSocket.close();
        }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public CodeDocumentInterface getCodeDoc() {
		return codeDoc;
	}


	public void setCodeDoc(CodeDocumentInterface codeDoc) {
		this.codeDoc = codeDoc;
	}


}
