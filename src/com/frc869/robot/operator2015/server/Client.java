package com.frc869.robot.operator2015.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	private Socket socket;
	private PrintWriter out;

	public Client(Socket socket) throws IOException {
		this.socket = socket;
		this.out = new PrintWriter(socket.getOutputStream(), true);
		//TODO handshake!!!
	}

	public void connected() {
		while(this.socket.isConnected()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String str) {
		this.out.println(str);
	}

	public boolean isConnected() {
		return this.socket.isConnected();
	}

}
