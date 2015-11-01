package com.tony.thrift.demo;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class HelloServerDemo {

	public static final int SERVER_PORT = 7911;

	public void startServer() {
		try {
			System.out.println("Server start ....");

			TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldImpl());
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			TServer server = new TSimpleServer(tArgs);
			server.serve();

		} catch (Exception e) {
			System.out.println("Server start error!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloServerDemo server = new HelloServerDemo();
		server.startServer();
	}

}
