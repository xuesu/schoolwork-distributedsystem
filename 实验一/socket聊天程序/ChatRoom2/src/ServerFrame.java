import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.UIManager;

public class ServerFrame {

	private JFrame frmChatroomtcpserver;
	private JTextPane textPane;
	private JTextArea textArea;
	private JButton btnSend;
	private Server server;
	private JList<String> list;
	private DefaultListModel<String> dlm;
	private JButton btnDisconnect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame window = new ServerFrame();
					window.frmChatroomtcpserver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatroomtcpserver = new JFrame();
		frmChatroomtcpserver.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ServerFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		frmChatroomtcpserver.setTitle("ChatRoom(TCPServer)");
		frmChatroomtcpserver.setBounds(100, 100, 600, 400);
		frmChatroomtcpserver.setMinimumSize(new Dimension(600, 400));
		frmChatroomtcpserver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 450 };
		gridBagLayout.rowHeights = new int[] { 180, 180, 30, 0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0, 1.0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		frmChatroomtcpserver.getContentPane().setLayout(gridBagLayout);

		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(new Color(204, 255, 255));
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.insets = new Insets(0, 0, 5, 0);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 1;
		gbc_textPane.gridy = 0;
		frmChatroomtcpserver.getContentPane().add(textPane, gbc_textPane);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(new Color(204, 255, 204));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		frmChatroomtcpserver.getContentPane().add(textArea, gbc_textArea);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBackground(SystemColor.scrollbar);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridx = 1;
		gbc_toolBar.gridy = 2;
		frmChatroomtcpserver.getContentPane().add(toolBar, gbc_toolBar);

		btnSend = new JButton("Send");
		btnSend.setBackground(SystemColor.activeCaption);
		btnSend.setIcon(new ImageIcon(ServerFrame.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		btnSend.setFont(new Font("ËÎÌו", Font.BOLD, 14));
		toolBar.add(btnSend);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBackground(UIManager.getColor("activeCaption"));
		btnDisconnect.setIcon(new ImageIcon(ServerFrame.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		toolBar.add(btnDisconnect);

		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 4;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		frmChatroomtcpserver.getContentPane().add(list, gbc_list);
		dlm = new DefaultListModel<String>();

		server = new Server(list, textPane, textArea,dlm);
		server.run();

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				String ipaddr = list.getSelectedValue();
				textPane.setText(server.getStrStore().get(ipaddr));
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.send();
			}
		});
		frmChatroomtcpserver.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				server.close();
			}
		});
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					textPane.setText(textPane.getText() + "ERROR:HAVENT CHOOSE CLIENT!");
				}
				else{
					String ipaddr = list.getSelectedValue();
					server.closeSoc(ipaddr);
				}
			}
		});

	}

}
