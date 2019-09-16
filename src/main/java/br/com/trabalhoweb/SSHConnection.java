//package br.com.trabalhoweb;
//
//import org.springframework.context.annotation.Configuration;
//
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.Session;
//
//@Configuration
//public class SSHConnection {
//
////private final static String S_PATH_FILE_PRIVATE_KEY = "C:\\Users\\Val\\.ssh\\privatekeyputy.ppk"; \\windows absolut path of our ssh private key locally saved
//private final static String S_PATH_FILE_KNOWN_HOSTS = "C:\\Users\\Val\\.ssh\\known_hosts";
//private final static String S_PASS_PHRASE = "mypassphrase";
//private final static int LOCAl_PORT = 3307; 
//private final static int REMOTE_PORT = 3306; 
//private final static int SSH_REMOTE_PORT = 22; 
//private final static String SSH_USER = "marcos.agnoletto";
//private final static String SSH_REMOTE_SERVER = "trabalhoweb.marcos.agnoletto.vms.ufsc.br";
//private final static String MYSQL_REMOTE_SERVER = "127.6.159.102";
//
//private Session sesion; //represents each ssh session
//
//	public void closeSSH () {
//		sesion.disconnect();
//	}
//
//	public SSHConnection () throws Throwable {
//
//	    JSch jsch = null;
//	
//	        jsch = new JSch();
////	        jsch.setKnownHosts(S_PATH_FILE_KNOWN_HOSTS);
////	        jsch.addIdentity(S_PATH_FILE_PRIVATE_KEY, S_PASS_PHRASE.getBytes());
//	
//	        sesion = jsch.getSession(SSH_USER, SSH_REMOTE_SERVER, SSH_REMOTE_PORT);
//	        sesion.connect(); //ssh connection established!
//	
//	        //by security policy, you must connect through a fowarded port          
//	        sesion.setPortForwardingL(LOCAl_PORT, MYSQL_REMOTE_SERVER, REMOTE_PORT); 
//	}
//}