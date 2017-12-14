package mergingProcessPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Merger {
	private static final String OUTPUT_FILE_PATH = "./out.txt"; 
	private static BufferedWriter outStrmToEncFile;
	private static String inputDirPath = "./inputs/";
	private static int numberOfInputFiles;
	private String [] linesFromInputs = new String[214748363];
	private int sumOfReadLines = 0; // sum of lines read from input files
	
	BufferedReader [] inStreams;
	
	public Merger() {
		this(inputDirPath);
	}
			
	public Merger(String inputDirPath){
		this.inputDirPath = inputDirPath + "/"; 
		prepareOutputStream();
		
		createEncodedFileIfDoesNotExist();
		createOutStream();
		
		prepareInputStreams();
	}
	
	public void merge() {
		writeAllInputsToArray();
		writeArrayToOutupFile();
	}
	
	public void writeArrayToOutupFile() {

		try {
			for(int i = getIndexOfFirstNotNullLine(); i<=sumOfReadLines; i++) {
				outStrmToEncFile.write(linesFromInputs[i]);
				outStrmToEncFile.write("\n");
			} 
			outStrmToEncFile.flush();
			outStrmToEncFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public int getIndexOfFirstNotNullLine() {
		int k=0;
		while (linesFromInputs[k] == null) {
			k++;
		}
		return k;
	}
	
	public void prepareOutputStream() {
		try {
			outStrmToEncFile = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeAllInputsToArray() {
		for (int i=0; i<inStreams.length; i++) {
			writeDataFromInputFileToArrayUsingStream(i);
		}
	}
	
	public void writeDataFromInputFileToArrayUsingStream(int streamIndex) {
		BufferedReader inStrm = inStreams[streamIndex];
		int num;
		String text = "";
		String line = "";
		try {
			while ( (line = inStrm.readLine()) != null) {
				num = parseLineNumber(line);
				text = parseLineText(line);
				setArrayValueOnIndex(num, text);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void setArrayValueOnIndex(int index, String textValue) {
		if (linesFromInputs[index] == "" || linesFromInputs[index] == null) {
			sumOfReadLines++;
		}
		linesFromInputs[index] = textValue;
	}
	
	public String parseLineText(String line) {
		int indxOfFirstSpace = line.indexOf(' ');
		int indxOfLastChar = line.length();
		return line.substring(indxOfFirstSpace+1, indxOfLastChar);
	}
	
	public int parseLineNumber(String line) {
		int indxOfFirstSpace = line.indexOf(' ');
		return Integer.valueOf(line.substring(0, indxOfFirstSpace));
	}
	
	public void prepareInputStreams() {
		countInputFiles();
		File inputDir = new File(inputDirPath);
		inStreams = new BufferedReader[inputDir.list().length];
		int i=0;
		for (String inputFileName : inputDir.list()) {
			try {
				inStreams[i] = new BufferedReader(new FileReader(inputDirPath+inputFileName));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void countInputFiles() {
		try {
			numberOfInputFiles = getNumberOfFilesInDirectory(inputDirPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumberOfFilesInDirectory(String dir) throws Exception {
		File f = new File(dir);
		if (!f.exists())
			throw new FileNotFoundException("Exception: " + dir + " does not exist");
		if (!f.isDirectory())
			throw new Exception("Exception: " + dir + "is not a directory.");
		return f.list().length;
	}
	
	public void createOutStream() {
		try {
			outStrmToEncFile = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createEncodedFileIfDoesNotExist() {
		File f = new File(OUTPUT_FILE_PATH);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int getSumOfReadLines() {
		return sumOfReadLines;
	}
	
}
