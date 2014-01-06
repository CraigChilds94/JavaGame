package input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class File {
	
	public String path;
	
	public File(String path, String name, String ext) {
		this.path = path+name+ext;
	}
	
	public String[][] getData(String delimiter, int a, int b) throws IOException {
		FileReader reader = new FileReader(path);
		BufferedReader buffreader = new BufferedReader(reader);
		String[][] output = new String[a][b];
		
		String line;
		int i = 0;
		while(!(line = buffreader.readLine().trim()).equals("END")) {
			System.out.println(line);
			output[i] = line.split(delimiter);
			System.out.println(a);
			i++;
		}
		
		return output;
	}
}
