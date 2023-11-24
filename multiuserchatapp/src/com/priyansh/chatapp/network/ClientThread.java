package com.priyansh.chatapp.network;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

//Client data Read
public class ClientThread extends Thread {
	private InputStream in;
	private JTextArea textArea;
	public ClientThread(InputStream in, JTextArea textArea) {
		this.in = in;
		this.textArea = textArea;
	}
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(this.in));
		String line;
		try {
		while(true) {
			line = br.readLine(); // \n
			System.out.println("Line Read..."+(line+"\n"));
			this.textArea.setText(this.textArea.getText()+ line+"\n");
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
