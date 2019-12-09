package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Date;

public class Utils{
	
	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	
	public byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) 
				fileIn.close();
		}
		
		return fileData;
	}
	
	// return supported MIME Types
	public String getContentType(String fileRequested) {
		
		if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
			return "text/html";
		if(fileRequested.endsWith(".aac"))
			return "audio/aac";
		if(fileRequested.endsWith(".abw"))
			return "application/x-abiword";
		if(fileRequested.endsWith(".arc"))
			return "application/x-freearc";
		if(fileRequested.endsWith(".avi"))
			return "video/x-msvideo";
		if(fileRequested.endsWith(".azw"))
			return "application/vnd.amazon.ebook";
		if(fileRequested.endsWith(".bin"))
			return "application/octet-stream";
		if(fileRequested.endsWith(".bz"))
			return "application/x-bzip";
		if(fileRequested.endsWith(".bz2"))
			return "application/x-bzip2";
		if(fileRequested.endsWith(".csh"))
			return "application/x-csh";
		if(fileRequested.endsWith(".csv"))
			return "text/csv";
		if(fileRequested.endsWith(".doc"))
			return "application/msword";
		if(fileRequested.endsWith(".docx"))
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		if(fileRequested.endsWith(".eot"))
			return "application/vnd.ms-fontobject";
		if(fileRequested.endsWith(".epub"))
			return "application/epub+zip";
		if(fileRequested.endsWith(".gz"))
			return "application/gzip";
		if(fileRequested.endsWith(".gif"))
			return "image/gif";
		if(fileRequested.endsWith(".ico"))
			return "image/vnd.microsoft.icon";
		if(fileRequested.endsWith(".ics"))
			return "text/calendar";
		if(fileRequested.endsWith(".jar"))
			return "application/java-archive";
		if(fileRequested.endsWith(".json"))
			return "application/json";
		if(fileRequested.endsWith(".jsonld"))
			return "application/ld+json";
		if(fileRequested.endsWith(".mid"))
			return "audio/midi";
		if(fileRequested.endsWith(".midi"))
			return "audio/x-midi";
		if(fileRequested.endsWith(".mjs"))
			return "text/javascript";
		if(fileRequested.endsWith(".mp3"))
			return "audio/mpeg";
		if(fileRequested.endsWith(".mpeg"))
			return "video/mpeg";
		if(fileRequested.endsWith(".mpkg"))
			return "application/vnd.apple.installer+xml";
		if(fileRequested.endsWith(".odp"))
			return "application/vnd.oasis.opendocument.presentation";
		if(fileRequested.endsWith(".ods"))
			return "application/vnd.oasis.opendocument.spreadsheet";
		if(fileRequested.endsWith(".odt"))
			return "application/vnd.oasis.opendocument.text";
		if(fileRequested.endsWith(".oga"))
			return "audio/ogg";
		if(fileRequested.endsWith(".ogv"))
			return "video/ogg";
		if(fileRequested.endsWith(".ogx"))
			return "application/ogg";
		if(fileRequested.endsWith(".opus"))
			return "audio/opus";
		if(fileRequested.endsWith(".otf"))
			return "font/otf";
		if(fileRequested.endsWith(".png"))
			return "image/png";
		if(fileRequested.endsWith(".pdf"))
			return "application/pdf";
		if(fileRequested.endsWith(".php")) //PHP in HERE
			return "text/html";
		if(fileRequested.endsWith(".php5"))
			return "text/php";
		if(fileRequested.endsWith(".ppt"))
			return "application/vnd.ms-powerpoint";
		if(fileRequested.endsWith(".pptx"))
			return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		if(fileRequested.endsWith(".rar"))
			return "application/x-rar-compressed";
		if(fileRequested.endsWith(".rtf"))
			return "application/rtf";
		if(fileRequested.endsWith(".sh"))
			return "application/x-sh";
		if(fileRequested.endsWith(".svg"))
			return "image/svg+xml";
		if(fileRequested.endsWith(".swf"))
			return "application/x-shockwave-flash";
		if(fileRequested.endsWith(".tar"))
			return "application/x-tar";
		if(fileRequested.endsWith(".tif") || fileRequested.endsWith(".tiff"))
			return "image/tiff";
		if(fileRequested.endsWith(".ts"))
			return "video/mp2t";
		if(fileRequested.endsWith(".ttf"))
			return "font/ttf";
		if(fileRequested.endsWith(".txt"))
			return "text/plain";
		if(fileRequested.endsWith(".vsd"))
			return "application/vnd.visio";
		if(fileRequested.endsWith(".wav"))
			return "audio/wav";
		if(fileRequested.endsWith(".weba"))
			return "audio/webm";
		if(fileRequested.endsWith(".webm"))
			return "video/webm";
		if(fileRequested.endsWith(".webp"))
			return "image/webp";
		if(fileRequested.endsWith(".woff"))
			return "font/woff";
		if(fileRequested.endsWith(".woff2"))
			return "font/woff2";
		if(fileRequested.endsWith(".xhtml"))
			return "application/xhtml+xml";
		if(fileRequested.endsWith(".xls"))
			return "application/vnd.ms-excel";
		if(fileRequested.endsWith(".xlsx"))
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		if(fileRequested.endsWith(".xml"))
			return "application/xml";
		if(fileRequested.endsWith(".xul"))
			return "application/vnd.mozilla.xul+xml";
		if(fileRequested.endsWith(".zip"))
			return "application/zip";
		if(fileRequested.endsWith(".3gp"))
			return "video/3gpp";
		if(fileRequested.endsWith(".3g2"))
			return "video/3gpp2";
		if(fileRequested.endsWith(".7z"))
			return "application/x-7z-compressed";
		if(fileRequested.endsWith(".css"))
			return "text/css";
		if(fileRequested.endsWith(".jpeg") ||  fileRequested.endsWith(".jpg"))
			return "image/jpeg";
		if(fileRequested.endsWith(".js"))
			return "text/javascript";
		else
			return "text/plain";
	}
	
	public void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(Config.ROOT_DIRECTORY, Config.FILE_NOT_FOUND);
		int fileLength = (int) file.length();
		String content = "text/html";
		byte[] fileData = readFileData(file, fileLength);
		
		out.println("HTTP/1.1 404 File Not Found");
		out.println("Server: Java HTTP Server from SSaurel : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); // blank line between headers and content, very important !
		out.flush(); // flush character output stream buffer
		
		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
		
		if (Config.verbose) {
			System.out.println("File " + fileRequested + " not found");
		}
	}
	
	public static boolean isPortAvailable(int port) {
//	    if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
//	        throw new IllegalArgumentException("Invalid start port: " + port);
//	    }

	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }

	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	                /* should not be thrown */
	            }
	        }
	    }

	    return false;
	}
	
	public String routingDefaultTypeFile(String fileRequested) {
		String strReturn = null;
		try {
			String fileFiltered =  getContentType(fileRequested);
			if(fileFiltered.equals("text/plain")) {
				//Routing file
				File file = null;
				String [] typeFiles = {"index.php","home.php","default.php","index.html","index.htm","home.html","home.htm","default.html","default.htm"}; 
				for (int i = 0; i < typeFiles.length; i++) {
					if(!fileRequested.endsWith(".html")) {
						file = new File(Config.ROOT_DIRECTORY,fileRequested+"/"+typeFiles[i]);
						if(file.isFile()) {
							if(String.valueOf(fileRequested.charAt(fileRequested.length() -1)).equals("/")){
								strReturn = fileRequested+file.getName();
								break;
							}
							else {
								strReturn = fileRequested+"/"+file.getName();
								break;	
							}
						}
					}
					
				}
			}
			return strReturn;	
		}catch (Exception e) {
			strReturn = null;
		}
		return strReturn;
	}
}
