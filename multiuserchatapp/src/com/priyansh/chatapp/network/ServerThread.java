package com.priyansh.chatapp.network;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread need a job to perform
//For job we got runnable
//Once a job is created via runnable then write the job function inside a run function
//Assign the job to a Thread
//public class ServerThread implements Runnable {

public class ServerThread extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerThread(Socket clientSocket, Server server) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		this.in = clientSocket.getInputStream(); //Client Data Read
		this.out = clientSocket.getOutputStream(); //Client Data Write
		System.out.println("New Client Comes");
	}
	
	@Override
	public void run() {
		//Read Data from Client and BroadCast to all
		BufferedReader br = new BufferedReader(new InputStreamReader(this.in));
		String line;
		try {
		while(true) {
			
				line = br.readLine();// \n
				System.out.println("Line Read..."+line);
				if(line.equalsIgnoreCase("quit")) {
					break; //Client Chat End
				}
				//out.write(line.getBytes()); //Client Send
				//BroadCast to all
				for(ServerThread serverThread : server.threads) {
					line = line + "\n";
					serverThread.out.write(line.getBytes());
				}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally {
			try {
			if(br!=null) {
				br.close();
			}
			if(this.in!=null) {
				in.close();
			}
			if(this.out!=null) {
				out.close();
			}
			if(this.clientSocket!=null) {
				clientSocket.close();
			}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
