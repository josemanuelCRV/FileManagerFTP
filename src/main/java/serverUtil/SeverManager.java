package serverUtil;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;

public class SeverManager {

	private final String user = "email@domain.com";
	private final String passwd = "password";
	private final String host = "111.222.3.444";
	private final String path = "/folder/subfolder";
	private final String userInfo = user + ":" + passwd;

	private FileSystemManager fsManager = null;
	private FileSystem fs = null;

	public void checkFTPConnection() throws FileSystemException, URISyntaxException {
		// URI uri = new URI(String scheme,String host,String path,String fragment);
		URI uri = new URI("ftp", userInfo, host, -1, path, null, null);
		//System.out.println(uri.toString());
		FileSystemOptions opts = new FileSystemOptions();
		fsManager = VFS.getManager();	
		FileObject listendir = fsManager.resolveFile(uri.toString(), opts);		
		fs = listendir.getFileSystem();

		System.out.println("[checkFTPConnection] - Connection successfully to: " + listendir.getName().getPath());
				
	}
	
	public FileObject builderConnection(){
		
		 
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

/**
 * Conexión pasive mode: FTP
 * 
 * FileSystemManager fsManager = null; FileSystem fs = null; FileSystemOptions
 * opts = new FileSystemOptions();
 * //FtpFileSystemConfigBuilder.getInstance().setPassiveMode(opts, true);
 * fsManager = VFS.getManager();
 * 
 * FileObject path =
 * fsManager.resolveFile("ftp://user:password@my.ftp.host/test/in/", opts);
 * 
 * fs = path.getFileSystem();
 * 
 * //prints Connection successfully established to /test/in
 * System.out.println("Connection successfully established to " +
 * path.getName().getPath());
 * 
 * 
 */
