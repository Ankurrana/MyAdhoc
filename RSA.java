import java.io.*;
import java.util.*;
import java.math.*;

class RSA{
	public static void main(String args[]){
		if( args.length < 2 ) {
			print("Require two Prime Numbers p and q as Command line inputs");
			print("RSA Key generation Process Stopped");	
			return;
		}

		int p = Integer.parseInt(args[0]);
		int q = Integer.parseInt(args[1]);

		if( isPrime(p)==false || isPrime(q)==false ){
			print("Both p and q must be primes");
			print("RSA Key generation Process Stopped");
			return;
		}
		generateKey(p,q);
	}	
	public static void generateKey(int p, int q){
		int n = p*q;
		int phiN = (p-1)*(q-1);
		int randomNumber;

		randomNumber = randInt(2,phiN-1);

		int e = searchE(randomNumber,phiN);
		print("Your generated Public key is "+e);
		print("Value of N = "+ n);
		int d = (BigInteger.valueOf(e).modInverse(BigInteger.valueOf(phiN))).intValue();
		print("Your generated Private key is "+d);
		CreateAndWritetoFile("publicKey.txt",e+"");
		CreateAndWritetoFile("privateKey.txt",d+"");
		CreateAndWritetoFile("n.txt",n+"");

		print("Public Key is written to a file named publicKey.txt");
		print("Private Key is written to a file named privateKey.txt");
		print("Value of N is written to a file named n.txt");
		print("Send public key to reciever");


	}
	public static int searchE(int random,int phiN){
		for(int i=random; i<=phiN-1;i++){
			if( gcd(i,phiN) == 1 ) return i;
		}
		return phiN-1;
	}

	public static int gcd(int a, int b) { return b==0 ? a : gcd(b, a%b); }
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
    	return randomNum;
	}

	public static void print(String str){
		System.out.println(str);
	}
	public static boolean isPrime(long n) {
	    if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    long sqrtN = (long)Math.sqrt(n)+1;
	    for(long i = 6L; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
    return true;
	}
	public static void CreateAndWritetoFile(String name, String data){
		Writer writer = null;
		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		    new FileOutputStream(name), "utf-8"));
		    writer.write(data);
		} catch (IOException ex) {
		}finally {
		   try {writer.close();} catch (Exception ex) {}
		}
	}
	public static int power(int a,int b, int mod){
		int result = 1;
		while(b>0){
			if( b%2 == 1) result = (result*a)%mod;
			a = (a*a)%mod;
			b = b>>1;
		}
		return result;
	}

	public static int readPrivateKey(){
		int key;
		try{
			BufferedReader bf = new BufferedReader(new FileReader("privateKey.txt"));
			String str = bf.readLine();
			if( str == null){
				print("Error Encountered: Private Key is Null");
				print("Setup a private Key First");
				return 0;
			}
			bf.close();
			key = Integer.parseInt(str);
			return key;
		}catch(Exception e){
			print("Exception Caught while reading PrivateKey.txt");
		}
		return 0;
	}
	public static int readN(){
		int key;
		try{
			BufferedReader bf = new BufferedReader(new FileReader("n.txt"));
			String str = bf.readLine();
			if( str == null){
				print("Error Encountered: N is Null");
				print("Setup a RSA Key First");
				return 0;
			}
			bf.close();
			key = Integer.parseInt(str);
			return key;
		}catch(Exception e){
			print("Exception Caught while reading n.txt");
		}
		return 0;
	}
	public static int readFile(String filename){
		int key;
		try{
			BufferedReader bf = new BufferedReader(new FileReader(filename+".txt"));
			String str = bf.readLine();
			if( str == null){
				print("Error Encountered: N is Null");
				print("Setup a RSA Key First");
				return 0;
			}
			bf.close();
			key = Integer.parseInt(str);
			return key;
		}catch(Exception e){
			print("Exception Caught while reading n.txt");
		}
		return 0;
	}

}