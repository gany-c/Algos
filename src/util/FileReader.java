package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	public static List<Integer> readFromFile(String fileName) throws FileNotFoundException{
		
		Scanner scanner = new Scanner(new File(fileName));
		List<Integer> output = new ArrayList<Integer>();
		int i = 0;
		while(scanner.hasNextInt())
		{
		     output.add(scanner.nextInt());
		}
		
		return output;
	}

}
