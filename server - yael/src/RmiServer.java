import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*; 

public class RmiServer extends UnicastRemoteObject implements RmiServerIntf {
    public static final String MESSAGE = "Hello World";
 
    private String serverAddress;
	private int port;
    
    
//    public RmiServer() throws RemoteException {
//        super(0);    // required to avoid the 'rmic' step, see below
//    }
    
  public RmiServer() throws RemoteException {
	  this.serverAddress = "127.0.0.1";
	  this.port = 5001;   // required to avoid the 'rmic' step, see below
}
    
 
    public String getMessage() {
        return MESSAGE;
    }
 
    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");
 
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(5001); 
            System.out.println("java RMI registry created.");
       
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
 
        //Instantiate RmiServer
 
        RmiServer obj = new RmiServer();
        System.out.println("1");
 
        // Bind this object instance to the name "RmiServer"
        Naming.bind("//localhost/RmiServer", obj);
        //Naming.rebind("//localhost/RmiServer", obj);

     
        System.out.println("PeerServer bound in registry");
    }
}