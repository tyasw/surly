/*
 * Surly.java
 *
 * 10/10/17
 * Surly implements parts of SURLY, a database management system, in Java.
 */
import java.util.*; 
import java.io.*;
 
public class Main {
	public static Database db;
	public static final float VERSION_NO = 2.0f;

	public static void main(String[] args) {
		try {
			String filename = args[0];
			printTop();
			db = new Database();
		  surlyParse S = new surlyParse(db);
			LinkedList<String> errorsList = new LinkedList<String>();
			long startTime = System.currentTimeMillis();
		  S.parse(args, errorsList);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			printErrors(errorsList);
			printEnd(totalTime);
		} catch (ArrayIndexOutOfBoundsException a) {
			System.err.println("ERROR: NO ARGUMENTS SPECIFIED");
		}
}
	public static void printErrors(LinkedList<String> errorsList) {
		if (errorsList.size() > 0) {
			System.out.println("ERRORS:");
			for (int i = 0; i < 50; i++) {
				System.out.print("=");
			} 
			System.out.println();
			for (int i = 0; i < errorsList.size(); i++) {
				System.out.println(errorsList.get(i));
			}
			for (int i = 0; i < 50; i++) {
				System.out.print("=");
			}
		}
		System.out.println();
		System.out.println();
	}

	public static void printTop() {
		printInfo();
		Date date = new Date();
		System.out.println("The current time is " + date.toString());
		System.out.println();
	}

	public static void printInfo() {
		for (int i = 0; i < 80; i++ ) {
			System.out.print("=");
		}
		System.out.println();
		printImage();
		System.out.println("SURLY Database Management System");
		System.out.println("version " + VERSION_NO);
		System.out.println("Created by Tony Bhangal and William Tyas");
		for (int i = 0; i < 80; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	public static void printImage() {
		try {
			File image = new File("surlyimage.txt");	
			Scanner draw = new Scanner(image);
			while (draw.hasNextLine()) {
				System.out.println(draw.nextLine());
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: IMAGE FILE NOT FOUND");
		}
	}

	public static void printEnd(long totalTime) {
		System.out.println("The total runtime was " + totalTime + "ms.");	
	}
}