## Multi-user chat built on multi-threaded code ##

### Tasks: ###

1. To communicate between client and server, use TCP sockets.


2. Each client enters their name upon entry. If the name is already taken, the client can enter another name. The client can enter a message, and when sent, it is sent to all other connected clients on its behalf.


3. Messages on the server are stored during the current session. Accordingly, if there are several messages on the server, then the connected client receives the last 100 messages. Messages and sessions are stored in RAM; there is no need to save this information in external storage.


4. The server must support up to 1000 simultaneous clients.


5. The client can send an arbitrary command to the server and receive the execution result in response (for example, the number of connected clients). You need to be able to quickly and easily add new commands. There should be a help command that returns information on other commands.


6. The chat should be covered with unit tests. You also need to implement load testing with an arbitrary number of clients.