package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ExecutePHPFile {
	private Config config;
	
	public int getPHPLength(String fileRequested) {
		StringBuilder output = new StringBuilder();
	    try {
			String line;
			Process p = Runtime.getRuntime().exec("php -f "+config.ROOT_DIRECTORY+fileRequested);
			BufferedReader inputPHP = new BufferedReader (new InputStreamReader(p.getInputStream()));
			while ((line = inputPHP.readLine()) != null) {
				output.append(line);
			}
			inputPHP.close();
    	}
	    catch (Exception err) {
	    	err.printStackTrace();
	    }
	    return output.length();
	}
	
	public byte[] executePHP(String fileRequested) {
		 StringBuilder output = new StringBuilder();
		    try {
				String line;
				Process p = Runtime.getRuntime().exec("php -f"+config.ROOT_DIRECTORY+fileRequested);
				BufferedReader inputPHP = new BufferedReader (new InputStreamReader(p.getInputStream()));
				while ((line = inputPHP.readLine()) != null) {
					System.out.println(inputPHP.readLine());
					output.append(line);
				}
				inputPHP.close();
				System.out.println(output.toString());
	    	}
		    catch (Exception err) {
		    	err.printStackTrace();
		    }
		    byte[] filePHPData = new byte [output.length()];
		    filePHPData = output.toString().getBytes();
		    return filePHPData;
	}
	
	
}
