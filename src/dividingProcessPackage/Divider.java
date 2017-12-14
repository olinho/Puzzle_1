package dividingProcessPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;


/**
 * class which divide output decoded file into many input files
 * for each line of output file, according to given pattern:
 * Num_of_line <space> line content
 * @author AlekSandR
 *
 */
public class Divider {
	final static int NUM_OF_FILES = 100;
	
	String inputDirPath = "./inputs/";
	BufferedWriter outStrmForChosenFile;
	
	EncodedFileReader encFileReader = EncodedFileReader.getInstance();
	String encFilePath = "./startingFile.txt";
	
	
	public Divider() {
		clearInputDirectory();
	}
	
	
	public void splitFileIntoSmallerAndShowDurationOfProcess() {
		long startTime = System.currentTimeMillis();
		splitFileIntoSmallerFiles();
		long finishTime = System.currentTimeMillis();
		long elapsedTime = finishTime - startTime;
		System.out.println("Time of working: "+elapsedTime);
	}
	
	private void clearInputDirectory() {
		File inputDir = new File(inputDirPath);
		String[] inputFiles = inputDir.list();
		for (String filename : inputFiles) {
			removeInputTxtFile(filename);
		}
	}
	
	private void removeInputTxtFile(String filename) {
		File f = new File(filename);
		if (f.exists()) {
			f.delete();
		} else {
			f = new File(inputDirPath + filename);
			if (f.exists()) {
				f.delete();
			}
		}
	}




	public void splitFileIntoSmallerFiles() {
		String nextEncodedLine;
		int lineNumber;
		try {
			while ((nextEncodedLine = encFileReader.readNextLine()) != null) {
				lineNumber = encFileReader.getLastReadLineNumber();
				saveNextLineFromOutputToInputFile(nextEncodedLine, lineNumber);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void saveNextLineFromOutputToInputFile(String encodedLineContent, int lineNumber) {
		int randomN = getRandomNumOfFile();
		String filePath = getNthInputFilePath(randomN);
		createFileIfDoesNotExists(filePath);
		
		try {
			outStrmForChosenFile = new BufferedWriter(new FileWriter(filePath, true));
			outStrmForChosenFile.write(lineNumber + " " + encodedLineContent); 
			outStrmForChosenFile.write("\n");
			outStrmForChosenFile.flush();
			outStrmForChosenFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				outStrmForChosenFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getNthInputFilePath(int n) {
		return inputDirPath + "input" + n + ".txt";
	}
	
	public void createFileIfDoesNotExists(String filePath) {
		File f = new File(filePath);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getRandomNumOfFile() {
		Random r = new Random();
		return r.nextInt(NUM_OF_FILES);
	}
	
	public void createDirectoryIfDoesNotExist() {
		File f = new File(inputDirPath);
		f.mkdir();
	}
}
