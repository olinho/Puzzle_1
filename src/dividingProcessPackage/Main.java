package dividingProcessPackage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mergingProcessPackage.Merger;

public class Main {

	public static void main(String[] args) throws IOException {
		Merger merger = new Merger(args[0]);
		merger.merge();
	}
	
	public static void mergeAndShowDurationOfThisProcess() {
		long startTime = System.currentTimeMillis();
		Merger merger = new Merger();
		merger.merge();
		long finishTime = System.currentTimeMillis();
		long duration = finishTime - startTime;
		System.out.println(duration);
	}

}
