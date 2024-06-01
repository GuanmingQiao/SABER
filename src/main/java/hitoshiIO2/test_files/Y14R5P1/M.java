import java.util.Scanner;
import java.io.File;

public class M {
	public static void main(String[] args) {
		try {
			File f = new File("/Users/adityasridhar/Documents/COLUMBIA/hitoshiIO2/test_files/Y14R5P1/A.in");
			Scanner scan = new Scanner(f);
			System.out.println("Number of test cases: " + scan.nextLine());
			int numTests = 0;
			while(scan.hasNextLine()) {
				numTests++;
				scan.nextLine();
			}
			System.out.println("Number of lines corresponding to test cases: " + numTests);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}