package endpoints;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.research.ws.wadl.Method;




@Path("/pmessages")
public class PatientMessages {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImage() {
		Response response = null;
		try {
		
		String josn =numberOfFiles();
		//System.out.println(josn);
			response= Response.ok(josn).build();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
        
    }

	private String numberOfFiles() {
		String message = null;
		File f = null;
	      File[] paths;
	      
	      try {  
	      
	         // create new file
	         f = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\ROOT\\images");
	         
	         // returns pathnames for files and directory
	         paths = f.listFiles();
	         
        	 JSONObject json = new JSONObject();
     		json.put("status", "ok");

     		JSONArray array = new JSONArray();
	         // for each pathname in pathname array
	         for(File path:paths) {
	        	 
	        	 
	        	 JSONObject item1 = new JSONObject();
	     		item1.put("url", "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/images/"+path.getName());
	     		item1.put("title",path.getName() );
	     		array.put(item1);

	     		
	     			            // prints file and directory paths
	            System.out.println(path.getName());
	         }
	         json.put("attachments", array);

	     		message = json.toString();
	     		parseResult(message);

	         
	      } catch(Exception e) {
	         
	         // if any error occurs
	         e.printStackTrace();
	      }
	      return message;
	}

	

	private static void parseResult(String result) {
		try {
			ArrayList<String> Ingredients_names = new ArrayList<>();

			JSONObject jsonObj = new JSONObject(result);

			JSONArray ja = jsonObj.getJSONArray("attachments");
			int len = ja.length();

			for (int j = 0; j < len; j++) {
				JSONObject json = ja.getJSONObject(j);
				Ingredients_names.add(json.getString("url").toString());
			}

			for (String s : Ingredients_names) {
				//System.out.println("Is it " + s);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	

}
