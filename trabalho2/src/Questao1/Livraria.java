package Questao1;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.StringJoiner;

public class Livraria {
	public ArrayList<Livro>livros = new ArrayList<>();
	
	public void lerArquivo(){
		String linha;
		
		try{
			FileReader l = new FileReader("livros.txt");
			BufferedReader arqLivros = new BufferedReader(l);
			
			try{
				System.out.println("Relação de livros disponíveis:");
				while ((linha = arqLivros.readLine()) != null){
					
					String[] pedaco = linha.split(";");					
					double preco = Double.parseDouble(pedaco[2]);
					Livro livro = new Livro(pedaco[0],pedaco[1],preco);
					livros.add(livro);
					
					
				}
			
			}
			catch(Exception e){
				System.out.println("Erro ao ler arquivo");
			}
			
			l.close();
		}
		catch(IOException e	){
			System.out.println("Arquivo não encontrado");
		}
	}	
	
	public void mostraLivraria(){
		for (Livro livro : livros) {
			System.out.println(livro);
		}
	}
}
