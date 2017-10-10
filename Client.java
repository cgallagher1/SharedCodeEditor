package sharedCodeEditor;

import javax.swing.JOptionPane;

public class Client 
{
	public static void main(String[] args)
	{
		String ipAddressOfCodeDoc = JOptionPane.showInputDialog("Enter in the IP address of the machine with the shared code document");

		//get the port number that this GUI's CodeDocumentClientProxy will listen on
		int thisGUIsListeningPortNum = Integer.parseInt(JOptionPane.showInputDialog("Enter in a port number for this GUI machine to listen on"));
		CodeDocumentInterface codeDocument = new CodeDocumentProxy(ipAddressOfCodeDoc, thisGUIsListeningPortNum);

		//create a GUI and pass it a code doc
		CodeGUI gui1 = new CodeGUI(codeDocument);

		//create a client proxy thread to communicate with the remote code document
		Thread t = new Thread(new CodeDocumentClientProxy(gui1, thisGUIsListeningPortNum));
		t.start();
	}

}
