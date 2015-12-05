package yelp;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class JsonFileGenerator {
	public static String client = "babbo";
	public static String Author = "Arnold Russel";
	public static Integer limit = 10;

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Rohan.Pandhare\\Desktop\\share folder rohan\\JSONFiles\\"+"lowdown-perth.json");
		
		FileWriter fout = null;
		Integer count = 0;
		String Driver = "com.mysql.jdbc.Driver";
		String host = "qa-db.nba.internal";
		String port = "3306";
		String userPass = "root";
		String dbname = "nba";
		String utf8 = "?useUnicode=true&characterEncoding=UTF-8";
		String content = null;
		String title = null;
		String line =null;
		Integer date = 1400000000;
		Integer row_id = 2110000000;
		Integer user_id = 2110000000;
		Connection connection = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8, userPass, userPass);
			
			System.out.println("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8 +","+ userPass+"," +"Aw33uBlyn57");
			if(connection != null){
				System.out.println("Connection created!!!");
			}else{
				System.out.println("Connection with DB failed");
			}
			
			fout = new FileWriter(file);
			
			String query = "SELECT D.TITLE, D.CONTENT FROM DOCUMENT D WHERE D.DATASOURCE = '"+client+"'AND D.TITLE IS NOT NULL AND CONTENT IS NOT NULL LIMIT "+limit.toString()+"";
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				content = rs.getString("CONTENT");
				title = rs.getString("TITLE");
				line =  "{"
						+"\"rating\""+":"+randomNumGenerator(1, 5)+","
						+"\"text\""+":"+"\""+content.replaceAll("\"", " ").replaceAll("\n", "").replaceAll("'", "")+"\""+","
						+"\"excerpt\""+":"+"\""+title.replaceAll("\"", " ").replaceAll("\n", "").replaceAll("'","")+"\""+","
						+"\"time_created\""+":"+ date++ +","
						+"\"user\""+":"+"{"
									+"\"image_url\""+":"+"\"http://s3-media3.fl.yelpcdn.com/photo/Ojt3P6rYxQfnQXh82L4f0g/ms.jpg\"" +","
									+"\"id\""+":"+"\""+ user_id++ +"\""+","
									+"\"name\""+":"+"\""+ Author + count++ +"\""
									+"}"+","
						+"\"id\""+":"+row_id++			
						+"}";				
				fout.append(line+'\n');
				System.out.println(line);
			}
			
			fout.flush();
			System.out.println("Data flused to file "+file.getAbsolutePath());
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
	
	public static String randomNumGenerator(Integer min,Integer max){
		Random rand = new Random();
		Integer randNum = rand.nextInt((max-min)+1)+min;
		return randNum.toString();
	}
}