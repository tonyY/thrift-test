package com.tony.thrift.demo;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class HelloClientDemo
{
	public static final String SERVER_IP = "localhost";
	public static final int SERVER_PORT = 7911;
	public static final int TIMEOUT = 30000;

	public void startClient(String userName) {
		TTransport transport = null;
		try {
			transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			TProtocol protocol = new TBinaryProtocol(transport);
			HelloWorldService.Client client = new HelloWorldService.Client(protocol);
			transport.open();
			String result = client.sayHello(userName);
			System.out.println(result);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}

	public static void main(String[] args) {
		HelloClientDemo client = new HelloClientDemo();
		client.startClient("tony");

	}

}
