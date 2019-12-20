

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Main {

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("app start ......");

		hehe();


	}
	
	
	static ExecutorService service = Executors.newFixedThreadPool(1000);

	private static void hehe() throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(8091);
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println(socket.getInetAddress().getHostAddress());
			ReadWriteRunnable readWriteRunnable = new ReadWriteRunnable(socket);
			service.execute(readWriteRunnable);
		}

	}
	
	
	

}
