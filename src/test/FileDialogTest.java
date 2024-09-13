package test;

import java.awt.FileDialog;
import java.awt.Frame;

public class FileDialogTest {

	public static void main(String[] args) {
		
		
		Frame f = new Frame("Parent");
		f.setSize(300,200);
		
		FileDialog fileopen = new FileDialog(f,"���Ͽ���",FileDialog.LOAD);
		
		f.setVisible(true);
		fileopen.setDirectory("C:\\");
		fileopen.setVisible(true);
		
		System.out.println(fileopen.getDirectory()+fileopen.getFile());
	}
	
}
