import java.io.*;
import java.net.*;
import java.util.*;

/* Important Definitions */
/*
	Server Socket 
	This class implements server sockets. A server socket waits for requests to come 
	in over the network. It performs some operation based on that request, and then possibly returns a result to the requester.

*/

/* External Dependencies */

/*
	Class Core - 
		findCore - reads the file f2.txt and returns the addr of the current node
	Class pack - Does Nothing  Can Be removed
	Class Destination

*/

/* Points to Think About */
/*
	readObject method returns string , either "file" or "request"
*/

class Main_Server
{
	static String query="";
	static String str="";
	static String addr="";
	static String source="";
	static String destination="";
	static String f="";
	static TreeSet v;
	static File ff;
	static InetAddress in;
	static String sst="";
	static String b="";
	static boolean bool=false;
	static boolean boolea=false;
	static ReCore re;
	static String neaddr="";
	static Core co;
	static String pass="";

	public static void print(Object str){
		System.out.println(str);
	}

	Main_Server(){}
	public static void main(String a[]){
	  try{
	  		print("Public Key (p,n) : ("+ RSA.readFile("publicKey")+","+RSA.readN()+")");
			ServerSocket ss = new ServerSocket(8888); /* Opens a Socket to recieve requests at port 8888  */
			while(true){

				System.out.println("Waiting...");
				Socket s = ss.accept();   /*  Keeps Listening to the specified port  */
				
				System.out.println("Connected");
				while(true){
					InputStream ins = s.getInputStream();
					OutputStream ous = s.getOutputStream();
					ObjectOutputStream oos=new ObjectOutputStream(ous); /* Converts an Object to a stream of Bytes so that it can be stored and recorvered */
					ObjectInputStream ois=new ObjectInputStream(ins);  
					
					co = new Core();
					v  = new TreeSet();
					  
					System.out.println("Before Reading ");
					String fd=(String)ois.readObject(); /* This is send from the sender */

					  /* Fd basically decides weather the request is a file or a request */


				    System.out.println("OBJECT : " + fd );
				    if(fd.equals("request")){
					 
				     /* If the Request is a 'request', then it simply reads its coreAddr and sends it to the ServerOutput Stream */
				     /* It sends its own address and then again starts waiting for a new Request from the Server */
					  
					  System.out.println("Request Received");
	                  addr=co.findCore();
	                  System.out.println("Core address : "+addr);
	                  oos.writeObject(addr);
	                  break;
	                }	

		                /* When the client is sending the file, it first sends the 
							1. Source Addr
							2. Destination name
							3. Content or data
		                */


		                if(fd.equals("file"))
		                {
						  System.out.println("File transfer is requested ");
						  source=(String)ois.readObject();
						  destination=(String)ois.readObject();
						  
						  System.out.println("Source :"+ source);
						  System.out.println("Destination :"+destination);

						  FileWriter fw=new FileWriter("encc.txt");
						  BufferedWriter bw=new BufferedWriter(fw);


						  pass ="";
						  pass=(String)ois.readObject();

						  System.out.println("Data is "+ pass);


						  Pack p=new Pack(); /* Does Nothing */
						  p.Pack1(pass);
						  System.out.println("The Enc File is " + pass);
							
						  bw.write(pass,0,pass.length()); /* It writes the data to the file encc.txt */
						  bw.flush();


						  ff=new File("encc.txt");
						  
						  String inet = (InetAddress.getLocalHost()).getHostName(); /* It just reads the hostname */
						  System.out.println("The local address is :"+ inet);
						  System.out.println("The destined address is :"+ destination);
						  	


						  if(destination.equalsIgnoreCase(inet))
						  {
						  	/* If the Current node is the destination , then : , it shows the necessary interface */
						  	  System.out.println("Current Node is Destination ");
							  new Destination(); /* Initiates the Inteface on the server accepting the Clients request */
							  Destination.ServSoc(pass,source,destination); /* pass is the data */ /* This function converts Hex into normal Text */
							  bool=true;
							  break;
						  }
						  else if(!bool)
						  {
						  	/* If this node is not the destination  */
							 BufferedReader bf=new BufferedReader(new FileReader("f2.txt")); /* shouldn't it be nextmesh.txt */

							 while((sst=bf.readLine())!=null)
							  		  {
							  			  try
							  			  {
							  			  if(destination.equals(sst))
							  		      {

									          co.sendFileToCore(destination,source,destination,pass);
									          System.out.println("Success to destination ");
									          boolea=true;
									          break;
										  }
							  			 
							  		      }
							  		      catch(Exception e)
							  		      {
							  				  System.out.println("Error in finding the core ");
							  				  continue;
							  			  }
				                      }
						  }
						  if(!boolea && !bool)
						  {	
						  	 /* If nothing is found in f2.txt , then comes the nextMesh.txt */
							 /* This one is used when the current node is not the destination node */
							  re=new ReCore();
							  neaddr=re.reqCore();
				              co.sendFileToCore(neaddr,source,destination,pass);
				              break;
						  }

		                break;
					    }


			       }
			  }

		   }

	  catch(IOException ie)
		  {
			  System.out.println(ie);
		  }
	  catch(Exception e)
	      {
			  System.out.println(e);
		  }


	  }

  }


