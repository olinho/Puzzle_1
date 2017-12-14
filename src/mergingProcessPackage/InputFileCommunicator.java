package mergingProcessPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ThreadFactory;

public class InputFileCommunicator implements Runnable{
	private String filePath; 
	private BufferedReader inStream;
	
	public InputFileCommunicator(String filePath) {
		this.filePath = filePath;
		createInStream();
	}
	
	private void createInStream() {
		try {
			inStream = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		
	}
	
}
