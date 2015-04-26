import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;



class Core
{
	static String g1="";
	static String n=" ";
	static String core="";
	static String nul=null;
	static File fk;
  public static void main(String a[]) throws Exception
  {


  }

 static String findCore() throws Exception
	  {	

	  	  return InetAddress.getLocalHost().getHostAddress();
		  // String s="";
		  // String k="";
		  // InetAddress in;
		  // TreeSet v=new TreeSet();
		  // String b="";

    //  	  BufferedReader bf=new BufferedReader(new FileReader("CoreAddress.txt"));

		  // while((s=bf.readLine())!=null)
		  // {
			 //  try
			 //  {
				//   in=InetAddress.getByName(s);
				//   b=in.getHostAddress();
			 //      v.add(b);
				//   System.out.println("Inetaddress : "+ in );
				  
				//   System.out.println("Address: "+ b + "  "+ in.getHostName());
		  //     }
		  //     catch(Exception e)
		  //     {
				//   System.out.println("Error in finding the core ");
					 
				//   continue;
			 //  }
		  // }
		  // bf.close();
		  // core=(String)v.last();
		  // System.out.println("The Core Node's Address Is : "+core );
		  // return core;

	 }

static void sendFileToCore(String core,String source,String destination,String msg) throws Exception{
		/* It is one of the most important method  */
		/*  It creates a new socket, on port number 8888 and sends the file to the node next
			in the mesh		
			msg - is the data
		*/

		try
		{
	         String str="",str1="";
	 		 Socket s=new Socket(core,8888);
	 		 ObjectOutputStream obop=new ObjectOutputStream(s.getOutputStream());
	 		 obop.writeObject("file");
	 		 obop.writeObject(source);

	 		 System.out.println("The Source address is : "+ source);
	 		 obop.writeObject(destination);
	 		 System.out.println("The destined address is : "+ destination);
	 		 obop.writeObject(msg);
             obop.writeObject(nul);
			JOptionPane.showMessageDialog(null, "File Transfered");
			}catch(Exception ee)
			{
				
			}
	 	 }
	 public static void print(String str){
	 	System.out.println(str);
	 }

	 		 //s.close();
	public static String encrypt(String data,int recieversPublicKey, int recN){	
		print("Encrypting data using RecieversPublic Key as (" + recieversPublicKey+","+recN+")");
		int len = data.length();
		int a[] = new int[len+1];
		int encryptedData[] = new int[len+1];

		for(int i=0;i<len;i++){
			a[i] = data.charAt(i);
		}
		for(int i=0;i<len;i++){
			encryptedData[i] = RSA.power(a[i],recieversPublicKey,recN);
		}
		String encryptedstring = Arrays.toString(encryptedData);
		print("Encryped Data  :");
		System.out.println(encryptedstring);
		return encryptedstring;
	}	
}