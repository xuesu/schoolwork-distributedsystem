package multicast;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyFrame {

	private JFrame frmMulticastchatroom;
	private JTextArea textPane;
	private JButton btnSend;
	private Member mem;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_Input;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame window = new MyFrame();
					window.frmMulticastchatroom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyFrame() {
		mem = new Member();
		initialize();
		Thread thread = new Thread (new ReceivedThread(mem.getMcs(),textPane));
		
		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 2;
		frmMulticastchatroom.getContentPane().add(toolBar, gbc_toolBar);
		btnSend = new JButton("Send");
		btnSend.setSelectedIcon(new ImageIcon(MyFrame.class.getResource("/javax/swing/plaf/basic/icons/image-delayed.png")));
		btnSend.setIcon(new ImageIcon(MyFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		toolBar.add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem.sendData(new Date() + " From:" + mem.getMcs().getLocalSocketAddress() +"\n"+ textArea.getText() + "\n");
			}
		});
		thread.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMulticastchatroom = new JFrame();
		frmMulticastchatroom.setIconImage(Toolkit.getDefaultToolkit().getImage(MyFrame.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		frmMulticastchatroom.setTitle("MulticastChatRoom");
		frmMulticastchatroom.setBounds(100, 100, 600, 400);
		frmMulticastchatroom.setMinimumSize(new Dimension(600,400));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{584, 0};
		gridBagLayout.rowHeights = new int[] {180, 150, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmMulticastchatroom.getContentPane().setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frmMulticastchatroom.getContentPane().add(scrollPane, gbc_scrollPane);
		textPane = new JTextArea();
		textPane.setText("you have entered\n");
		textPane.setTabSize(4);
		textPane.setLineWrap(true);
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(10);
		textArea.setBackground(new Color(204, 255, 204));
		scrollPane_Input = new JScrollPane(textArea);
		GridBagConstraints gbc_scrollPane_Input = new GridBagConstraints();
		gbc_scrollPane_Input.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Input.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_Input.gridx = 0;
		gbc_scrollPane_Input.gridy = 1;
		frmMulticastchatroom.getContentPane().add(scrollPane_Input, gbc_scrollPane_Input);
		

		frmMulticastchatroom.addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);mem.close();
			}
		});
	}
}
