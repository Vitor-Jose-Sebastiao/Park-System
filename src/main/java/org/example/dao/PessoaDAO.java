package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.example.modelo.Cargo;
import org.example.modelo.Pessoa;
import org.example.util.FabricaConexao;

public class PessoaDAO implements IPessoaDAO {
    public void inserir(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa (nome, sobrenome, login, senha, cargo) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conexao = FabricaConexao.getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql);
        ) {
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getSobrenome());
            pst.setString(3, pessoa.getLogin());
            pst.setString(4, pessoa.getSenha());
            pst.setString(5, pessoa.getCargo().name());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Pessoa buscarPorId(int id) {
        String sql = "SELECT * FROM Pessoa WHERE id = ?";

        try {
            Pessoa var6;
            try (Connection conexao = FabricaConexao.getConnection()) {
                try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    ResultSet rs = pst.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("login"), rs.getString("senha"), Cargo.valueOf(rs.getString("cargo")));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Pessoa> listarTodas() {
        List<Pessoa> pessoas = new ArrayList();
        String sql = "SELECT * FROM Pessoa";

        try (
                Connection conexao = FabricaConexao.getConnection();
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                pessoas.add(new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("login"), rs.getString("senha"), Cargo.valueOf(rs.getString("cargo"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    public boolean atualizar(Pessoa pessoa) {
        String sql = "UPDATE Pessoa SET nome = ?, sobrenome = ?, login = ?, senha = ?, cargo = ? WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection conexao = FabricaConexao.getConnection();
                    PreparedStatement pst = conexao.prepareStatement(sql);
            ) {
                pst.setString(1, pessoa.getNome());
                pst.setString(2, pessoa.getSobrenome());
                pst.setString(3, pessoa.getLogin());
                pst.setString(4, pessoa.getSenha());
                pst.setString(5, pessoa.getCargo().name());
                pst.setInt(6, pessoa.getId());
                var5 = pst.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM Pessoa WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection conexao = FabricaConexao.getConnection();
                    PreparedStatement pst = conexao.prepareStatement(sql);
            ) {
                pst.setInt(1, id);
                var5 = pst.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pessoa buscarPorLoginSenha(String login, String senha) {
        String sql = "SELECT * FROM Pessoa WHERE login = ? AND senha = ?";

        try {
            Pessoa var7;
            try (Connection conexao = FabricaConexao.getConnection()) {
                try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                    pst.setString(1, login);
                    pst.setString(2, senha);
                    ResultSet rs = pst.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var7 = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("sobrenome"), rs.getString("login"), rs.getString("senha"), Cargo.valueOf(rs.getString("cargo")));
                }
            }

            return var7;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}