import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class ClientFrame {

	private JFrame frame;
	private JTextField textField_Ip;
	private JTextField textField_Port;
	private JTextPane textPane;
	private Client client;
	private JTextField textField_Name;
	private JTextArea textArea;
	private JLabel lblIfConnected;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.getContentPane().setBounds(new Rectangle(0, 0, 600, 400));
		frame.setBounds(100, 100, 600, 400);
		frame.setMinimumSize(new Dimension(600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {30, 105, 105, 30};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setForeground(new Color(192, 192, 192));
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		frame.getContentPane().add(toolBar, gbc_toolBar);
		
		JLabel lblIpAddress = new JLabel("Ip Address");
		toolBar.add(lblIpAddress);
		
		textField_Ip = new JTextField();
		textField_Ip.setDropMode(DropMode.INSERT);
		toolBar.add(textField_Ip);
		textField_Ip.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		toolBar.add(lblPort);
		
		textField_Port = new JTextField();
		toolBar.add(textField_Port);
		textField_Port.setColumns(6);
		
		JLabel lblName = new JLabel("Name");
		toolBar.add(lblName);
		
		textField_Name = new JTextField();
		toolBar.add(textField_Name);
		textField_Name.setColumns(10);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(new Color(204, 255, 255));
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.weighty = 1.0;
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 0;
		gbc_textPane.gridy = 1;
		frame.getContentPane().add(textPane, gbc_textPane);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setDropMode(DropMode.INSERT);
		textArea.setBackground(new Color(204, 255, 204));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 2;
		frame.getContentPane().add(textArea, gbc_textArea);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.fill = GridBagConstraints.BOTH;
		gbc_toolBar_1.gridx = 0;
		gbc_toolBar_1.gridy = 3;
		frame.getContentPane().add(toolBar_1, gbc_toolBar_1);
		
		JButton btnConnectToServer = new JButton("Connect to Server");
		toolBar_1.add(btnConnectToServer);
		btnConnectToServer.setIcon(new ImageIcon(ClientFrame.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setIcon(new ImageIcon(ClientFrame.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		toolBar_1.add(btnDisconnect);
		
		JButton btnSend = new JButton("Send");
		btnSend.setIcon(new ImageIcon(ClientFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		toolBar_1.add(btnSend);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setIcon(new ImageIcon(ClientFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		lblStatus.setBackground(UIManager.getColor("inactiveCaption"));
		toolBar_1.add(lblStatus);
		
		lblIfConnected = new JLabel("Disconnected");
		toolBar_1.add(lblIfConnected);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		client = new Client(textField_Ip, textField_Port, textPane, textArea,textField_Name,lblIfConnected);
		
		btnConnectToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_Ip.getText().length() == 0 || textField_Port.getText().length() ==  0){
					textPane.setText(textPane.getText() +"\nERROR: HAVENT INPUT THE INETADDRESS!\n");
					return;
				}
				if(client == null)client = new Client(textField_Ip, textField_Port, textPane, textArea,textField_Name,lblIfConnected);
				client.connect();
				textPane.setText("");
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(client.ifConnected())client.send();
				else{
					textPane.setText(textPane.getText() + "\nERROR: HAVENT CONNECTED \n");
				}
			}
		});
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.close();
				textPane.setText("");
			}
		});
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				client.close();
			}
		});
	}

}
