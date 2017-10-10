package sharedCodeEditor;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server
{
	public static void main(String[] args)
	{
		//print out the IP address of the CodeDocument
		try 
		{
			System.out.println("Code Document Driver, IP address: " + InetAddress.getLocalHost().getHostAddress());
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//create a real code document that will be shared between many GUIs
		CodeDocumentInterface codeDocument = new CodeDocument();

		//create a client proxy thread to communicate with remote GUI's
		Thread t = new Thread(new GUIClientProxy(codeDocument));
		t.start();
	}
	
}
