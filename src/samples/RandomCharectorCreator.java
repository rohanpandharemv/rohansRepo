package samples;

import java.util.Random;

public class RandomCharectorCreator {
	public static void main(String[] args) {
		/*final String alphabet = "0123456789ABCDE";
	    final int N = alphabet.length();

	    Random r = new Random();

	    for (int i = 0; i < 15; i++) {
	        System.out.print(alphabet.charAt(r.nextInt(N)));
	    }*/
		
		
		for(int i = 1; i < 10; i++){
			System.out.println(randomCharector());
		}
	}
	
	
	public static String randomCharector(){
		
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
}
