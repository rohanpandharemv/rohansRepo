package samples;



import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

       public static void main(String[] ag) {

              String driver = "com.mysql.jdbc.Driver";
              /*
              * String host = propertyHandler.getDBHost(); String port =
              * propertyHandler.getDBPort(); String user =
              * propertyHandler.getDBUser(); String pass =
              * propertyHandler.getDBPass(); String dbname =
              * propertyHandler.getDBName();
              */
              String utf8 = "?useUnicode=true&characterEncoding=UTF-8";
              Connection connection = null;

              try {
                     Class.forName(driver);
                     // Connection pooling
                     connection = DriverManager
                             .getConnection("jdbc:mysql://db3.nba.internal:3306/nba?useUnicode=true&characterEncoding=UTF-8","rpandhare","374PUA4b7q3");
                     // connection = DriverManager.getConnection("jdbc:mysql://" + host +
                     // ":" + port + "/" + dbname + utf8, user,
                     // pass);
                     if (connection != null) {
                    	 System.out.println("Connected Succesfully!!!");
                     } else {
                    	 System.out.println("Connection Failed!!!");	
                     }
              } catch (Exception e) {
                     e.printStackTrace();
              }
       }

}

