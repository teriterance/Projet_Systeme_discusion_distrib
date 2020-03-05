package servPattern;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface IProtocole {

	public void execute( IContext aContext , InputStream anInputStream , OutputStream anOutputStream, Socket clientSocket );
	
}
