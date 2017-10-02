package com.jmclabs.FileManagerFTP;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

public class SendMyFiles {

	static Properties props;
	

//	public static void main(String[] args) {
//
//		SendMyFiles sendMyFiles = new SendMyFiles();
//		if (args.length < 1) {
//			System.err.println("Usage: java " + sendMyFiles.getClass().getName() + " Properties_file File_To_FTP ");
//			System.exit(1);
//		}
//
//		String propertiesFile = args[0].trim();
//		String fileToFTP = args[1].trim();
//		sendMyFiles.startFTP(propertiesFile, fileToFTP);
//
//	}
	
	public void chargerProperties(String[] args){
		
		SendMyFiles sendMyFiles = new SendMyFiles();
		
		if (args.length < 1) {
			System.err.println("Usage: java " + sendMyFiles.getClass().getName() + " Properties_file File_To_FTP ");
			System.exit(1);
		}		
		String propertiesFile = args[0].trim();
		String fileToFTP = args[1].trim();
		sendMyFiles.startFTP(propertiesFile, fileToFTP);
		
	}

	public boolean startFTP(String propertiesFilename, String fileToFTP) {

		props = new Properties();
		StandardFileSystemManager manager = new StandardFileSystemManager();

		try {

			props.load(new FileInputStream("src/main/resources/" + propertiesFilename));
			String host = props.getProperty("host").trim();
			String user = props.getProperty("user").trim();
			String passwd = props.getProperty("passwd").trim();
			String path = props.getProperty("path").trim();
			String localDirectory = props.getProperty("localDirectory").trim();
			String userInfo = user + ":" + passwd;	
			 
			
			// check if the file exists
			String filepath = localDirectory + fileToFTP;
			File file = new File(filepath);
			if (!file.exists())
				throw new RuntimeException("Error. Local file not found");

			// Initializes the file manager
			manager.init();

			// Setup our SFTP configuration
			FileSystemOptions opts = new FileSystemOptions();
			//FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
			SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
			SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
			SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

			// Create the SFTP URI using the host name, userid, password, remote path and file name
			// String sftpUri = "sftp://" + userId + ":" + password + "@" + serverAddress + "/" + remoteDirectory + fileToFTP;
			
			// Create URI using schema, userInfo, host, remotepath and fileName
			URI uri = new URI("ftp", userInfo, host, -1, path + "/" + fileToFTP, null, null);			
//			String sftpUri = "ftp://" + userId + ":" + password + "@" + serverAddress + "/" + remoteDirectory + fileToFTP;
						
			// Create local file object
			FileObject localFile = manager.resolveFile(file.getAbsolutePath());

			// Create remote file object
			FileObject remoteFile = manager.resolveFile(uri.toString(), opts);

			// Copy local file to sftp server
			remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
			System.out.println("File upload successful");

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			manager.close();
		}

		return true;
	}

}
