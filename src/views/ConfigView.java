package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import main.Config;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ConfigView {

	public JFrame frmConfigPserver;
	private JTextField txtPServerPort;
	private JTextField txtPathRoot;
	private Config config;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ConfigView window = new ConfigView();
					window.frmConfigPserver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfigView() {
		config = new Config();
		initialize();
		getConfigData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfigPserver = new JFrame();
		frmConfigPserver.setTitle("Config PServer");
		frmConfigPserver.setBounds(100, 100, 314, 308);
		frmConfigPserver.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConfigPserver.getContentPane().setLayout(null);
		frmConfigPserver.setResizable(false);
		frmConfigPserver.setLocationRelativeTo(null);
		
		JPanel panelFrame = new JPanel();
		panelFrame.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Configuration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelFrame.setBounds(10, 10, 280, 251);
		frmConfigPserver.getContentPane().add(panelFrame);
		panelFrame.setLayout(null);
		
		JPanel panelPort = new JPanel();
		panelPort.setBorder(new TitledBorder(null, "Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPort.setBounds(10, 23, 260, 47);
		panelFrame.add(panelPort);
		panelPort.setLayout(null);
		
		txtPServerPort = new JTextField();
		txtPServerPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPServerPort.setBounds(10, 15, 161, 19);
		panelPort.add(txtPServerPort);
		txtPServerPort.setColumns(10);
		
		JButton btnSavePServerPort = new JButton("Save");
		btnSavePServerPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int port = Integer.parseInt(txtPServerPort.getText());
				config.changePortPServerINI(port);
				JOptionPane.showMessageDialog(null, "Change Port Successfully");
				getConfigData();
			}
		});
		btnSavePServerPort.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSavePServerPort.setBounds(175, 14, 75, 21);
		panelPort.add(btnSavePServerPort);
		
		JPanel panelRoot = new JPanel();
		panelRoot.setLayout(null);
		panelRoot.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Path root", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelRoot.setBounds(10, 95, 260, 47);
		panelFrame.add(panelRoot);
		
		txtPathRoot = new JTextField();
		txtPathRoot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPathRoot.setColumns(10);
		txtPathRoot.setBounds(10, 15, 161, 19);
		panelRoot.add(txtPathRoot);
		
		JButton btnChoosePathRoot = new JButton("Choose");
		btnChoosePathRoot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.setCurrentDirectory(new java.io.File("."));
				fileChoose.setDialogTitle("Select your folder");
				fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int c = fileChoose.showSaveDialog(null);
				if(c == fileChoose.APPROVE_OPTION) {
					String path = fileChoose.getSelectedFile().getAbsolutePath();
					config.changeRootPServerINI(path);
					JOptionPane.showMessageDialog(null, "Success");
					getConfigData();
				}
			}
		});
		btnChoosePathRoot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnChoosePathRoot.setBounds(175, 14, 75, 21);
		panelRoot.add(btnChoosePathRoot);
		
		JPanel panelMore = new JPanel();
		panelMore.setLayout(null);
		panelMore.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "More", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMore.setBounds(10, 168, 260, 47);
		panelFrame.add(panelMore);
		
		JButton btnOpenConfigFile = new JButton("Open cfg_p.ini");
		btnOpenConfigFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if(!Desktop.isDesktopSupported()){
		            System.out.println("Desktop is not supported");
		            return;
		        }

				try {
			        Desktop desktop = Desktop.getDesktop();
			        File file = new File(config.INI_FOLDER_PATH+ config.CONFIG_INI_PSERVER_FILE_NAME);
			        if(file.exists()) desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
			}
		});
		btnOpenConfigFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOpenConfigFile.setBounds(134, 15, 116, 21);
		panelMore.add(btnOpenConfigFile);
		
		JButton btnResetDefault = new JButton("Reset default");
		btnResetDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.restoreDefaultCfgData();
				JOptionPane.showMessageDialog(null, "Restore successfully");
				getConfigData();
			}
		});
		btnResetDefault.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnResetDefault.setBounds(10, 15, 114, 21);
		panelMore.add(btnResetDefault);
		
		JLabel lblDevelopByHung = new JLabel("Develop by Hung Thinh - SICT");
		lblDevelopByHung.setForeground(Color.GRAY);
		lblDevelopByHung.setBounds(65, 232, 139, 13);
		panelFrame.add(lblDevelopByHung);
	}
	
	private void getConfigData() {
		int port = config.getPortPServerINI();
		String pathRootDir = config.getPathRootPServerINI();
		
		txtPServerPort.setText(String.valueOf(port));
		txtPathRoot.setText(pathRootDir);
	}
}
