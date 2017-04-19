package Questao2;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GeoIp {
	public ArrayList <String> conteudoGeo = new ArrayList<String>();
	public ArrayList <String> listaCodigos = new ArrayList<String>();
	public ArrayList <String> resuldao = new ArrayList<String>();
	
	public void verResultado(){
		lerGeoIp();
		lerCodigo();
		gerarResultado();
	}
	public void lerGeoIp(){
		try {
			File arquivo = new File("GeoIPCountryWhois.csv");
			Scanner f = new Scanner(arquivo).useDelimiter("\\n");
			try {
				while (f.hasNext()) {
					String linha = f.next();
					conteudoGeo.add(linha);
					//System.out.println(linha);					
				}
				f.close();
			}
			catch(Exception e) {
				System.out.println("Ocorreu um erro na leitura do arquivo GeoIPCountryWhois.csv");
			}	
			
		}
		catch(FileNotFoundException e){
			System.out.println("Arquivo GeoIPCountryWhois.csv não encontrado");
		}
	}
	
	public void  lerCodigo(){
		try {
			File arquivo = new File("codigos.txt");
			Scanner f = new Scanner(arquivo);
			try {
				while (f.hasNext()){
					String linha = f.next();
					listaCodigos.add(linha);
				}
			}
			catch(Exception e){
				System.out.println("Falha na leitura do arquivo codigos.txt");
			}			
			f.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Arquivo codigos.txt não encontrado");
		}
		
	}
	
	public void gerarResultado(){
		ArrayList<String>resultado = new ArrayList<String>();
		int contagem = 0;
		String col4 = new String();
		String col5 = new String();
		for (String codigo : listaCodigos){
			for (String geo : conteudoGeo){
				if (geo.contains(codigo)){					
					if (contagem == 0){
						String[] linha = geo.split(",");
						col4 = linha[4];
						col5 = linha[5];
					}
					contagem++;					
				}								
			}
			String col4SemAspas = col4.substring(1, (col4.length()-1));
			String col5SemAspas = col5.substring(1, (col5.length()-1));
			if (contagem != 0){
				resultado.add(col4SemAspas + ": "+  contagem + " (" + col5SemAspas + ")");
			}	
			contagem = 0;
		}
		/*for (String resultados : resultado){
			System.out.println(resultados);
		}*/
		
		escreverArquivo(resultado);
	}
	
	private void escreverArquivo(ArrayList<String>resultado){
		
		try {
			FileOutputStream arquivo = new FileOutputStream("resultado.txt",false);
			PrintWriter pw = new PrintWriter(arquivo);
			for (String resultados : resultado){
				pw.println(resultados);
			}
			pw.close();
		}
		catch (IOException e){
			System.out.println("Não foi possivel criar o arquivo resultado.txt");
		}
		
		try {
			File arquivo = new File("resultado.txt");
			Scanner f = new Scanner(arquivo).useDelimiter("\\n");
			while (f.hasNext()){
				String linha = f.next();
				System.out.print(linha);
			}
			f.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Não foi possível ler o arquivo resultado.txt");
		}
	}		
}