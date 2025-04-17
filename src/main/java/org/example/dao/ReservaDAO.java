package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.example.modelo.Reserva;
import org.example.util.FabricaConexao;

public class ReservaDAO implements IReservaDAO {
    public void inserir(Reserva reserva) {
        String sql = "INSERT INTO Reserva (id_pessoa, id_vaga, data_reserva, status) VALUES (?, ?, ?, ?)";

        try (
                Connection conexao = FabricaConexao.getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql);
        ) {
            pst.setInt(1, reserva.getIdPessoa());
            pst.setInt(2, reserva.getIdVaga());
            pst.setObject(3, reserva.getDataReserva());
            pst.setString(4, reserva.getStatus());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Reserva buscarPorId(int id) {
        String sql = "SELECT * FROM Reserva WHERE id = ?";

        try {
            Reserva var6;
            try (Connection conexao = FabricaConexao.getConnection()) {
                try (PreparedStatement pst = conexao.prepareStatement(sql)) {
                    pst.setInt(1, id);
                    ResultSet rs = pst.executeQuery();
                    if (!rs.next()) {
                        return null;
                    }

                    var6 = new Reserva(rs.getInt("id"), rs.getInt("id_pessoa"), rs.getInt("id_vaga"), (LocalDateTime)rs.getObject("data_reserva", LocalDateTime.class), rs.getString("status"));
                }
            }

            return var6;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Reserva> listarTodas() {
        List<Reserva> reservas = new ArrayList();
        String sql = "SELECT * FROM Reserva";

        try (
                Connection conexao = FabricaConexao.getConnection();
                Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while(rs.next()) {
                reservas.add(new Reserva(rs.getInt("id"), rs.getInt("id_pessoa"), rs.getInt("id_vaga"), (LocalDateTime)rs.getObject("data_reserva", LocalDateTime.class), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    public boolean atualizar(Reserva reserva) {
        String sql = "UPDATE Reserva SET id_pessoa = ?, id_vaga = ?, data_reserva = ?, status = ? WHERE id = ?";

        try {
            boolean var5;
            try (
                    Connection conexao = FabricaConexao.getConnection();
                    PreparedStatement pst = conexao.prepareStatement(sql);
            ) {
                pst.setInt(1, reserva.getIdPessoa());
                pst.setInt(2, reserva.getIdVaga());
                pst.setObject(3, reserva.getDataReserva());
                pst.setString(4, reserva.getStatus());
                pst.setInt(5, reserva.getId());
                var5 = pst.executeUpdate() > 0;
            }

            return var5;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletar(int id) {
        String sql = "DELETE FROM Reserva WHERE id = ?";

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

    public List<Reserva> buscarPorIdPessoa(int idPessoa) {
        List<Reserva> reservas = new ArrayList();
        String sql = "SELECT * FROM Reserva WHERE id_pessoa = ?";

        try (
                Connection conexao = FabricaConexao.getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql);
        ) {
            pst.setInt(1, idPessoa);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                reservas.add(new Reserva(rs.getInt("id"), rs.getInt("id_pessoa"), rs.getInt("id_vaga"), (LocalDateTime)rs.getObject("data_reserva", LocalDateTime.class), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservas;
    }
}