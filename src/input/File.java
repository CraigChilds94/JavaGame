package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File {
	
	public String path;
	
	/**
	 * Create a new file object for reading and writing game files
	 * @param path Where the file is located
	 * @param name The name of the file
	 * @param ext The extension of the file
	 */
	public File(String path, String name, String ext) {
		this.path = path+name+ext;
	}
	
	/**
	 * Read the file
	 * @param delimiter What seperates each character
	 * @return an arraylist containing arrays of strings
	 * @throws IOException
	 */
	public ArrayList<String[]> read(String delimiter) throws IOException {
		FileReader reader = new FileReader(path);
		BufferedReader buffreader = new BufferedReader(reader);
		ArrayList<String[]> output = new ArrayList<String[]>();
		
		String line;
		int i = 0;
		while(!(line = buffreader.readLine().trim()).equals("END")) {
			output.add(line.split(delimiter));
			i++;
		}
		
		return output;
	}
}
