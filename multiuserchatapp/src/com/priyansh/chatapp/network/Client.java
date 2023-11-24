package com.priyansh.chatapp.network;

import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JTextArea;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientThread thread;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT = Integer.parseInt("9999");
		String SERVER_IP = "192.168.1.18";
		this.socket = new Socket(SERVER_IP,PORT);
		this.out = socket.getOutputStream();
		this.in = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
//		System.out.println("Client Comes");
//		System.out.println("Enter the message sent to the Server...");
//		Scanner sc = new Scanner(System.in);
//		String message = sc.nextLine();
//		OutputStream out = socket.getOutputStream(); //write bytes on network
//		out.write(message.getBytes());
//		System.out.println("Message send to the Server");
//		sc.close();
//		out.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		this.out.write(message.getBytes());
	}
	
	public void readMessages() {
		this.thread = new ClientThread(this.in, this.textArea); //Calling a read thread
		this.thread.start();
	}

	//@SuppressWarnings("unused")
//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//		Client client = new Client();
//	}

}
