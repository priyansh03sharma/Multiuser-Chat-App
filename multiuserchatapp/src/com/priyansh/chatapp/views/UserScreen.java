package com.priyansh.chatapp.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.priyansh.chatapp.dao.UserDAO;
import com.priyansh.chatapp.dto.UserDTO;
import com.priyansh.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {

	private JFrame frame;
	private JTextField useridtxt;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UserScreen window = new UserScreen();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserScreen window = new UserScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message = "";
			if(userDAO.isLogin(userDTO)) {
				//JOptionPane.showMessageDialog(this,"Welcome "+userid);
				message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this,message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message = "Invalid Userid or Password";
				JOptionPane.showMessageDialog(this,message);
			}
			//JOptionPane.showMessageDialog(this,message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void register() {
		String userid = useridtxt.getText();
		char[] password = passwordField.getPassword();
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register Successfully");
			//System.out.println("Record Added...");
		}
		else {
			JOptionPane.showMessageDialog(this, "Register Fail");
			//System.out.println("Record not added...");
		}
		}
//		catch(ClassNotFoundException |SQLException ex) {
//			System.out.println("DB Issue...");
//			ex.printStackTrace();
//		}
		catch(Exception ex) {
			System.out.println("Some Generic Exception Raised...");
			ex.printStackTrace();//where is the exception
		}
		System.out.println("userid "+userid+" Password "+password+" "+password.toString()); //ClassName@HashCode(Hexa)
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 25));
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(308, 44, 193, 61);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(389, 160, 231, 42);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1.setBounds(231, 176, 128, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel_1_1.setBounds(198, 276, 161, 26);
		getContentPane().add(lblNewLabel_1_1);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		loginbt.setBounds(243, 392, 116, 42);
		getContentPane().add(loginbt);
		
		JButton signupbt = new JButton("Signup");
		signupbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		signupbt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		signupbt.setBounds(451, 392, 116, 42);
		getContentPane().add(signupbt);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(389, 260, 231, 42);
		getContentPane().add(passwordField);
		setSize( 833, 542);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
