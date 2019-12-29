package main;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class ExecutePHPFile {
	private Config config;
	
	protected String [] handlePath(String path) {
		/*
		 * This function return:
		 * strPathFile[0] => path of file
		 * strPathFile[1] => file need to execute
		 */
		System.out.println("##########");
		String [] strPathFile = new String[2];
		
		int endFileNum = path.lastIndexOf("/");
		
		strPathFile[0] = path.substring(0,endFileNum).replace("/", "\\");
		strPathFile[1] = path.substring(endFileNum+1,path.length());
//		strPathFile[1] = strPathFile[1].replace("/", "\\")

		System.out.println("QQ_Firstfile:"+strPathFile[0]);
		System.out.println("QQ_Endfile:"+strPathFile[1]);
		
		return strPathFile;
	}
	
	public StringBuilder executePHP(String fileRequested) {
		
		 StringBuilder outPHPData = new StringBuilder();
		 String [] strPathFile = handlePath(fileRequested);
		 String filePath = strPathFile[0];
		 String fileExe = strPathFile[1];
         String line = null;
         Process p = null;
         StringBuilder output_test = new StringBuilder(); //only for test env

		 try {
	            /* Create process */
//			 	String filePathFilter  = detectPhpMyAdminUri(filePath);
			 	String filePathFilter  = filePath;

		 		String param, fileExeFinal;
		 		String getParamAndFileExe[] = getFileExcuteAndParamPhp(fileRequested, fileExe);
		 		
		 		
		 		Runtime rt = Runtime.getRuntime();
		 		Process proc = null;
			 	if(filePathFilter.equals("phpmyadmin")) {
			 		String command = "cmd /c D: && cd "+Config.RESOURCE_PATH+filePath+" && php -f "+getParamAndFileExe[0]+" "+getParamAndFileExe[1];
//		            p = Runtime.getRuntime().exec(command);
			 		proc = rt.exec(command);
		            System.out.println("__command:"+command);

			 	}
			 	else {
//			 		Process p = Runtime.getRuntime().exec("cmd /c D: && cd D:\\Eclipse_newgen\\PhoenixServer\\phpmyadmin && php -f index.php");
//		            String command = "cmd /c C: && cd "+Config.DEFAULT_ROOT_PATH+filePath+" && php -f"+getParamAndFileExe[0] + " "+getParamAndFileExe[1];
		            String command = "cmd /c C: && php-cgi.exe -q "+Config.DEFAULT_ROOT_PATH+filePath+getParamAndFileExe[0] + " "+getParamAndFileExe[1];
		            
			 		
			 		proc = Runtime.getRuntime().exec(command);
			 		 System.out.println("__command_wo:"+command);
			 	}
			 	
//		 		//DEBUG HOT
////		 		String command = "cmd /c C: && cd "+Config.DEFAULT_ROOT_PATH+filePath+" && php -f "+getParamAndFileExe[0] + " "+getParamAndFileExe[1];
//		 		String command = "cmd /c C: && cd "+Config.DEFAULT_ROOT_PATH+filePath+" && php-cgi.exe -f "+  Config.DEFAULT_ROOT_PATH+filePath+ getParamAndFileExe[0] + " "+getParamAndFileExe[1];
//		 		proc = Runtime.getRuntime().exec(command);
//		 		 System.out.println("__command_wo:"+command);
		 		
		 		
			 	 BufferedReader stdInput = new BufferedReader(new 
		                 InputStreamReader(proc.getInputStream()));
	
		            BufferedReader stdError = new BufferedReader(new 
		                 InputStreamReader(proc.getErrorStream()));
	
		            // Read the output from the command
		            System.out.println("Here is the standard output of the command:\n");
		            String s = null;
		            while ((s = stdInput.readLine()) != null) {
		                output_test.append(s+"\n");
		            }
	
		            // Read any errors from the attempted command
		            System.out.println("Here is the standard error of the command (if any):\n");
		            while ((s = stdError.readLine()) != null) {
		                System.out.println("stdErr:"+s);
		            }
//            	p.waitFor();
//			 	p.waitFor();
	            /* Get OuputStream */
//	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(p.getOutputStream())), true);
////	            
//	            /* Read the output of command prompt */
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	            line = reader.readLine();
//	            outPHPData.append(line);
//	            /* Read upto end of execution */
//	            while (line != null) {
//	                /* Pass the value to command prompt/user input */
//	                writer.println("");
//	                line = reader.readLine();
//	                outPHPData.append(line+"\n");
//	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//         try {            
	            
	            /*
	             * TESTTTTTTTTttt
	             */
//	            Runtime rt = Runtime.getRuntime();
//	        	 String command = "cmd /c D: && cd D:\\Eclipse_newgen\\PhoenixServer\\phpmyadmin && php.exe -f phpmyadmin.css.php";
//
//	            Process proc = rt.exec(command);
//
//	            BufferedReader stdInput = new BufferedReader(new 
//	                 InputStreamReader(proc.getInputStream()));
//
//	            BufferedReader stdError = new BufferedReader(new 
//	                 InputStreamReader(proc.getErrorStream()));
//
//	            // Read the output from the command
//	            System.out.println("Here is the standard output of the command:\n");
//	            String s = null;
//	            while ((s = stdInput.readLine()) != null) {
//	                output_test.append(s);
//	                System.out.println(s);
//	            }
//
//	            // Read any errors from the attempted command
//	            System.out.println("Here is the standard error of the command (if any):\n");
//	            while ((s = stdError.readLine()) != null) {
//	                System.out.println("stdErr:"+s);
//	            }
	            
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 	return outPHPData;
		 	return output_test;
	}
	
	protected String detectPhpMyAdminUri(String uri) {
		String tmp = uri.substring(1,uri.length());
		System.out.println("QQ_loc"+tmp);
		
		if(tmp.length()>10) { //phpmyadmin->10letters
			return tmp.substring(0,tmp.indexOf("\\"));
		}
		else {
			return tmp;
		}
	}
	
	protected String[] getFileExcuteAndParamPhp(String rawPhp, String fileExe) {
		/*
		 * This function return:
		 * paramAndFile[0] => fileExecute
		 * paramAndFile[1] => param
		 */
		String paramAndFile[] = new String [2];
		Uri uri = new Uri(rawPhp);
		String query = uri.getQuery();
		System.out.println("QQ_query:"+query);
		if(fileExe.contains(".php?")) {
			paramAndFile[0] = fileExe.substring(0,fileExe.indexOf("?"));
			paramAndFile[1] = query.replaceAll("&", " ");
		}
		else {
			paramAndFile[0] = fileExe;
			paramAndFile[1] = "";
		}
		
		return paramAndFile;
			
	}
	
}
