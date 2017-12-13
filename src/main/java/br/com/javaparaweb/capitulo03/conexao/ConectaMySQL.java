package br.com.javaparaweb.capitulo03.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {
	public static void main(String[] args) {
		Connection conexao = null;
		try {
			String url="jdbc:mysql://localhost/agenda";
			String user = "newUser";
			String pass = "newpass";
			conexao=DriverManager.getConnection(url, user, pass);
			System.out.println("Conectou");
			conexao.close();
		}catch(SQLException erro) {
			System.out.println("Erro ao criar conexão. Erro: "+erro.getMessage());
		}
	}
}
