package config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Config {
	
	private static Properties config;
	private static InputStream is;
	
	private static Properties getConfig() {
		if(config == null){
			
			config = new Properties();
			is = Config.class.getResourceAsStream("/config/properties/config.properties");  
			try {
				
				config.load(is);
			} catch (FileNotFoundException e1) {				
				e1.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
			
				
		
		}
		return config;
	}
	public static void setProperty(String key,String value) {
		getConfig().setProperty(key, value);
	}
	public static String getProperty(String property) {
		return getConfig().getProperty(property);
		
	}
	
	
	
	
}
