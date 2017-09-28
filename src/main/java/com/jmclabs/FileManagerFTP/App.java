package com.jmclabs.FileManagerFTP;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");

		SendMyFiles send = new SendMyFiles();

		send.startFTP("ftp.properties", "example.txt");

	}
}
