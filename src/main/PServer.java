package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import exception.PortIsNotAvailableException;
import views.Launcher;

public class PServer {
	
	protected Config config;
	protected ServerSocket serverConnect;
	protected Socket connect;
	public ThreadServer threadPServer;
	public boolean state;
	
	public PServer() {}
	
	public void runPServer() throws IOException, PortIsNotAvailableException {
		Utils utils = new Utils();
		if(utils.isPortAvailable(config.PORT)) {
			System.out.println("Port 80 co san ne");
			serverConnect = new ServerSocket(config.PORT);
			System.out.println("Server started.\nListening for connections on port : " + config.PORT + " ...\n");
			setState(true);
			System.out.println("PServer::isStarted="+getState());
			// we listen until user halts server execution
			this.state = true;
			while(this.state) {
				System.out.println("PSERVER::runPServer()->is still running");
				connect = serverConnect.accept();
				if (config.verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				// create dedicated thread to manage the client connection
				threadPServer = new ThreadServer(connect);
				threadPServer.start();
			}
		}
		else {
			throw new PortIsNotAvailableException("Port "+config.PORT+" is not available."); 
		}
		
	}
	
	public void stopPServer() {
		threadPServer = new ThreadServer();
		threadPServer.stop();
		threadPServer.interrupt();
		this.state = false;
		try {
			serverConnect.close();
		} catch (IOException e) {
			
		}
		System.out.println(this.state);
	}
	
	
	public boolean getState() {
		return this.state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
}
