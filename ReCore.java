import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

/* This actually works when the current node is not the destination node */

class ReCore{
	static String add="";
	public static void main(String a[]){
		try{
	        String add=reqCore();
		} catch(Exception e) { System.out.println(e); }
    }

	public static String reqCore(){
		BufferedReader brf;
		String h="";
		System.out.println("Socket side");
 		String s1 = "";
 		String s2="request";
 		String addr="";
 		Socket s;
		String sss="";
        boolean b=true;
 	
 		try{	
	 		BufferedReader br =null;
	 		BufferedWriter bw;
	 		FileReader fr=new FileReader("NEXTMESH.txt");
	 		brf=new BufferedReader(fr);
	 		System.out.println("Socket side after file read");	 	
	 		while((s1=brf.readLine())!=null){
	     		if(b){
					sss = s1;
	                    try{
						  s=new Socket(s1,8888);
		                  System.out.println("Connection Eastablished with "+ s1);
		                  InputStream is=s.getInputStream();
		                  OutputStream os=s.getOutputStream();
		                  ObjectOutputStream oos=new ObjectOutputStream(os);
		                  
		                  oos.writeObject("request");
		 		          ObjectInputStream obip=new ObjectInputStream(s.getInputStream());
		 		          add=(String)obip.readObject();
		 		          
		 		          b=false;
		 		          System.out.println("The Next Core Address  :" + add);
	                  }
	 		          catch(Exception e){
						JOptionPane.showMessageDialog(null, "Next Intermediate Node Is Fail");
						System.out.println(e);
						return "false";					
					  }		   
				 }
			}
		}catch(Exception ee){}	 
        return sss;
    }
}