package samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class VpnConnect1 {
	
	final static String userName ="rpandhare";
	final static String password ="M3k$e9&c27";
		
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)throws Exception {
		
		File file = new File("C:\\Users\\Rohan.Pandhare\\Documents\\Keys\\Rohan_Pandhare_Private_Key.ppk"); 
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String line;
		StringBuffer sb1 = new StringBuffer();
		
		while( true ){
			if((line = br.readLine()) == null)
				break;
			
			sb1.append(line +'\n');
		}
		
		//System.out.println(sb1.toString()+"\n\n");
		
		Connection connection = new Connection("qa.db.nba.internal");
		
		System.out.println("Connection Object created");
		
		connection.connect();
		
		//connection.authenticateWithPassword("NewBrandAnalytics", "yxQz82.TbMzgU");
		
		//connection.authenticateWithPublicKey(userName, sb1.toString().toCharArray(), password);
		
		connection.authenticateWithPublicKey(userName, file, password);
		
		Session sesstion = connection.openSession();
		
		InputStream stdout = new StreamGobbler(sesstion.getStdout());
		
		BufferedReader stdOutReader = new BufferedReader(new InputStreamReader(stdout));
		
		System.out.println("Connected to VPN");
		
		String tempCommand = "man uname";
		
		System.out.println("Sending command : " + tempCommand);
		
		sesstion.execCommand(tempCommand);
		
		StringBuffer sb = new StringBuffer();
		
		while(true){
			String line1 = stdOutReader.readLine();
			
			if(line1 == null)
				break;
			sb.append(line+'\n');
				
		}
		
		System.out.println("Command Output : " + sb.toString());
	}
}
