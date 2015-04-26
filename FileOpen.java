import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;

class FileOpen extends JFrame 
{
	static int recieversPublicKey;
	static int recieversN;

	 JLabel jl1;
	 JLabel jl2;
	 JLabel jl3;
	 JLabel jl5;
	 JLabel recieversPublicKeyLabel;
	 JLabel recieversNLabel;

	 JButton jb1;
	 JButton jb2;
	 JButton jb3;
	 // JButton jb4;
	 JTextField jt1;
	 JTextField jt2;
	 JTextField recPublicKeyTextField;
	 JTextField recNTextField;

	 String msg="";
	 static JTextArea jta;
	 JScrollPane jsp;
	 Container c;
	 ImageIcon ii;
	 ImageIcon i2;
	 JLabel jl4;
	 JLabel jl6;
	 JLabel jl7;
	 JList jlist;
	 String str="";
	 Core co;
	 ReCore rc;
	 String pass="";
	 String path="";
	 String coreaddr="";
	 String core="";
	 String nextcore="";
	 String dest="";
	 String dest1="";
	 File f;
	 File fgs;
	 Vector v = new Vector();



	 FileOpen(){
	 	recieversPublicKey = 17;
	 	recieversN = 3233;
		 new net();
		 v = net.vnode;  /* This is the list of the nextMesh */ /* It will only run in Windows Operating System  */
		 
		 jl1=new JLabel("Plz Enter The Destination Name ");
		 jl2=new JLabel("Plz Choose File ");
		 jl3=new JLabel("File Name Will Be Displayed Here..");
		 jl5=new JLabel("Next Core Address ");
		 recieversNLabel = new JLabel("Recievers N (of RSA)");
		 recieversPublicKeyLabel = new JLabel("Recievers Public Key");



		 jb1=new JButton("Send");
		 jb2=new JButton("Reset");
		 jb3=new JButton("Browse..");
		 // jb4=new JButton("Start Server");
		 jt1=new JTextField(10);
		 jt2=new JTextField(10);
		
		 recPublicKeyTextField = new JTextField(10);
		 recNTextField = new JTextField(10);

		 jl4=new JLabel(ii);
		 jl6=new JLabel(i2);
		 jl7=new JLabel("NEXTMESH");
		 jta=new JTextArea();
		 jsp=new JScrollPane(jta);
		 jlist=new JList(v); /* Its the list of all nodes in the network */

		 c=getContentPane();
		 c.setLayout(null);
		 c.add(jl1);
		 c.add(jt1);
		 c.add(jl2);
		 c.add(jl3);
		 c.add(jb3);
		 c.add(jb1);
		 c.add(jb2);
		 // c.add(jb4);
		 c.add(jsp);
		 c.add(jl4);
		 c.add(jl5);
		 c.add(jl6);
		 c.add(jl7);
		 c.add(jt2);
		 c.add(jlist);

		 c.add(recPublicKeyTextField);
		 c.add(recNTextField);
		 c.add(recieversNLabel);
		 c.add(recieversPublicKeyLabel);


		 jl4.setBounds(0,10,900,90);
		 jl1.setBounds(70,110,200,25);
		 jt1.setBounds(300,110,100,25);
		 jl2.setBounds(70,145,200,25);
		 jt2.setBounds(300,145,100,25);
		 jb3.setBounds(300,180,100,25);
		 // jb4.setBounds(300,305,100,25);
		 jsp.setBounds(410,110,280,400);
		 jb1.setBounds(150,335,100,25);
		 jb2.setBounds(300,335,100,25);
		 jl6.setBounds(150,275,250,150);
		 jl7.setBounds(700,75,75,50);
		 jlist.setBounds(700,110,200,400);
		 jl3.setBounds(70,440,600,25);
		 jl5.setBounds(70,475,600,25);
		 setSize(800,600);
		 setVisible(true);

		recNTextField.setBounds(300,250,100,25);
		recPublicKeyTextField.setBounds(300,220,100,25);
	    recieversNLabel.setBounds(70,250,200,10);
	    recieversPublicKeyLabel.setBounds(70,220,200,25);


		// jb4.addActionListener( new ActionListener() { /* On Clicking Start Server  */
		// 	public void actionPerformed(ActionEvent ae){
		// 		System.out.println("Event Occured!");
		// 		try{
		// 			Process p = Runtime.getRuntime().exec("cmd.exe /K startServer.bat");
		// 		}
		// 		catch(IOException e){
		// 			e.printStackTrace();
		// 		}
		// 	} 
		// });



		jb3.addActionListener(new ActionListener(){ /*  This one is used for Browsing the input text file */
			/* On clicking the browse Button   */
			public void actionPerformed(ActionEvent ae){
				JFileChooser jf=new JFileChooser();
				jta.setText("");
				String str1 = "";
				int m=jf.showOpenDialog(null);
				if(m==JFileChooser.APPROVE_OPTION){
					   f=jf.getSelectedFile();
					   str=f.getPath();
					   path=f.getAbsolutePath();
					   jl3.setText("U Have Selected The File Named \""+str+"\""+"from "+path);
					try{
					   FileInputStream fis=new FileInputStream(str);
					   byte b[]=new byte[fis.available()];
					   jt2.setText(str);
					   fis.read(b);
					   str1=new String(b);
					   jta.setText(str1);
				   }
				   catch(Exception ui){}
			   }
			}
		});

		jb1.addActionListener(new ActionListener(){ /* On clicking Send */
			public void actionPerformed(ActionEvent ae){
	            System.out.println("Sending...");
				co = new Core();
				rc = new ReCore();
				try {
					recieversPublicKey = Integer.parseInt( recPublicKeyTextField.getText());
					recieversN = Integer.parseInt(recNTextField.getText());

					coreaddr = co.findCore(); /* The Addr of the current node f2.txt  */
					jl3.setText("The Core Address is : "+ coreaddr);
					String d = ((InetAddress.getLocalHost()).getHostAddress()); /* The name of the current node */
										
					dest=jt1.getText(); /* The Destination name */
					dest1=jt2.getText(); /* Just the file name  */



					jl5.setText(dest);
					/* Uncomment it here in case of errors */
					// pass = co.met1(); /* Just Converts to hexadecimal  */

					pass = co.encrypt(jta.getText(),recieversPublicKey,recieversN);

					if(coreaddr.equals((InetAddress.getLocalHost()).getHostAddress())){
						/* If the node is itself the core node of the localNetwork */
	                    nextcore=rc.reqCore();
	                    if(nextcore.equalsIgnoreCase("false")){
	                     	jl5.setText("The Next Mesh Not Found");
	                    }
	                    else{
	                     	jl5.setText("The Next Mesh Core is :"+nextcore);
	                    }
	                     
	                    co.sendFileToCore(nextcore,(InetAddress.getLocalHost()).getHostAddress(),dest,pass);
	                    co.sendFileToCore(nextcore,(InetAddress.getLocalHost()).getHostAddress(),dest1,pass);			
					}
					else{
						co.sendFileToCore(coreaddr,(InetAddress.getLocalHost()).getHostAddress(),dest,pass);
						co.sendFileToCore(coreaddr,(InetAddress.getLocalHost()).getHostAddress(),dest1,pass);
						System.out.println("Successful transfer of file ");
						jl5.setText("The Next Mesh Core is :"+coreaddr);			
					}

			   }
			catch(Exception e) { System.out.println(e);}
			}
		});
	 
		jb2.addActionListener( new ActionListener() { /* On Clicking Reset  */
			public void actionPerformed(ActionEvent ae){
				jt1.setText("");
				jt2.setText("");
				jta.setText("");
			} 
		});

		jlist.addListSelectionListener(new ListSelectionListener() { /* This Actually Edits the next mesh file, this selects the next node in the cycle */
 			public void valueChanged(ListSelectionEvent le) 
 			{ 
 				System.out.println("\njList1_valueChanged(ListSelectionEvent e) called."); 
 				if(!le.getValueIsAdjusting()) 
 				{ 
					try
					{
						Object o = jlist.getSelectedValue(); 
 						System.out.println("" + ((o==null)? "null" : o.toString()) + " is selected."); 
		 				String router=o.toString();
 						System.out.println("\nRouter is" +  router);
						FileOutputStream fos = new FileOutputStream("NEXTMESH.txt");
						fos.write(router.getBytes());
					}
					catch (Exception ee)
					{
						ee.printStackTrace();
					}
 				
 				}
			}
	 } );
   }
	 public static void main(String a[]){
		 new FileOpen();
	 }
 }
