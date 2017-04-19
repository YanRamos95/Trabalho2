package Questao1;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Livraria livraria = new Livraria();
		
		livraria.lerArquivo();
		livraria.mostraLivraria();
		
		PesquisaLivros pesquisa = new PesquisaLivros();
		
		pesquisa.menuPesquisa(livraria);
		
		
	}

}
