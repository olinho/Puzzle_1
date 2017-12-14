import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.Test;

import dividingProcessPackage.DataCreator;
import dividingProcessPackage.EncodedFileReader;
import mergingProcessPackage.Merger;

public class TesterOfDividingProcessPackageClasses {

	@Test
	public void testInstanceOfDataCreator() {
		DataCreator dataCreator = null;
		try {
			dataCreator = DataCreator.getInstance();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertNotNull(dataCreator);
	}
	
	@Test
	public void testExceptionOnExceedFileLength() {
		EncodedFileReader encodedFileReader = EncodedFileReader.getInstance();
		int n=0;
		try {
			while(true) {
				encodedFileReader.readNextLine();
				n++;
			}
		} catch (Exception e) {
			assertEquals("The last line was reached.", e.getMessage());
		}
		
	}
	
	@Test
	public void testNumberOfReadLines() {
		Merger merger = new Merger();
		merger.writeAllInputsToArray();
		assertEquals(merger.getSumOfReadLines(), 1000);
	}


}
