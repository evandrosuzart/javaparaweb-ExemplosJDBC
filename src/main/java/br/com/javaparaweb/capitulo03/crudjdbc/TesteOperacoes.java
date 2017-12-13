package br.com.javaparaweb.capitulo03.crudjdbc;

import java.sql.Date;
import java.util.List;

public class TesteOperacoes {
	public static void main(String[] args) {
		Contato fulano = new Contato();
		fulano.setNome("Fulano");
		fulano.setEmail("fulano@dev.com");
		fulano.setTelefone("+5511 5522-0033");
		fulano.setDataCadastro(new Date(System.currentTimeMillis()));
		ContatoCrudJDBC crud = new ContatoCrudJDBC();
		//exemplo para salvar o contato
		//crud.salvar(fulano);
		Contato testeBusca = new Contato();
		
		//caso tenha registro e precise fazer um teste para excluir o registro "fulano", atribuir o valor
		//do código e excluir para testar
		//fulano.setCodigo(?);
		//crud.excluir(fulano);
		
		//para atualizar o contato fulano
		//crud.atualizar(fulano);
		
		testeBusca.setCodigo(4);
		
		//atribuindo ao contato os dados do regisgtro de código 4
		testeBusca = crud.buscaContato(4);
		
		//verificando se a operação de busca deu certo, exibindo o nme do contato que acabamos de buscar
		System.out.println(testeBusca.getNome());
		
		
		//buscando a lista com todos os contatos salvos
		List<Contato> contatos = crud.listar();
		
		//exibindo o tamanho da lista salva em base de dados
		System.out.println("A lista tem "+contatos.size()+" registros");
	}
}
