package yelp;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.output.FileWriterWithEncoding;

public class JsonCreator {
	public static String client = "hyatt";       //Any of the available datasource
	public static Integer limit = 1000;             // Number of records need to create in file
	public static String filename = "original-pizza-indian-shores-2";           // name of the file, should be name of the hotel
    public static String fileLocation = "C:\\Users\\Rohan.Pandhare\\Desktop\\share folder rohan\\JSONFiles\\"; // location of the file to be created
    public static String MID = "S";
    public static String UID = "H";
    
	public static void main(String[] args) {
		
		File file = new File(fileLocation+filename+".json");
		
		FileWriterWithEncoding fout = null;
		Integer count = 0;
		String Driver = "com.mysql.jdbc.Driver";
		String host = "qa-db.nba.internal";
		String port = "3306";
		String userPass = "root";
		String dbname = "nba";
		String utf8 = "?useUnicode=true&characterEncoding=UTF-8";
		
		String content = null;
		String line =null;
		
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
			
			fout = new FileWriterWithEncoding(file,"UTF-8");
			
			String query = "SELECT D.TITLE, D.CONTENT FROM DOCUMENT D WHERE D.DATASOURCE = '"+client+"'AND D.TITLE IS NOT NULL AND CONTENT IS NOT NULL LIMIT "+limit.toString()+"";
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				content = rs.getString("CONTENT");
				line =  "{"
						+"\"created\""+":"+ "\""+randomNumGenerator(2016, 4000)+"-11-"+ randomNumGenerator(1, 30) +" "+randomNumGenerator(10, 24)+":"+randomNumGenerator(10, 59)+":"+randomNumGenerator(10, 60)+"\"" +","
						+"\"id\""+":"+ "\""+UID+randomCharectors()+MID+"\""+","
						+"\"language\""+":"+ "\"en\""+","
						+"\"rating\""+":"+randomNumGenerator(1, 5)+","
						+"\"reply_to_review_url\""+":"+"\"https://biz.yelp.com.sg/r2r/--g1DLf0h8kW4haLlYmRvg/comment/8Stq_lmhHw8Gt459P_4c7g/compose?utm_campaign=yelp_feed&utm_medium=feed_v2&utm_source=sprinklr\""+","
						+"\"text\""+":"+"\""+content.replaceAll("\"", " ").replaceAll("\n", "").replaceAll("'", "")+"\""+","
						+"\"url\""+":"+"\""+"http://www.yelp.com.sg/biz/clementi-881-coffee-station-singapore?hrid=sVDgFiMMhddnDabOWYCwcg&utm_campaign=yelp_feed&utm_medium=feed_v2&utm_source=sprinklr"+"\""+","
						+"\"user\""+":"+"{"
									+"\"photo_url\""+":"+"\"http://s3-media3.fl.yelpcdn.com/photo/Ojt3P6rYxQfnQXh82L4f0g/ms.jpg\"" +","
									+"\"id\""+":"+"\""+ MID+randomCharectors()+UID+"\""+","
									+"\"name\""+":"+"\""+ randomNamesGenerator() +"\""
									+"}"			
						+"}";
				
				fout.append(line);
				count++;
				if(count != limit)
				{	
					fout.append('\n');
				}
				System.out.println(line);
			}
			
			fout.flush();
			
			System.out.println("Data written to file "+file.getAbsolutePath());
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
	
	/**
	 * Method to return the random number in between given range in String format
	 * @param min
	 * @param max
	 * @return String
	 */
	public static String randomNumGenerator(Integer min,Integer max){
		Random rand = new Random();
		Integer randNum = rand.nextInt((max-min)+1)+min;
		return randNum.toString();
	}
	
	/**
	 * Method to return the random string
	 * @return String
	 */
	public static String randomCharectors(){
		
		final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int N = alphabet.length();
	    
	    Random r = new Random();
	    
	    Character finalRandomString[] = new Character[15];
	    String finalString = "";
	    
	    for (int i = 0; i < 15; i++) {
	         finalRandomString[i]  =  alphabet.charAt(r.nextInt(N));
	         finalString = finalString + finalRandomString[i].toString(); 
	    }
		
		return finalString;
	}
	
	/**
	 * Method to generate the random Names
	 * @return String Names
	 */
	public static String randomNamesGenerator(){
		String nameString = "Rohan Abdhesh Piyush Rajat Rachna Shama Santosh Sumeet Nikesh Nihanth Shrushti" +
	            "Amit Prasad Biren Kiran Swapnil Dhruva Samar Nikhil Vaibhav Krishna Hemlata Reema Seema Deepak Jyoti Pushpa Priya Ratan Shraddha " +
	            "Poonam Leena Sonal Ashwini Arya Disha Karuna Katrina Radhika Namrata Khushi Taylor Bharat Malik Mayank Munish Monika Rahul Nihanth Shailesh Shrikant Sunil" +
	            "Rashi Madrid Alex Robin Wyatt Dick Raksha Spruha Darren Alba John Messi Leonel Derek Plaso Marko";
		String lastnameString = "Pandhdare Kumar Neema Tandon Agarwal Ganpa Zanzal Gandhi Bhalkikar " +
	                "Vaishnavi Kedar Thakur Kapate Marade Singh Paayo Nagpure Golcha Pradhan " +
	                "Salame Tripathi Khosla Khanna Sharma Maiti Gaikwad Harrir Dhillon Modi Kapoor Devgan Roshan Bhatt Laddha " +
	                "Pandit Vasule Mankar Saini Dean Boon Harris Jessica Snow Leonel Mullair Polo Jethwa Desai Ericson" +
	                "Freeman Joshnson Ponting Alenson Pinter Deanson Dallason Morkel Obama Birdekar Yadav Jadhav Barbade Murkute Dikase Rai Bhatiya Siddiquie Pathak";
		
		Random numberGen = new Random();
		int rand = 0;
		String Name = null;
		String nameArr[] = nameString.split(" ");
		String lstNameArr[] = lastnameString.split(" ");
	
		List<String> names = new ArrayList<String>();
		List<String> lastNames = new ArrayList<String>();

		for(int num = 0; num < nameArr.length ; num++){
			names.add(nameArr[num]);
		}

		for(int num = 0; num < lstNameArr.length ; num++){
			lastNames.add(lstNameArr[num]);
		}

		rand = numberGen.nextInt(names.size());
		Name =   names.get(rand);
		rand = numberGen.nextInt(lastNames.size());
		Name = Name +" "+lastNames.get(rand);
			
		return Name;		
	}
}