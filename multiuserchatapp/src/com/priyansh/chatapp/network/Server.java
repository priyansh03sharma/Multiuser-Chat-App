package com.priyansh.chatapp.network;

import java.io.IOException;


import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerThread> threads = new ArrayList<>(); //Contains all client sockets
	public Server() throws IOException {
		int PORT = Integer.parseInt("9999");
		this.serverSocket = new ServerSocket(PORT);
		System.out.println("Server start and waiting for Clients to join...");
		this.handleClientRequest();
	}
	//Multiple Client HandShaking
	public void handleClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = this.serverSocket.accept();//HnadShaking
			//Per Client Per Thread
			ServerThread serverThread = new ServerThread(clientSocket, this); //Creating a new Thread
			this.threads.add(serverThread);
			serverThread.start();
			}
	}
	
	/*Single Client*/
	/*
	public Server() throws IOException {
		int PORT = Integer.parseInt("9999");
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the client connection...");
		Socket socket = serverSocket.accept(); //HandShaking
		System.out.println("Client joins the server");
		InputStream in = socket.getInputStream(); //read bytes from network
		@SuppressWarnings("unused")
		byte arr[] = in.readAllBytes();
		String str = new String(arr); // bytes converts into string
		System.out.println("Message received from the Client " + str);
		in.close();
		socket.close();
	}*/

	//@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server = new Server();
	}

}
