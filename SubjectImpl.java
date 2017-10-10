package sharedCodeEditor;
import java.util.ArrayList;
//Handels Observers
public class SubjectImpl implements Subject
{
	private ArrayList<Observer> observers;
	//Constructor begins an ArrayList
	public SubjectImpl()
	{
		observers = new ArrayList<Observer>();
	}
	//Attaches observer to GUI
	@Override
	public void attach (Observer o) 
	{
		observers.add(o);
	}
	//If GUI is closed, gets rid of observer
	@Override
	public void detach(Observer o) 
	{
		for(Observer observer: observers)
		{
			if (((GUIProxy)observer).getIp().equals(((GUIProxy)o).getIp())) 
			{
				if (((GUIProxy)observer).getIp().equals(((GUIProxy)o).getIp())) 
				{
					observers.remove(observer);
					break;
				}
			}
		}
	}
	//Notifies there has been a change
	@Override
	public void notifyAllObservers(String text) 
	{
		for (Observer observer: observers) 
		{
			observer.update(text);
		}
	}
}
