package fix10160;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.output.FileWriterWithEncoding;

public class Fix10160 {
	public static void main(String[] args) {
		Pattern patt = Pattern.compile("https?://www.agoda.com(?:/[a-z]{2}-[a-z]{2})?/([^?/]+)/hotel/([^?/]+).html.*?");
		
		File file = new File("C:\\Users\\Rohan.Pandhare\\Desktop\\PERFAgodaCorrectURL.txt");
		FileWriterWithEncoding fout = null;
		Integer count = 0;
		String Driver = "com.mysql.jdbc.Driver";
		//String host = "qa-db.nba.internal";
		String host = "db3.nba.internal";
		String port = "3306";
		String userPass = "root";
		String dbname = "nba";
		String utf8 = "?useUnicode=true&characterEncoding=UTF-8";
		
		String content = null;
		String line =null;
		
		String ID = null;
		String URL = null; 
		
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8, "rpandhare" , "374PUA4b7q3");
			//connection = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8, userPass , userPass);
			System.out.println("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8 +","+ userPass+"," +userPass);
			if(connection != null){
				System.out.println("Connection created!!!");
			}else{
				System.out.println("Connection with DB failed");
			}
			
			fout = new FileWriterWithEncoding(file,"UTF-8");
			
			String query = "SELECT ID, URL FROM HARVESTER WHERE SOURCE_ID = '268401487' AND URL IS NOT NULL";
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				ID = rs.getString("ID");
				URL = rs.getString("URL");
				
				Matcher m = patt.matcher(URL);
				
				if(m.matches()){
					System.out.println(ID+" - "+ URL);
					fout.append(ID + " - " + URL + '\n');
					count++;
				}
				
			}
			
			fout.flush();
			
			System.out.println(count + " records written to file " + file.getAbsolutePath());
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(connection != null){
					connection.close();
					System.out.println("Connection Closed!!!!");
				}else{
					System.out.println("Connection dose not exist!!!");
				}
				
				if(file != null){
					file = null;
					System.out.println("File closed!!!");
				}else{
					System.out.println("File is already closed!!!");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}
}
