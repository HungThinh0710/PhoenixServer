package views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import exception.PortIsNotAvailableException;
import main.Config;
import main.MySQL;
import main.PServer;
import main.ThreadServer;
import main.Utils;
import java.awt.Color;
import java.awt.Desktop;

public class Launcher<V> extends JFrame {

	private JPanel contentPane;
	public static JButton btnStartPServer;
	private JButton btnStartMySQL;
	
	private JTextArea txtConsole;
	private JLabel lblPortOfPServer;
	private JLabel lblPidOfPServer;
	private JLabel lblPserver;
	private JLabel lblPidOfMySql;
	private JLabel lblPortOfMySQL;
	private JLabel lblMySql;
	/**
	 * Init Pserver variable.
	 */
	private Config config;
	private static PServer pServer;
	private Socket connect;
	private ServerSocket serverConnect;
	private ThreadServer threadServer;
	
	private ThreadServer threadState;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pServer = new PServer();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Launcher frame = new Launcher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Launcher() {
		setTitle("Phoenix Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 564);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Modules", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 53, 742, 153);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panelModule = new JPanel();
		panelModule.setBounds(10, 10, 105, 132);
		panel.add(panelModule);
		panelModule.setLayout(null);
		
		JLabel lblModule = new JLabel("Module");
		lblModule.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblModule.setBounds(10, 10, 85, 18);
		panelModule.add(lblModule);

		lblPserver = new JLabel("PServer");
		lblPserver.setForeground(new Color(0, 0, 0));
		lblPserver.setBackground(new Color(51, 51, 51));
		lblPserver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPserver.setHorizontalAlignment(SwingConstants.CENTER);
		lblPserver.setBounds(10, 45, 85, 13);
		panelModule.add(lblPserver);
		
		lblMySql = new JLabel("MySQL");
		lblMySql.setHorizontalAlignment(SwingConstants.CENTER);
		lblMySql.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMySql.setBounds(10, 91, 85, 13);
		panelModule.add(lblMySql);
		
		JPanel panel_PID = new JPanel();
		panel_PID.setLayout(null);
		panel_PID.setBounds(138, 10, 105, 132);
		panel.add(panel_PID);
		
		JLabel lblPIDs = new JLabel("PID(s)");
		lblPIDs.setHorizontalAlignment(SwingConstants.CENTER);
		lblPIDs.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPIDs.setBounds(10, 10, 85, 18);
		panel_PID.add(lblPIDs);
		
		lblPidOfPServer = new JLabel("");
		lblPidOfPServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPidOfPServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPidOfPServer.setBounds(10, 45, 85, 13);
		panel_PID.add(lblPidOfPServer);
		
		lblPidOfMySql = new JLabel("");
		lblPidOfMySql.setHorizontalAlignment(SwingConstants.CENTER);
		lblPidOfMySql.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPidOfMySql.setBounds(10, 91, 85, 13);
		panel_PID.add(lblPidOfMySql);
		
		JLabel lblPidOfTomcat = new JLabel("");
		lblPidOfTomcat.setBackground(Color.GREEN);
		lblPidOfTomcat.setHorizontalAlignment(SwingConstants.CENTER);
		lblPidOfTomcat.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPidOfTomcat.setBounds(10, 136, 85, 13);
		panel_PID.add(lblPidOfTomcat);
		
		JPanel panelPort = new JPanel();
		panelPort.setLayout(null);
		panelPort.setBounds(265, 10, 105, 132);
		panel.add(panelPort);
		
		JLabel lblPort = new JLabel("Port(s)");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPort.setBounds(10, 10, 85, 18);
		panelPort.add(lblPort);
		
		lblPortOfPServer = new JLabel("");
		lblPortOfPServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortOfPServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPortOfPServer.setBounds(10, 45, 85, 13);
		panelPort.add(lblPortOfPServer);
		
		lblPortOfMySQL = new JLabel("");
		lblPortOfMySQL.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortOfMySQL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPortOfMySQL.setBounds(10, 91, 85, 13);
		panelPort.add(lblPortOfMySQL);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(393, 10, 337, 132);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnStartPServer = new JButton("Start");
		btnStartPServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				startPServer();
				
				startPServer2();
			}
			
		});
		btnStartPServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartPServer.setBounds(10, 36, 73, 21);
		panel_1.add(btnStartPServer);
		
		JButton btnAcessOfPServer = new JButton("Access");
		btnAcessOfPServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
			    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			        try {
			        	URI uri = new URI("http://localhost");
			            desktop.browse(uri);
			        } catch (Exception e2) {
			            e2.printStackTrace();
			        }
			    }
			}
		});
		btnAcessOfPServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAcessOfPServer.setBounds(93, 36, 73, 21);
		panel_1.add(btnAcessOfPServer);
		
		JButton btnLogsOfPserver = new JButton("Logs");
		btnLogsOfPserver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogsOfPserver.setBounds(259, 36, 69, 21);
		panel_1.add(btnLogsOfPserver);
		
		JButton btnConfigOfPServer = new JButton("Config");
		btnConfigOfPServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigView configViewPage = new ConfigView();
				configViewPage.frmConfigPserver.setVisible(true);
			}
		});
		btnConfigOfPServer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfigOfPServer.setBounds(176, 36, 73, 21);
		panel_1.add(btnConfigOfPServer);
		
		btnStartMySQL = new JButton("Start");
		btnStartMySQL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					startMySQLHandle();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStartMySQL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartMySQL.setBounds(10, 85, 73, 21);
		panel_1.add(btnStartMySQL);
		
		JButton btnAccessMySql = new JButton("Access");
		btnAccessMySql.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAccessMySql.setBounds(93, 85, 73, 21);
		panel_1.add(btnAccessMySql);
		
		JButton btnConfigMySql = new JButton("Config");
		btnConfigMySql.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConfigMySql.setBounds(176, 85, 73, 21);
		panel_1.add(btnConfigMySql);
		
		JButton btnLogsMySQL = new JButton("Logs");
		btnLogsMySQL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogsMySQL.setBounds(259, 85, 69, 21);
		panel_1.add(btnLogsMySQL);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setHorizontalAlignment(SwingConstants.CENTER);
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblActions.setBounds(10, 8, 85, 18);
		panel_1.add(lblActions);
		
		JLabel lblPhoenixServerTitle = new JLabel("Phoenix Server v1.1.0");
		lblPhoenixServerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoenixServerTitle.setFont(new Font("Consolas", Font.PLAIN, 26));
		lblPhoenixServerTitle.setBounds(82, 10, 528, 42);
		contentPane.add(lblPhoenixServerTitle);
		
		txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		txtConsole.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtConsole.setBounds(20, 216, 785, 310);
		contentPane.add(txtConsole);
		initResource();
	}
	private void initResource() {
		Config.createINIConfigrationFile();
	}
	
	
	private void startPServer2() {
		Thread startThread = new Thread() {
			@Override
			public void run() {
				try {
					pServer.runPServer();
				} catch (IOException e) {
					System.err.println("Throws ra day ne: "+ e);			        
				}
				catch (PortIsNotAvailableException error) {
					txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer][ERROR] "+error.getMessage()+" \n");
					error.printStackTrace();
					btnStartPServer.setEnabled(true);
					btnStartPServer.setText("Start");
				}
			}
		};
		
		if(btnStartPServer.getText().toString().equals("Start") && !pServer.getState()) {
			startThread.start();
//			changeStatusThread.start();
			btnStartPServer.setEnabled(false);
			txtConsole.append("["+ getCurrentTime() +"] "+"[PServer]"+"Attempting to start PServer app...\n");
			lblPserver.setForeground(new Color(255, 215, 0));
			if(pServer.getState() && btnStartPServer.getText().toString().equals("Start")) {
				btnStartPServer.setEnabled(true);
				btnStartPServer.setText("Stop");
				//Set state to views
				txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer]"+ "PServer is running. \n");
				lblPortOfPServer.setText(String.valueOf(Config.PORT));
				lblPidOfPServer.setText(String.valueOf(ProcessHandle.current().pid()));
				lblPserver.setForeground(new Color(0, 128, 0));
			}
			
			System.out.println("START_startThread::isAlive=" + startThread.isAlive());
			return;
		}
		if(btnStartPServer.getText().toString().equals("Stop") && pServer.getState()) {
			pServer.stopPServer();
			startThread.stop();
			btnStartPServer.setEnabled(false);
			txtConsole.append("["+ getCurrentTime() +"] "+"[PServer]"+"Attempting to stop PServer app...\n");
			lblPserver.setForeground(new Color(255, 215, 0));
			if(!pServer.getState() && btnStartPServer.getText().toString().equals("Stop")) {
				btnStartPServer.setEnabled(true);
				btnStartPServer.setText("Start");
				//Set state to views
				txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer]"+ "PServer has been stopped. \n");
				lblPortOfPServer.setText("");
				lblPidOfPServer.setText("");
				btnStartPServer.setEnabled(true);
				lblPserver.setForeground(new Color(0, 0, 0));
			}
			System.out.println("START_startThread::isAlive=" + startThread.isAlive());
		}
	}
	
	private void startPServer() {
		if(btnStartPServer.getText().toString().equals("Stop") && pServer.getState()) {
			btnStartPServer.setEnabled(false);
			new Thread() {
				@Override
				public void run() {
					stopPSever();
					txtConsole.append("["+ getCurrentTime() +"] "+"[PServer]"+"Attempting to stop PServer app...\n");
					lblPserver.setForeground(new Color(255, 215, 0));
					while(!pServer.getState()) {
						if(!pServer.getState() && btnStartPServer.getText().toString().equals("Stop")) {
							btnStartPServer.setEnabled(true);
							btnStartPServer.setText("Start");
							//Set state to views
							txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer]"+ "PServer has been stopped. \n");
							lblPortOfPServer.setText("");
							lblPidOfPServer.setText("");
							btnStartPServer.setEnabled(true);
							lblPserver.setForeground(new Color(0, 0, 0));
						}
					}
				}
			}.start();
		}
		else {
			Thread t1 = new Thread() {
				public void run() {
					try {
						pServer.runPServer();
					} catch (IOException e) {
						System.err.println("Throws ra day ne: "+ e);			        
					}
					catch (PortIsNotAvailableException error) {
						txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer][ERROR] "+error.getMessage()+" \n");
						error.printStackTrace();
						btnStartPServer.setEnabled(true);
						btnStartPServer.setText("Start");
					}
				}
			};
			t1.start();

			Thread trackingPServerState = new Thread() {
				@Override
				public void run() {
					btnStartPServer.setEnabled(false);
					txtConsole.append("["+ getCurrentTime() +"] "+"[PServer]"+"Attempting to start PServer app...\n");
					lblPserver.setForeground(new Color(255, 215, 0));
					while(pServer.getState()) {
						if(pServer.getState() && btnStartPServer.getText().toString().equals("Start")) {
							btnStartPServer.setEnabled(true);
							btnStartPServer.setText("Stop");
							//Set state to views
							txtConsole.append("["+ getCurrentTime() +"] "+ "[PServer]"+ "PServer is running. \n");
							lblPortOfPServer.setText(String.valueOf(Config.PORT));
							lblPidOfPServer.setText(String.valueOf(ProcessHandle.current().pid()));
							lblPserver.setForeground(new Color(0, 128, 0));
						}
					}
				}
			};
			trackingPServerState.start();
		}
	}
	
	private void stopPSever() {
		pServer.stopPServer();
	}
	
	
	private void startMySQLHandle() throws InterruptedException {
		MySQL mySql = new MySQL();
		Thread startMySQL = new Thread() {
			@Override
			public void run() {
				try {
					txtConsole.append("["+ getCurrentTime() +"] "+"[MySql]"+"Attempting to start MySQL app...\n");
					mySql.startMYSQL();
				}catch (PortIsNotAvailableException error) {
					btnStartMySQL.setText("Start");
					lblPidOfMySql.setText("");
					lblPortOfMySQL.setText("");
					btnStartMySQL.setEnabled(true);
					txtConsole.append("["+ getCurrentTime() +"] "+ "[MySql][ERROR] "+error.getMessage()+" \n");
					error.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread stopThread = new Thread() {
			@Override
			public void run() {
				try {
					mySql.stopMYSQL();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		if(btnStartMySQL.getText().equals("Start")) {
			startMySQL.start();
			
			//Change status of views
			
			int portMySql = Config.getPortMySQLINI();
//			int pidMySql = mySql.getMySQLPid(portMySql);
			
			lblPortOfMySQL.setText(String.valueOf(portMySql));
//			Thread.sleep(3000);
//			lblPidOfMySql.setText(String.valueOf(mySql.getMySQLPid(Config.getPortMySQLINI())));
			btnStartMySQL.setText("Stop");
			lblMySql.setForeground(new Color(255, 215, 0));
			lblMySql.setForeground(new Color(0, 128, 0));
			txtConsole.append("["+ getCurrentTime() +"] "+ "[MySql]"+ "MySql is running. \n");
			return;
		}
		if(btnStartMySQL.getText().equals("Stop")) {
			
			txtConsole.append("["+ getCurrentTime() +"] "+"[MySql]"+"Attempting to stop MySql app...\n");
//			TimeUnit.SECONDS.sleep(3);
//			Thread.sleep(3000);
//			mySql.stopMYSQL();
			stopThread.start();
			//Change status of views

			txtConsole.append("["+ getCurrentTime() +"] "+ "[MySql]"+ "MySql has been stopped. \n");
			lblMySql.setForeground(new Color(0, 0, 0));
			lblPortOfMySQL.setText("");
			lblPidOfMySql.setText("");
			btnStartMySQL.setText("Start");
			return;
		}
	}
	
	private String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");  
	    Date date = new Date();  
	    return String.valueOf(formatter.format(date));
	}
	private void delay(Long ms){
		Long dietime = System.currentTimeMillis()+ms;
		while(System.currentTimeMillis()<dietime){
			//do nothing   
		}
	}
}
