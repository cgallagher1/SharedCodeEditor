package sharedCodeEditor;

public class CodeDocument implements CodeDocumentInterface
{
	private String text;
	private SubjectImpl subImp;
	
	//Construct creates a string and SubjectImple
	public CodeDocument() 
	{
		text = new String();
		subImp = new SubjectImpl();
	}
	
	//SubjectImple interacting with observers
	@Override
	public void attach(Observer o) 
	{
		subImp.attach(o);
	}

	@Override
	public void detach(Observer o) 
	{
		subImp.detach(o);
	}

	@Override
	public void notifyAllObservers(String text)
	{
		subImp.notifyAllObservers(text);
	}

	//getters and setters
	@Override
	public String getText() 
	{
		return text;
	}

	@Override
	public void SetText(String t)
	{
		text = t;
		notifyAllObservers(t);
	}

}
