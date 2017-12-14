package dividingProcessPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EncodedFileReader {
	private static EncodedFileReader instance = null;
	
	private static final String FILE_PATH = "./startingFile.txt";
	
	private Counter counter;
	private static BufferedReader inStrm;
	private String lastLine = "";
	
	
	private EncodedFileReader() {
		counter = Counter.getInstance();
		createOutStream();
	}
	
	public static EncodedFileReader getInstance() {
		if (instance == null) {
			synchronized (EncodedFileReader.class) {
				if (instance == null) {
					instance = new EncodedFileReader();
				}
			}
		}
		return instance;
	}
	
	public int getLastReadLineNumber() {
		return counter.getCurrentValue();
	}
	
	public String readNextLine() throws Exception {
		String line = null;
		try {
			if ((line = inStrm.readLine()) != null) {
				System.out.println(line);
				counter.increase();
			} else {
				throw new Exception("The last line was reached.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return line;
	}
	
	private void createOutStream() {
		try {
			inStrm = new BufferedReader(new FileReader(FILE_PATH));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
	
	private static class Counter {
		private static Counter instance = null;
		private int counter = 0;
		
		private Counter() {}
		
		public static Counter getInstance() {
			if (instance == null) {
				synchronized (Counter.class) {
					if (instance == null) {
						instance = new Counter();
					}
				}
			}
			return instance;
		}
		
		public int increase() {
			return counter++;
		}
		
		public int getCurrentValue() {
			return counter;
		}
	}
	
	

}
