package Atividades01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class funAti implements Runnable {

	private static int quatTotal;
	private String arquivo;

	public funAti(String palavra) {
		this.arquivo = palavra;

	}

	public void run() {
		try {
			soma(quatPalavra(this.arquivo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Quantidade total: " + quatTotal);
	}

	public static int quatPalavra(String Arquivo) throws IOException {
		int cont = 0;
		BufferedReader leitor = new BufferedReader(new FileReader(Arquivo));
		while (leitor.ready()) {
			String linha = leitor.readLine();
			cont += linha.split(" ").length;
		}
		System.out.println("Quantidade de palavras no arquivo: " + cont);
		leitor.close();
		return cont;
	}

	public synchronized static void soma(int valor) {
		quatTotal += valor;
	}

}
