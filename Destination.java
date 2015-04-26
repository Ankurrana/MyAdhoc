import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Destination extends JFrame implements ActionListener
{
	JTabbedPane tp ;
	JLabel jl,jl1,jl2,jL;
	JButton jb,jb1,jb2;
	static JTextField tf1,tf2,senderTextField,DestinationTextField;
	static JTextArea tf,ta1;
	JScrollPane sp,sp1;
	static String g1=" ";
	static String n,n1,n2;

	public Destination()
	{
		super("Data Recieved");
		setSize(500,530);
		Container c = getContentPane();

		JPanel jp = new JPanel();
		jp.setLayout(null);
		jl = new JLabel("Sender Name ",JLabel.LEFT);
		jL = new JLabel("Receiver Name ",JLabel.LEFT);
		senderTextField = new JTextField(15);
		DestinationTextField = new JTextField(15);


		tf = new JTextArea(20,40);
		sp = new JScrollPane(tf);
		jb= new JButton("Done!");
		jp.add(jl);
		jp.add(senderTextField);
		jp.add(jL);
		jp.add(DestinationTextField);
		jp.add(sp);
		jp.add(jb);

		jl.setBounds(25,10,150,25);
		senderTextField.setBounds(150,10,150,25);
		jL.setBounds(25,40,150,25);
		DestinationTextField.setBounds(150,40,150,25);
		sp.setBounds(25,70,450,350);
		jb.setBounds(220,425,75,25);
		jp.setVisible(true);

		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		jl1 = new JLabel("Sender Name ",JLabel.LEFT);
		jl2 = new JLabel("Receiver Name ",JLabel.LEFT);
		tf1 = new JTextField(15);
		tf2 = new JTextField(15);
		ta1 = new JTextArea(20,40);
		sp1 = new JScrollPane(ta1);
		jb2= new JButton("Retrive");

		jl1.setBounds(25,10,150,25);
		tf1.setBounds(150,10,150,25);
		jl2.setBounds(25,40,150,25);
		tf2.setBounds(150,40,150,25);
		sp1.setBounds(25,70,450,350);
		jb2.setBounds(220,425,75,25);
		jb.addActionListener(this);
		jb2.addActionListener(this);
		
		ta1.setText("");

		tp = new JTabbedPane();
		tp.addTab("Message Recieved",null,jp,"Retrive the Message here");
		c.add(tp);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String arg[])throws Exception{
		Destination rm = new Destination();
	}
	public static void print(Object str){
		System.out.print(str);
	}

	public static String ServSoc(String data,String sender,String dest)
	{	
		int privateKey = RSA.readPrivateKey();
		int n = RSA.readN();	
		String[] items = data.replaceAll(" ","").replaceAll("\\[", "").replaceAll("\\]", "").split(",");
		int[] results = new int[items.length];
		for (int i = 0; i < items.length; i++) {
		    try {
		        results[i] = Integer.parseInt(items[i]);
		    } catch (NumberFormatException nfe) {};
		}
		String finalString="";
		int len = results.length;
		for(int i=0;i<len;i++){
			finalString += (char)RSA.power(results[i],privateKey,n);
		}
		senderTextField.setText(sender);
		DestinationTextField.setText(dest);
		tf.setText(finalString);
		print(finalString);		
		return finalString;	
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == jb){			
			super.dispose();
		}
	}
}
