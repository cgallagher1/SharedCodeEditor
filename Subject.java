package sharedCodeEditor;

//Classes inherit from this interface, will control the observers
public interface Subject 
{
	public void attach (Observer o);
	
	public void detach (Observer o);
	
	public void notifyAllObservers(String text);
}
