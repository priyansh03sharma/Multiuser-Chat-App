package com.priyansh.chatapp.views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.priyansh.chatapp.network.Client;
import com.priyansh.chatapp.utils.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					try {
						ClientChatScreen frame = new ClientChatScreen();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
	}
	
	private void sendIt() {
		String message = textField.getText();
		try {
		client.sendMessage(UserInfo.USER_NAME+" - "+message);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		setResizable(false);
		textArea = new JTextArea();
		client = new Client(textArea);
		setTitle("Chit-Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 765, 368);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(10, 31, 724, 335);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 388, 641, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendIt = new JButton("Send");
		sendIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendIt.setBackground(new Color(240, 240, 240));
		sendIt.setFont(new Font("Tahoma", Font.BOLD, 17));
		sendIt.setBounds(670, 388, 105, 49);
		contentPane.add(sendIt);
		setVisible(true);
		
	}
}
