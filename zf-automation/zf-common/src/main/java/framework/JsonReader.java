package framework;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * This class help to create method for reading data from Json
 */
public class JsonReader {

	static JSONParser jsonParser;
	static FileReader reader;
	static JSONObject detailobject;
	static Object object;
	
	/**
	 * This method will read the file from the path given
	 * 
	 */	public JsonReader() {

		 jsonParser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);

		 try {
			 reader = new FileReader(EnvironmentManager.getDataFromJsonPath());
			 TestLogger.fileInfo(EnvironmentManager.getDataFromJsonPath());
			
				object = jsonParser.parse(reader);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 catch (FileNotFoundException e) {
			 
			 TestLogger.errorMessage(e.getMessage());
		 }		
	 }

	 /**
	  * Method will return the json object for the respective module
	  * 
	  * @param modulename 
	  * 
	  * @return Jsonobject
	  */
	 public static JSONObject getJsonObject(String moduleName) {

		
		 try {
//			 object = jsonParser.parse(reader);
			 JSONArray jsonList = (JSONArray) object;
			 for(int i=0;i<jsonList.size();i++) {
				 JSONObject moduleObject=(JSONObject) jsonList.get(i);
				 if (moduleObject.containsKey(moduleName)){
					 detailobject=(JSONObject) moduleObject.get(moduleName);
					 TestLogger.testMessage(moduleName);
					 return detailobject;
					 }
			 }
		 } catch (Exception e) {
			 TestLogger.errorMessage(e.getMessage());
			 Assert.fail(e.getMessage());
		 }
		 return null;

	 }

	 /**
	  * This method will check the value is present for a particular key and will return the the value
	  * 	  
	  * @param moduleName : Mention the name of the module
	  * @param key : Mention the submodule as key
	  * 
	  * @return string value
	  */
	 public String getJsonData(String key) {

		 String values=null;
		 try {
			 
			 values=(String) detailobject.get(key);
			 TestLogger.testMessage(values);
			 return values;
		 } catch (Exception e) {
			 TestLogger.errorMessage(e.getMessage());
			 Assert.fail(e.getMessage());
		 }
		 return null;
	 }

}
