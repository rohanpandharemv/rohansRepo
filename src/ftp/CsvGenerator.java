package ftp;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.output.FileWriterWithEncoding;


public class CsvGenerator  {
	
	public static String client = "hyatt";
	public static Integer limit = 100;
	public static String fileLocation = "C:\\Users\\Rohan.Pandhare\\Desktop\\ftp";
	
	public static void main(String[] args)
	{
		File file = new File(fileLocation+ client +"Test.csv");
		
		FileWriterWithEncoding fout = null;
		String CsvHeader = "0,1,2,3,4,5a,5b,6a,6b,7,9a,9b,10,11,12,13a,13b";
		String Driver = "com.mysql.jdbc.Driver";
		String host = "qa-db.nba.internal";
		String port = "3306";
		String userPass = "root";
		String dbname = "nba";
		String utf8 = "?useUnicode=true&characterEncoding=UTF-8";
		String content = null;
		String title = null;
		String line =null;
		int count= 1;
		Connection connection = null;
		
		Statement stmt = null;
		
		ResultSet rs = null;
		
		
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection("jdbc:mysql://"+ host + ":" + port + "/" + dbname +utf8, userPass, userPass);
			
			if(connection != null){
				System.out.println("Connection created!!!");
			}else{
				System.out.println("Connection with DB failed");
			}
			
			fout = new FileWriterWithEncoding(file, "UTF-8");
			fout.append(CsvHeader+'\n');
			
			String query = "SELECT D.TITLE, D.CONTENT FROM DOCUMENT D WHERE D.DATASOURCE = '"+client+"'AND D.TITLE IS NOT NULL AND CONTENT IS NOT NULL LIMIT "+limit.toString()+"";
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(query);
			System.out.println(CsvHeader);
			while(rs.next()){
				content = rs.getString("CONTENT");
				title = rs.getString("TITLE");
				line =  "7823," 																//0
						+ randomNamesGenerator() +","											//1
						+randomNumGenerator(20,60) 												//2
						+","+randomGenderGenerator()											//3	
						+",2 Slightly agree"													//4
						+",1 Agree,"															//5a
						+randomNamesGenerator()+" "+ title.replaceAll(","," ").replaceAll("\n", " ").replaceAll("\"", " ")	//5b
						+","+randomAnswerGenerator()											//6a	
						+","+randomAnswerGenerator()											//6b
						+",Below Average,"														//7
						+randomNumGenerator(3, 5)+","											//9a
						+randomNamesGenerator()+" "+ content.replaceAll("\n","").replaceAll(",","").replaceAll("\"", " ")	//9b	
						+","+randomRecommendationGenerator()+","                                //10
						+randomNumGenerator(3, 5)												//11
						+","+randomGroupGenerator()+","											//12	
						+randomNumGenerator(3, 5)												//13a						
						+","+randomRecommendationGenerator();									//13b
				
				if(count < limit){
					fout.append(line+'\n');
				}
				count++;
				
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
	/**
	 * MEthod to generate random numbers within given range		
	 * @param min
	 * @param max
	 * @return Number
	 */
	public static String randomNumGenerator(Integer min,Integer max){
		Random rand = new Random();
		Integer randNum = rand.nextInt((max-min)+1)+min;
		return randNum.toString();
	}
	
	
	/**
	 * Method to generate the random Names
	 * @return String Names
	 */
	public static String randomNamesGenerator(){
		String nameString = "Rohan Abdhesh Piyush Rajat Rachna Shama Santosh Sumeet Nikesh Nihanth Shrushti" +
	            "Amit Ashok Prasad Kajol Shilpa Shreya Rucha Keren Christina Betty Sheela Shona Sangeeta Hezel Biren Kiran Swapnil Dhruva Samar Nikhil Vaibhav Krishna Hemlata Reema Seema Deepak Jyoti Pushpa Priya Ratan Shraddha " +
	            "Poonam Leena Sonal Ashwini Arya Disha Karuna Katrina Radhika Namrata Khushi Narayan Taylor Bharat Avinash Aalok Malik Mayank Munish Monika Rahul Nihanth Shailesh Shrikant Sunil" +
	            "Rashi Madrid Alex Robin Wyatt Dick Raksha Spruha Darren Alba John Messi Leonel Derek Plaso Marko Dinesh Mahela";
		String lastnameString = "Pandhdare Kumar Neema Tandon Agarwal Ganpa Zanzal Gandhi Bhalkikar " +
	                "Vaishnavi Kedar Thakur Kapate Marade Singh Paayo Nagpure Golcha Pradhan Gaurav" +
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
	
	/**
	 * Method to return random Gender M/F
	 * @return String Gender
	 */
	public static String randomGenderGenerator(){
		String genderList = "M F";
		String[]  genderArr = genderList.split(" ");
		
		List<String> Gender = new ArrayList<String>();
		
		for (int i = 0; i < genderArr.length; i++) {
			Gender.add(genderArr[i]);
		}
		
		Random numberGen = new Random();
		int rand = numberGen.nextInt(Gender.size());
		
		return Gender.get(rand); 
	}
	
	/**
	 * Method to return random answer Yes/No
	 * @return String answer
	 */
	public static String randomAnswerGenerator(){
		String answerList = "Yes No";
		String[]  answerArr = answerList.split(" ");
		
		List<String> Answer = new ArrayList<String>();
		
		for (int i = 0; i < answerArr.length; i++) {
			Answer.add(answerArr[i]);
		}
		
		Random numberGen = new Random();
		int rand = numberGen.nextInt(Answer.size());
		
		return Answer.get(rand); 
	}
	
	
	/**
	 * Method to return random Group
	 * @return String Gender
	 */
	public static String randomGroupGenerator(){
		String groupList = "Family Friends Batchmates Lads Girlfriends Boyfriends Parents GrandParents Buddys Co-workers Best-Friends" +
				"Bosses Folks Fellows Students Runners Dancers Hawkers Journalist Cricketrs FootBallers Room-Mates Siblings";
		String[]  groupArr = groupList.split(" ");
		
		List<String> Groups = new ArrayList<String>();
		
		for (int i = 0; i < groupArr.length; i++) {
			Groups.add(groupArr[i]);
		}
		
		Random numberGen = new Random();
		int rand = numberGen.nextInt(Groups.size());
		
		return Groups.get(rand); 
	}
	
	
	/**
	 * Method to generate random recommendation
	 * @return String recommendation
	 */
	public static String randomRecommendationGenerator(){
		String recoms  = "I will recommend this to everyone.-We will recommend this to everyone-not recommended at all-Good to go.-Back yourself from this place-This is like a palace." +
				"-They've recommend this to me.-You have to go there & see it.-Pure brilliant for this place-Have to be very very clear.-To be very very clear this place is like a hell-Damn I am not going here again-" +
				"Thank you for wonderfull stay will come here again.-Worst case scenario.-For those who do it by their own way.-Woooooooooooooooooooowwwwwwwwwwwwwwwwwww stay-Still in dilemma, will tell you later.";
		String[] recArr = recoms.split("-");
		
		List<String> recomList = new ArrayList<String>();
		
		for(int i = 0; i < recArr.length; i++) {
			recomList.add(recArr[i]);
		}
		
		Random randNum = new Random();
		
		int rand = randNum.nextInt(recomList.size());
		
		return recomList.get(rand);
	}
	
}

/*
 driver=com.mysql.jdbc.Driver
 host=qa-db.nba.internal
 port=3306
 user=root
 pass=root
 dbname=nba
*/