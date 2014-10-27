/**
 * 
 */
package com.epam.concurrency.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author I7eter
 *
 */
public final class ConsoleManager {

	/**
	 * 
	 */
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public ConsoleManager() {
		// TODO Auto-generated constructor stub
	}
	
    public final static void clearConsole() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception e) {
            //  Handle any exceptions.
        }
    }

	public static void writeLine(String str) {

		try {
			writer.write(str + "\n");
			writer.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static String getInput(String prompt){

		try {
			writeLine(prompt + ": ");
			return reader.readLine();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
