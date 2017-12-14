package dividingProcessPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class DataCreator {
	private static DataCreator instance = null;
	
	final static int NUM_OF_LINES = 1000;
	final static int NUM_OF_CHARACTERS_PER_LINE = 1000;
	
	
	private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lower = upper.toLowerCase();
	private static final String digits = "0123456789";
	private static final String alphanum = upper + lower + digits; 
	
	
	Random random = new Random();
	
	String inputDirPath = "./inputs/";
	String inputFilePath;
	
	String encFilePath = "./startingFile.txt";
	
	
	private DataCreator(){
		createEncryptedTextFile();
	}
	
	public void createEncryptedTextFile() {
		File f = new File(encFilePath);
		try {
			if (!f.exists()) {				
				f.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static DataCreator getInstance() throws FileNotFoundException {
		if (instance == null) {
			synchronized (DataCreator.class) {
				if (instance == null) {
					instance =  new DataCreator();
				}
			}
		}
		return instance;
	}
	
	
	
	public void fillEncryptedFileByRandomText() throws IOException {
		BufferedWriter encFileWriter = new BufferedWriter(new FileWriter(encFilePath));
		
		for (int i=0; i<NUM_OF_LINES; i++) {
			encFileWriter.write(getRandomString());;
			encFileWriter.write("\n");
		}
		encFileWriter.flush();
		encFileWriter.close();
	}
	
	public String getRandomString() {
		return getNLengthRandomString(NUM_OF_CHARACTERS_PER_LINE);
	}
	
	public String getNLengthRandomString(int n) {
		String str="";
		
		for (int i=0; i<n; i++) {
			int indx = random.nextInt(alphanum.length());
			str += alphanum.charAt(indx);
		}
		return str;
	}
	
	public String getNthInputFilePath(int n) {
		return inputDirPath + "input" + n + ".txt";
	}
}
