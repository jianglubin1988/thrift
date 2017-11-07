package com.thrift.server;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

import com.thrift.service.Hello;
import com.thrift.service.impl.HelloImpl; 

public class ThriftServer {

	// 注册端口
		public static final int SERVER_PORT = 8082;

		public static void main(String[] args) throws TException {
			// 设置处理器
			TProcessor tprocessor = new Hello.Processor(new HelloImpl());
			// 简单的单线程服务模型,阻塞IO
			TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
			TServer.Args tArgs = new TServer.Args(serverTransport);
			tArgs.processor(tprocessor);
			//// 使用二进制协议
			tArgs.protocolFactory(new TBinaryProtocol.Factory());
			// 创建服务器
			TServer server = new TSimpleServer(tArgs);
			System.out.println("HelloServer start....");
			server.serve(); // 启动服务
		}
}
