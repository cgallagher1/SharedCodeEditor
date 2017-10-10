package sharedCodeEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class CodeGUI extends JFrame implements Observer
{
	private CodeDocumentInterface codeDoc;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private boolean bool;
	
	DocumentListener documentListener = new DocumentListener() {
		
		//If deleting
		@Override
		public void removeUpdate(DocumentEvent e) 
		{
			setBool(false);
			codeDoc.SetText(getTextArea().getText());
			
		}
		//If adding text
		@Override
		public void insertUpdate(DocumentEvent e) 
		{
			setBool(false);
			codeDoc.SetText(getTextArea().getText());
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) 
		{
			// Not used
			
		}
	};
	
	//Constructor
	public CodeGUI(CodeDocumentInterface codeDocument)
	{
		setPanel(new JPanel());
		codeDoc = codeDocument;
		setTextArea(new JTextArea());
		codeDoc.attach(this);
		setScrollPane(new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		getScrollPane().setLayout(new ScrollPaneLayout());
		getTextArea().getDocument().addDocumentListener(documentListener);
		setBool(true);
		
		//Detaches when closed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				codeDocument.detach(null);
				System.exit(0);
			}
		});
		
		Show();
	}
	//Shows Gui
	private void Show()
	{
		scrollPane.setViewportView(textArea);
		scrollPane.setVisible(true);
		add(scrollPane);
		setSize(new Dimension(500, 500));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Your editor");
		setVisible(true);
		JButton save = new JButton("Save");
		add(save, BorderLayout.PAGE_END);
		//Implements the button to save to a specific file
		save.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				File file = fileChooser.getSelectedFile();
				String fileLocation = file.getAbsolutePath() + ".txt";
				File fileWorkingIn = new File(fileLocation);
				BufferedWriter bufferedWriter = null;
				try
				{
					FileWriter fWriter = new FileWriter(fileWorkingIn);
					bufferedWriter = new BufferedWriter(fWriter);
				
					if (!file.exists()) 
					{
						fileWorkingIn.createNewFile();
					}
				
					getTextArea().write(fWriter);
				
					bufferedWriter.close();
					
				}
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	@Override
	public void update(String text) 
	{
		getTextArea().getDocument().removeDocumentListener(documentListener);
		if (isBool()) 
		{
			getTextArea().setText(text);
		}
		else
		{
			setBool(true);
		}
		getTextArea().getDocument().addDocumentListener(documentListener);
	}


	//Getters and Setters
	public JScrollPane getScrollPane() {
		return scrollPane;
	}


	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	public JTextArea getTextArea() {
		return textArea;
	}


	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}


	public CodeDocumentInterface getCodeDocInterface() {
		return codeDoc;
	}


	public void setCodeDocInterface(CodeDocument codeDocInterface) {
		this.codeDoc = codeDocInterface;
	}
	
	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
