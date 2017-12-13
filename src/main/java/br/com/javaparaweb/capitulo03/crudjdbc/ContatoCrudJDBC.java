package br.com.javaparaweb.capitulo03.crudjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ContatoCrudJDBC {
	
	//método que adiciona o contato para a base de dados
	public void salvar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insertST=null;
		String sql = "insert into contato(nome, telefone, email, dt_cad, obs) values (?,?,?,?,?) ";
		try {
			insertST= conexao.prepareStatement(sql);
			insertST.setString(1, contato.getNome());
			insertST.setString(2, contato.getTelefone());
			insertST.setString(3, contato.getEmail());
			insertST.setDate(4, contato.getDataCadastro());
			insertST.setString(5, contato.getObervacao());
			insertST.executeUpdate();
		}catch(SQLException erro) {
			System.out.println("Erro ao adicionar contato na base. Erro: "+erro.getMessage());
		}
	}
	
	public void atualizar(Contato contato) {}
	public void excluir(Contato contato) {}
	public List<Contato> listar(){}
	public Contato buscaContato(int valor) {}
	public Connection geraConexao() {}
	public static void main(String[]args) {}
	
}
