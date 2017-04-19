package Questao1;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.InputMismatchException;


public class PesquisaLivros {
	
	public ArrayList <Livro> pesquisa = new ArrayList<>();
	
	public void menuPesquisa(Livraria livraria) {
		int opcao;
		System.out.print("\n");
		System.out.print("\n");
		System.out.println("Pesquisa de livros!!!!! ");
		do {
			pesquisa.clear();
			do {				
				System.out.println("Como quer pesquisar, pelo título da obra ou pela autor?");
				try{
					opcao = pegarInt("Insira 0 para título ou 1 para autor: ");
				}
				catch(InputMismatchException e){
					opcao = 2;
				}
				if (opcao != 0 && opcao != 1) System.out.println("Opção inválida!!");						
			}
			while (opcao != 0 && opcao != 1);
			String dica = new String();
			int i = 0;
			do {
				try{
					dica = fazPesquisa(opcao, livraria);
				}
				catch(InputMismatchException e){
					pesquisa.clear();
				}
				if (pesquisa.size() == 0) {
					System.out.println("Livro não encontrado");
					i++;
				}
				if (i == 3) break;
			}
				
			while (pesquisa.size() == 0);
			this.listarPesquisa();
			this.gravarPesquisa(dica, opcao);
			do{
				try{
					opcao = pegarInt("Deseja realizar nova busca? 0 - SIM     1 - NÃO");
				}
				catch(InputMismatchException e){
					opcao = 2;
					 System.out.println("Opção inválida!!");
				}
			}
			while (opcao == 2);
		}
		while (opcao == 0);
		System.out.println("Tchau!!!!");
	}
	
	
	public String fazPesquisa(int opcao, Livraria livraria){
		String dica;
		if (opcao == 0) {
			dica = pegarString("Insira o título da obra ou parte dele:");
		}
		else {
			dica = pegarString("Insira o nome autor ou parte dele:");
		}
		
		for (Livro livro : livraria.livros) {
			
			if (opcao == 0) {
				if (livro.getTitulo().contains(dica)) 
					pesquisa.add(livro);
			}
			else 
				if (livro.getAutor().contains(dica))
					pesquisa.add(livro);
		}
		return dica;
	}
	
	
	
	private int pegarInt(String mensagem){
		System.out.print(mensagem+" ");
		Scanner a = new Scanner(System.in);
		int retorno = a.nextInt();
		return retorno;
	}
	private String pegarString(String mensagem){
		System.out.print(mensagem);
		Scanner a = new Scanner(System.in);
		String retorno = a.next();
		return retorno;
	}
	
	public void listarPesquisa(){		
		for (Livro livro : pesquisa) {
			System.out.println(livro);			
		}
	}
	
	public void gravarPesquisa(String dica, int opcao){
		try {
			FileOutputStream arquivoPesquisa = new FileOutputStream("pesquisa.txt",true);
			PrintWriter pw = new PrintWriter(arquivoPesquisa);
			//System.out.println("dica: "+dica+" opção: "+opcao);
			if (opcao == 0)
				pw.println("Pesquisa de Título feita por "+dica);
			else 
				pw.println("Pesquisa de Autor feita por "+dica);
			for (Livro livro : pesquisa) {
				pw.println(" - "+livro);
			}
			pw.println(" ");
			pw.println(" ");
			System.out.println("Pesquisa gravada no log pesquisa.txt");
			pw.close();
		}
		catch(Exception e){
			System.out.println("Erro na gravação do log");
		}
		
	}
}
