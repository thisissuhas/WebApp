package endpoints;
import java.sql.*;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
public class RetrieveImage {
public static BufferedImage main(String[] args) {
	BufferedImage imag=null;
try{
	
	String connectionURL = "jdbc:mysql://localhost:3306/test" ;

	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection connection = DriverManager.getConnection(connectionURL, "root", "root");

	
	
PreparedStatement ps=connection.prepareStatement("select * from pic");
ResultSet rs=ps.executeQuery();



while(rs.next()){
	Blob aBlob = rs.getBlob("img");
	InputStream is = aBlob.getBinaryStream(1, aBlob.length());
	 imag=ImageIO.read(is);
	
}


			
connection.close();

			
}catch (Exception e) {e.printStackTrace();	}
return imag;
}
}