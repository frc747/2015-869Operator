package com.frc869.robot.operator2015.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private ServerSocket server;
	private boolean running = false;
	private int port;
	private Thread thread;
	private Client client;
	
	public Server(int port){
		this.port = port;
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}


	@Override
	public void run(){
		while(running){
			try {
				this.server = new ServerSocket(port);
				Socket socket;
				while((socket = this.server.accept()) != null){
					this.client = new Client(socket);
					this.client.connected();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String str){
		if(this.client != null){
			this.client.sendMessage(str);
		}
	}
	
	public boolean hasClient(){
		if(this.client != null){
			return this.client.isConnected();
		}
		return true;
	}

}
