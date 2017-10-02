package com.jmclabs.FileManagerFTP;

import java.net.URISyntaxException;

import org.apache.commons.vfs2.FileSystemException;

import com.jmclabs.builder.Resource;

import serverUtil.SeverManager;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws URISyntaxException {
		
		System.out.println("Hello World!");
		 
		
		SeverManager severManager = new SeverManager();
		SendMyFiles send = new SendMyFiles();
		
		try {
			severManager.checkFTPConnection();
			System.out.println("Connection successfully established to OK!!!");
									
			send.startFTP("ftp.properties", "doc_subida.txt");
			 
		} catch (FileSystemException e) {		
			e.printStackTrace();
		}


//		Resource recurso = new Resource.ResourceBuilder()
//				.server(SERVER)
//				.localpath(FILENAME)
//				.remotepath(REMOTEPATH)
//				.build();
//
//		putFileToFTP(recurso);
		

	}

}
