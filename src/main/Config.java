package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.ini4j.Ini;

public class Config {
	public static final File ROOT_DIRECTORY = new File(".");
	public static final String DEFAULT_FILE = "default_html\\default.html";
	public static final String FILE_NOT_FOUND = "default_html\\404.html";
	public static final String METHOD_IS_NOT_SUPPORTED = "method_not_supported.html";
	public static final String CONFIG_INI_PSERVER_FILE_NAME = "cfg_p.ini";
	protected static final String CURRENT_USER_HOME_DIR = System.getProperty("user.home");
	
	protected static final String PHOENIX_SERVER_DATA_PATH = CURRENT_USER_HOME_DIR+"\\PhoenixServer\\";
	
	public static final String INI_FOLDER_PATH = CURRENT_USER_HOME_DIR+"\\PhoenixServer\\Config\\";
	protected static final String DEFAULT_ROOT_PATH = CURRENT_USER_HOME_DIR+"\\PhoenixServer\\nhtdocs\\";
	protected static final int DEFAULT_PORT = 80;
	
	
	//COMMAND CONFIG
	public static final File COMMAND_DIR = new File("./command");
	public static final String MYSQL_START_BAT_FILE = COMMAND_DIR+"\\mysql_start.bat";
	public static final String MYSQL_STOP_BAT_FILE = COMMAND_DIR+"\\mysql_stop.bat";
	
	//MYSQL CONFIG
	public static final File MYSQL_DIR = new File("/mysql");
	public static final String MYSQL_CONFIG_FILE = "D:\\Eclipse_newgen\\PhoenixServer\\mysql\\bin\\my.ini";
	
	
	//ROOT_RESOURCE CONFIG
	public static final File RESOURCE_PATH = new File("D:\\Eclipse_newgen\\PhoenixServer\\");
	
	
	public static final int PORT = 80;
	// verbose mode
	public static final boolean verbose = true;
	
	
	
	protected static void initialConfigFolder() {
		File phoenixServerFolder = new File(PHOENIX_SERVER_DATA_PATH);
		if(!phoenixServerFolder.exists()) {
			System.out.println("not exist");
			File file = new File(PHOENIX_SERVER_DATA_PATH);
			if(file.mkdirs()){
			    System.out.println("CONFIG::[Created PhoenixServer folder in home dir]");
			}else 
				System.out.println("CONFIG::[Can NOT create PhoenixServer folder in home dir]");
		}
		
		File configFolder = new File(INI_FOLDER_PATH);
		if(!configFolder.exists()) {
			System.out.println("not exist");
			File file = new File(INI_FOLDER_PATH);
			if(file.mkdirs()){
				createINIConfigrationFile();
			    System.out.println("CONFIG::[Created PhoenixServer/Config folder in home dir]");
			}else 
				System.out.println("CONFIG::[Can NOT create PhoenixServer/Config folder in home dir]");
		}
		File rootFolder = new File(DEFAULT_ROOT_PATH);
		if(!rootFolder.exists()) {
			System.out.println("not exist");
			File file = new File(DEFAULT_ROOT_PATH);
			if(file.mkdirs()){
			    System.out.println("CONFIG::[Created nhtdocs folder]");
			}else 
				System.out.println("CONFIG::[Can NOT create nhtdocs folder]");
		}
		
	}
	
	public static void createINIConfigrationFile(){
		try {
			initialConfigFolder();
			File iniFile = new File(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME);
			if(!iniFile.isFile()){
				if(iniFile.createNewFile()) {
					System.out.println("CONFIG::[Created cfg_p.ini]");
					restoreDefaultCfgData();
				}
				else System.out.println("CONFIG::[CAN NOT CREATE cfg_p.ini FILE]");
			}
			else 
				System.out.println("CONFIG::[cfg_p.ini is exist]");
			} 
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void changePortPServerINI(int port) {
		try {
			Ini ini = new Ini(new File(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME));
            ini.put("PServer", "Port", port);
            ini.store();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void changeRootPServerINI(String path) {
		try {
			Ini ini = new Ini(new File(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME));
            ini.put("PServer", "RootDir", path);
            ini.store();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void restoreDefaultCfgData() {
		 try {
	        	Ini ini = new Ini(new File(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME));
	            ini.put("PServer", "Port", DEFAULT_PORT);
	            ini.put("PServer", "RootDir", DEFAULT_ROOT_PATH);
	            ini.put("PServer", "PHPDir", "NULL");
	            ini.store();
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	
	public static int getPortPServerINI() {
		int port = 0;
		try {
			Ini ini = new Ini();
        	ini.load(new FileReader(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME));
        	System.out.println(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME);
        	Ini.Section pServerIni = ini.get("PServer");
        	port = Integer.parseInt(pServerIni.get("Port"));
        	return port;
		} catch (Exception e) {
			// TODO: handle exception
			return port;
		}
	}
	
	public static String getPathRootPServerINI() {
		String path = null;
		try {
			Ini ini = new Ini();
        	ini.load(new FileReader(INI_FOLDER_PATH+CONFIG_INI_PSERVER_FILE_NAME));
        	Ini.Section pServerIni = ini.get("PServer");
        	path = pServerIni.get("RootDir");
        	return path;
		} catch (Exception e) {
			// TODO: handle exception
			return path;
		}
	}
	
	
	public static int getPortMySQLINI() {
		int port = 0;
		try {
			Ini mySQLini = new Ini();
			mySQLini.load(new FileReader(MYSQL_CONFIG_FILE));
        	Ini.Section mySQLIni = mySQLini.get("mysqld");
        	port = Integer.parseInt(mySQLIni.get("port"));
        	return port;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return port;
		}
	}
	public static void main(String args[]) {
//		initialConfigFolder();
//		createINIConfigrationFile();
////		restoreDefaultCfgData();
//		changePortPServerINI(80);
//		System.out.println(getPortPServerINI());
//	
//		System.out.println(getPortMySQLINI());
	}
}
