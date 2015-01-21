import java.rmi.Naming;

import javax.management.remote.rmi.RMIServer;


public class RmiClient {

	public static void main(String args[]) throws Exception {
        RMIServer obj = (RMIServer)Naming.lookup("//localhost/RmiServer");
        System.out.println(((Throwable) obj).getMessage()); 
	}

}
