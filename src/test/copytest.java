package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class copytest {

	public static void main(String[] args) throws FileNotFoundException,IOException {
		
		File original = new File("C:\\Users/TaeSan/Desktop/14.jpg");
		
		File copy = new File("c:/3.jpg");
		
		FileInputStream fis = new FileInputStream(original);
		FileOutputStream fos = new FileOutputStream(copy);
		
		int b;
		
		long size_10per = original.length();
		
		long read_size = 0;
		
		while((b=fis.read())!= -1)
		{
			fos.write(b);
			fos.flush();
			read_size++;
			if(read_size % size_10per==0)
				System.out.println("*");
			
			
		}
	}
}
