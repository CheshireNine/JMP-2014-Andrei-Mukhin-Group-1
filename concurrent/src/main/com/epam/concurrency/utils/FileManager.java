package com.epam.concurrency.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public final class FileManager {

	private FileManager() {
		// TODO Auto-generated constructor stub
	}

	public static FileOutputStream getFileOutputStream(File file) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}

	public static FileInputStream getFileInputStream(File file) {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stream;
	}
}
