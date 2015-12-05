package samples;

import java.util.*;

public class RandomStringGenerator {
	public static void main(String[] args) {
		
	
		for(int i = 0 ; i < 50; i++){
			System.out.println(randomNamesGenerator());
		}
		
	}
	
	public static String randomNamesGenerator(){
		String nameString = "Rohan Abdhesh Piyush Rajat Rachna Shama Santosh Sumeet " +
	            "Amit Prasad Reema Seema Deepak Jyoti Pushpa Priya Ratan Shraddha " +
	            "Bharat Mayank Munish Monika Rahul Nihanth Shailesh Shrikant Sunil" +
	            "Alex Robin Wyatt Dick Darren Alba John Messi Leonel Derek Plaso Marko";
		String lastnameString = "Pandhdare Kumar Neema Tandon Agarwal Ganpa Zanzal Gandhi Bhalkikar " +
	                "Vaishnavi Kedar Thakur Kapate Marade Singh Paayo Nagpure Golcha Pradhan " +
	                "Salame Tripathi Pandit Vasule Mankar Saini Dean Boon Harris Jessica Snow Leonel Mullair Polo";
		
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