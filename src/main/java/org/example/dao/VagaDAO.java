package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.example.modelo.Vaga;
import org.example.util.FabricaConexao;

public class VagaDAO implements IVagaDAO {
    public void inserir(Vaga vaga) {
        String sql = "INSERT INTO Vaga (codigo, disponivel) VALUES (?, ?)";

        try (
                Connection conexao = FabricaConexao.getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql);
        ) {
            pst.setString(1, vaga.getCodigo());
            pst.setBoolean(2, vaga.isDisponivel());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Vaga buscarPorId(int id) {
        String sql = "SELECT * FROM Vaga WHERE id = ?";

        try {
            Vaga var6;
            try (Connection conexao = FabricaConexao.getConnection()) {
                try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    ResultSet rs = pst.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Vaga(rs.getInt("id"), rs.getString("codigo"), rs.getBoolean("disponivel"));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaga> listarTodas() {
        List<Vaga> vagas = new ArrayList();
        String sql = "SELECT * FROM Vaga";

        try (
                Connection conexao = FabricaConexao.getConnection();
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                vagas.add(new Vaga(rs.getInt("id"), rs.getString("codigo"), rs.getBoolean("disponivel")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return vagas;
    }

    public boolean atualizar(Vaga vaga) {
        String sql = "UPDATE Vaga SET codigo = ?, disponivel = ? WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection conexao = FabricaConexao.getConnection();
                    PreparedStatement pst = conexao.prepareStatement(sql);
            ) {
                pst.setString(1, vaga.getCodigo());
                pst.setBoolean(2, vaga.isDisponivel());
                pst.setInt(3, vaga.getId());
                var5 = pst.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM Vaga WHERE id = ?";

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Vaga buscarPorCodigoOuNome(String termo) {
        String sql = "SELECT * FROM Vaga WHERE codigo = ? OR codigo LIKE ?";

        try {
            Vaga var6;
            try (Connection conexao = FabricaConexao.getConnection()) {
                try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                    pst.setString(1, termo);
                    pst.setString(2, "%" + termo + "%");
                    ResultSet rs = pst.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Vaga(rs.getInt("id"), rs.getString("codigo"), rs.getBoolean("disponivel"));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}