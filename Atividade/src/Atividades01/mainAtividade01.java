package Atividades01;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class mainAtividade01 {

	public static void main(String[] args) throws IOException {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		;
		while (scan.hasNext()) {
			funAti fA = new funAti(scan.next() + ".txt");
			Thread t1 = new Thread(fA);
			t1.start();

		}

		scan.close();

	}

	// arquivo_a arquivo_b arquivo_c

}
