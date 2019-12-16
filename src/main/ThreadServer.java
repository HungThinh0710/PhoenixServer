package main;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class ThreadServer extends Thread{
	private Config config;
	private Socket connect;
	private Utils utils;
	private boolean openDefault = false;
	public ThreadServer() {
	}
	
	public ThreadServer(Socket connect) {
//		this.serverRun = myServer;
		this.connect = connect;
	}	
	

	@Override
	public void run() {
		System.out.println("ThreadServer::run()->is still running");
		// TODO Auto-generated method stub
//			super.run();
		// we manage our particular client connection
		BufferedReader in = null; PrintWriter out = null; BufferedOutputStream dataOut = null;
		DataInputStream in2 = null;
		String fileRequested = null;
		try {
			//Initial Utils 
			utils = new Utils();
						
			// we read characters from the client via input stream on the socket
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			// we get character output stream to client (for headers)
			out = new PrintWriter(connect.getOutputStream());
			// get binary output stream to client (for requested data)
			dataOut = new BufferedOutputStream(connect.getOutputStream());
			
			// get first line of the request from the client
			String input = in.readLine();
			
			System.out.println(input);
			// we parse the request with a string tokenizer
			StringTokenizer parse = new StringTokenizer(input);
			String method = parse.nextToken().toUpperCase(); // we get the HTTP method of the client
			// we get file requested
			fileRequested = parse.nextToken().toLowerCase();
			System.out.println("requested_now_fist " + fileRequested);

			// REMOTING default typefile
			String fileRequestedStock = fileRequested;//FileRequestTestStock;
			String fileRouted = utils.routingDefaultTypeFile(fileRequested);
			if(fileRouted!=null) 
				fileRequested = fileRouted;
			System.out.println("requested_now_second " + fileRequested);
			System.out.println("file return " + fileRouted);
			
			
			//TEST_?_HANDLE
			String fileFilteredResource = utils.filterResource(fileRequested);
			if(fileFilteredResource != null) {
				fileRequested = fileFilteredResource;
				System.out.println("After_refil? " + fileFilteredResource);
			}
			
			
//			String fileRouted = utils.routingDefaultTypeFile(fileRequested);
//			if(fileRouted != null)
//				fileRequested = fileRouted;
			
			//
			// we support only GET and HEAD methods, we check
			if (!method.equals("GET")  &&  !method.equals("HEAD")) {
				if (config.verbose) {
					System.out.println("501 Not Implemented : " + method + " method.");
				}
				
				// we return the not supported file to the client
				File file = new File(config.ROOT_DIRECTORY, config.METHOD_IS_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				String contentMimeType = "text/html";
				//read content to return to client
				byte[] fileData = utils.readFileData(file, fileLength);
				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Server: PServer from Hung Thinh - SICT: v1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + contentMimeType);
				out.println("Content-length: " + fileLength);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				// file
				dataOut.write(fileData, 0, fileLength);
				dataOut.flush();
				
			} else {
				// GET or HEAD method
				if (fileRequested.endsWith("/")) {
					System.out.println("Khongtimthayyyyyyyyyyyyy");
					fileRequested = config.DEFAULT_FILE;
					openDefault = true;
				}
				
//				File file = new File(config.ROOT_DIRECTORY, fileRequested);
				File file;
				if(openDefault) {
					file = new File(config.RESOURCE_PATH, fileRequested);
					openDefault=false;
				}
				else {
					file = new File(config.getPathRootPServerINI(), fileRequested);
				}
//				file = new File(config.getPathRootPServerINI(), fileRequested);
				System.out.println("SSS_root:"+file.getAbsolutePath());
				int fileLength = (int) file.length();
				
				
				String content = utils.getContentType(fileRequested);

				
				if (method.equals("GET")) { // GET method so we return content
//					if(content.equals("text/html")) {
					if(fileRequested.endsWith(".php") || fileRequested.contains(".php")) {
						 try {
							    ExecutePHPFile php = new ExecutePHPFile();
							    StringBuilder getData = new StringBuilder();
							    getData = php.executePHP(fileRequested);
							    
							    int phpLength = getData.toString().length();
							    byte [] phpData = new byte [phpLength];
							    phpData = getData.toString().getBytes();

						        out.println("HTTP/1.1 200 OK");
								out.println("Server: PServer from Hung Thinh - SICT: v1.0");
								out.println("Date: " + new Date());
								out.println("Content-type: " + content);
								out.println("Content-length: " + phpLength);
								out.println(); // blank line between headers and content, very important !
								out.flush(); // flush character output stream buffer
								
								dataOut.write(phpData,0,phpLength);
								dataOut.flush();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								System.out.println(e.getMessage());
							}
					}
					else {
						
						byte[] fileData = utils.readFileData(file, fileLength);
						// send HTTP Headers
						out.println("HTTP/1.1 200 OK");
						out.println("Server: PServer from Hung Thinh - SICT: v1.0");
						out.println("Date: " + new Date());
						out.println("Content-type: " + content);
						out.println("Content-length: " + fileLength);
						out.println(); // blank line between headers and content, very important !
						out.flush(); // flush character output stream buffer
						
						dataOut.write(fileData, 0, fileLength);
						dataOut.flush();
					}
				}
				
				if (config.verbose) {
					System.out.println("File " + fileRequested + " of type " + content + " returned");
				}
				
			}
			
		} catch (FileNotFoundException fnfe) {
			try {
				utils.fileNotFound(out, dataOut, fileRequested);
			} catch (IOException ioe) {
				System.err.println("Error with file not found exception : " + ioe.getMessage());
			}
			
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				dataOut.close();
				connect.close(); // we close socket connection
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			} 
			
			if (config.verbose) {
				System.out.println("Connection closed.\n");
			}
		}
	}
	
	
}
