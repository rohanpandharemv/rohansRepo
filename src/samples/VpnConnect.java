package samples;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;

public class VpnConnect {
	final static String username = "NewBrandAnalytics";
	final static String password = "yxQz82.TbMzgU";//" a_gZGj.QX2W}@;x4"
	
	public static void main(String[] args)throws Exception {
		URL url = new URL("http://perf-admin.nba.internal:8080/hyatt/index.jsp?page=Admin__Admin_P20");
		
		
		System.getProperties().put("http.proxyHost","vpn.newbrandanalytics.com");
		System.getProperties().put("http.proxyPort", 80);
		System.setProperty("http.proxyUser",username);
		System.setProperty("http.proxyPassword", password);
		System.getProperties().put("http.proxySet", true);
		
		
		Authenticator.setDefault(
				new Authenticator(){
					public PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(username, password.toCharArray());
					}
				});
		
		URLConnection connection = (HttpURLConnection) url.openConnection(); 
		
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
				
		if(connection!=null){
			System.out.println("Somewhat Success");
		}
		
	}
}
