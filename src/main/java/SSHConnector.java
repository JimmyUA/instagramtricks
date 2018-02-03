import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHConnector {

    private static String user = "root";
    private static String password = "root";
    private static String host = "92.42.107.78";
    private static int port = 22;

    public static void main(String[] args) throws JSchException {
        JSch jSch = new JSch();
        Session session =jSch.getSession(user, host, port);
//        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Connecting...");
        session.connect();
        System.out.println("Connected");

    }

}
