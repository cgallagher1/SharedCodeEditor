# SharedCodeEditor
This editor can be shared by multiple programmers on different machines. When a developer on one machine types in their editor all the other developers will see the changes. The programmer must be able to save a local copy of the file at any point.

In order to accomplish this I implimented the Observer and Proxy patterns. The Observer will be used to notify the group when someone makes a change to the file and the Proxy will be used to distribute the changes to multiple machines. 

There is a basic GUI for the editor window with some menus to accomplish basic editing. When the program starts it will prompt for the IP address of the machine that holds the Observer's Subject, which you might call the CodeDocument. This class might be a GUI-less central server. The GUI editor and the CodeDocument must not have any reference to networking code. This should be handled in proxies/subject.

This program may requires separate threads of execution. A thread is another 'flow of control' in a program. As long as two threads do not interact with each other or share data it is relatively straightforward to implement them.
