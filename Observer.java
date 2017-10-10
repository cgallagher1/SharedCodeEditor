package sharedCodeEditor;

//Other classes inherit this interface, will notify that there has been a change
public interface Observer 
{
	void update(String text);
}
