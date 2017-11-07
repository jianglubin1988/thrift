package com.thrift.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.thrift.service.Hello;

public class ThriftClient {

	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 8082;
	public static final int TIMEOUT = 30000;

	public static void main(String[] args) {
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("logType", "ICS");
			headers.put("timestamp", String.valueOf(new Date().getTime()));
			String json = "{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"AnyLog\\\",\\\"namespace\\\":\\\"com.iflytek.flume.ftpsink.model\\\",\\\"fields\\\":[{\\\"name\\\":\\\"fields\\\",\\\"type\\\":{\\\"type\\\":\\\"map\\\",\\\"values\\\":{\\\"type\\\":\\\"array\\\",\\\"items\\\":[\\\"string\\\",\\\"int\\\",\\\"long\\\",\\\"bytes\\\",\\\"float\\\",\\\"double\\\",\\\"boolean\\\"],\\\"java-class\\\":\\\"java.util.List\\\"}}}]}";
			// String str = StrUtils.getString(body);
			// 设置传输通道
			TTransport transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
			// 协议要和服务端一致
			// 使用二进制协议
			TProtocol protocol = new TBinaryProtocol(transport);
			// 创建Client
			Hello.Client client = new Hello.Client(protocol);
			transport.open();
			String result = client.helloString(json);
			System.out.println("result : " + result);
			// 关闭资源
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
