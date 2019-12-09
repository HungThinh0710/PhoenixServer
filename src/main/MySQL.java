package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.PortIsNotAvailableException;

public class MySQL {
//	public boolean isServerRunning;
//	
//	
//	public boolean isServerRunning() {
//		return isServerRunning;
//	}
//
//
//	protected static void setServerRunning(boolean isServerRunning) {
//		this.isServerRunning = isServerRunning;
//	}


	public boolean startMYSQL() throws PortIsNotAvailableException, IOException, InterruptedException{
		Utils utils = new Utils();
		StringBuilder output = new StringBuilder();
		int port = Config.getPortMySQLINI();
		boolean state = false;
	    	if(utils.isPortAvailable(port)) {
	    		String line;
				String startCommand_Debug = "D:\\Eclipse_newgen\\PhoenixServer\\mysql\\bin\\mysqld.exe --defaults-file=D:\\Eclipse_newgen\\PhoenixServer\\mysql\\bin\\my.ini --standalone --console";
				String startCommand_Debug2 = "D:\\Eclipse_newgen\\PhoenixServer\\command\\mysql_start.bat";
				
				String startCommand = Config.MYSQL_DIR+"\\bin\\mysqld.exe --defaults-file="+Config.MYSQL_CONFIG_FILE+" --standalone --console";
				Process p = Runtime.getRuntime().exec(startCommand_Debug);
				p.waitFor();
				BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
				while ((line = input.readLine()) != null) {
					output.append(line);
				}
				input.close();
				state = true;
	    	}
	    	else {
	    		state = false;
				throw new PortIsNotAvailableException("Port "+port+" is not available."); 
			}
	    return state;
	}
	
	
	public boolean stopMYSQL() throws InterruptedException {
		Utils utils = new Utils();
		int port = Config.getPortMySQLINI();
		boolean state = false;
		Thread.sleep(3000);
	    try {
	    	if(!utils.isPortAvailable(port)) {
				String shutdownMySQLCommand = "D:\\Eclipse_newgen\\PhoenixServer\\mysql\\bin\\mysqladmin.exe -u root shutdown";
				
				Process p = Runtime.getRuntime().exec(shutdownMySQLCommand);
				p.waitFor();
				state = true;
	    	}
	    	else {
	    		state = false;
				throw new PortIsNotAvailableException("Port "+port+" for MySQL is not started."); 
			}
    	}
	    catch (Exception err) {
	    	err.printStackTrace();
	    }
	    return state;
	}
	
	public int getMySQLPid(int port) {
		Utils utils = new Utils();
		StringBuilder output = new StringBuilder();
		int portReturn = 0;
	    try {
			String line;
//	    	if(!utils.isPortAvailable(port)) {
				String commandFindPort = "cmd /c netstat -a -n -o | find \""+port+"\"";
				Process p = Runtime.getRuntime().exec(commandFindPort);
				p.waitFor();
				BufferedReader input = new BufferedReader (new InputStreamReader(p.getInputStream()));
				if ((line = input.readLine()) != null) {
					output.append(line);
				}
				input.close();
				String strHanled = output.toString().replace(" ", ""); //Remove all space
				int lastCharacterIndex = strHanled.lastIndexOf("G"); //Get last character of LISTENING word, ->G
				portReturn = Integer.parseInt(strHanled.substring(lastCharacterIndex+1, strHanled.length()-1));
				
				return portReturn;
//	    	}
//	    	else {
//	    		portReturn = 0;
//				throw new PortIsNotAvailableException("Port "+port+" is not started."); 
//			}
    	}
	    catch (Exception err) {
	    	err.printStackTrace();
	    }
	    return portReturn;
	}
//	public static void main(String[] args) throws PortIsNotAvailableException, IOException, InterruptedException {
		//THIS FUNCTION IS ONLY FOR TEST
//		System.out.println(startMYSQL());
//		stopMYSQL();
//		String startCommand = Config.MYSQL_DIR+"\\bin\\mysqld --defaults-file="+Config.MYSQL_CONFIG_FILE+" --standalone --console";
//		System.out.println(startCommand);
//		System.out.println(getMySQLPid(3306));
//	};
}
