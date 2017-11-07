package com.thrift.service.impl;

import org.apache.thrift.TException;

import com.thrift.service.Hello.Iface;

public class HelloImpl implements Iface{

	@Override
	public String helloString(String word) throws TException {
		System.out.println("this is hello method");
		System.out.println("param word: " + word);
		System.out.println("method end");
		return "successs";
	}

}
