package br.com.javaparaweb.capitulo03.crudjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class ContatoCrudJDBC {

	// método que adiciona o contato para a base de dados
	public void salvar(Contato contato) {
		Connection conexao = this.geraConexao();
		PreparedStatement insertST = null;
		String sql = "insert into contato(nome, telefone, email, dt_cad, obs) values (?,?,?,?,?) ";
		try {
			insertST = conexao.prepareStatement(sql);
			insertST.setString(1, contato.getNome());
			insertST.setString(2, contato.getTelefone());
			insertST.setString(3, contato.getEmail());
			insertST.setDate(4, contato.getDataCadastro());
			insertST.setString(5, contato.getObervacao());
			insertST.executeUpdate();
		} catch (SQLException erro) {
			System.out.println("Erro ao adicionar contato na base. Erro: " + erro.getMessage());
		}finally {
			try {
				insertST.close();
				conexao.close();
				System.out.println("Conexões encerradas");
			}catch(SQLException erro) {
				System.out.println("Erro ao fechar as operações de cadastro. Erro: "+erro.getMessage());
			}
		}
	}

	//método que atualiza informações de um determinado contato 
	public void atualizar(Contato contato) {
		
		String sql = "UPDATE contato SET nome=?, telefone=?, email=?, dt_cad=?, obs=? WHERE codigo=?";
		Connection conexao = this.geraConexao();
		PreparedStatement update = null;
		try {
			update= conexao.prepareStatement(sql);
			update.setString(1, contato.getNome());
			update.setString(2, contato.getTelefone());
			update.setString(3, contato.getEmail());
			update.setDate(4, contato.getDataCadastro());
			update.setString(5, contato.getObervacao());
			update.setInt(6, contato.getCodigo());
			update.execute();
			System.out.println("Contato atualizado com sucesso!");
		}catch(SQLException erro) {
			System.out.println("Erro ao atualizar contato. Mensagem: "+erro.getMessage());
		}finally {
			try {
				update.close();
				conexao.close();
				System.out.println("Conexões encerradas");
			}catch(SQLException erro){
				System.out.println("Erro ao fechar as operações de atualização. Erro: "+erro.getMessage());
			}
		}
	
	}

	public void excluir(Contato contato) {
		PreparedStatement exclui = null;
		String sql = "DELETE FROM contato WHERE codigo=?";
		Connection conexao = this.geraConexao();
		try {
			exclui = conexao.prepareStatement(sql);
			exclui.setInt(1, contato.getCodigo());
			exclui.execute();
			System.out.println("Contato excluído com sucesso");
		}catch (SQLException erro) {
			System.out.println("Erro ao deletar contato. Erro: "+erro.getMessage());
		}finally {
			try {
				exclui.close();
				conexao.close();
				System.out.println("Conexões encerradas");
			}catch(SQLException erro) {
				System.out.println("Erro ao fechar as operações de exclusão de contato. Menssagem: "+erro.getMessage());
			}
		}
				
	}

	// método que lista os contatos cadastrados na base
	public List<Contato> listar(){
		Connection conexao = this.geraConexao();
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		String sql = "select* from contato";
		try {
			consulta = (Statement) conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			while (resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObervacao(resultado.getString("obs"));
				contatos.add(contato);
			}
		}catch(SQLException erro) {
			System.out.println("Erro ao listar contatos. Mensagem: "+erro.getMessage());
		}finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
				System.out.println("Conexões encerradas");
			}catch(SQLException erro) {
				System.out.println("Erro ao fechar as operações de consulta.  Mensagem: "+erro.getMessage());
			}
		}
		return contatos;
	}

	public Contato buscaContato(int valor) {
		Contato contato = new Contato();
		String sql= "select * from contato where codigo ="+valor;
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Connection conexao = this.geraConexao();
		
		try {
			consulta=conexao.prepareStatement(sql);
			resultado= consulta.executeQuery();
			while(resultado.next()) {
				
				contato.setCodigo(valor);
				contato.setNome(resultado.getString("nome"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setObervacao(resultado.getString("obs"));
			}
			consulta.execute();
		}catch(SQLException erro) {
			System.out.println("Erro ao buscar contato. Erro: "+erro.getMessage());
		}finally {
			try {
				resultado.close();
				consulta.close();
				conexao.close();
				System.out.println("Operações enscerradas");
			}catch(SQLException erro) {
				System.out.println("Erro ao fechar as operações de consulta. Erro: "+erro.getMessage());
			}
		}
		
		
		return contato;
	}

	public Connection geraConexao() {
		Connection conexao = null;
		try {
			String url="jdbc:mysql://localhost/agenda";
			String user = "newUser";
			String pass = "newpass";
			conexao=DriverManager.getConnection(url, user, pass);
			System.out.println("Conectou");
			
		}catch(SQLException erro) {
			System.out.println("Erro ao criar conexão. Erro: "+erro.getMessage());
		}
		return conexao;
	}

	

}
