package org.example.dao;

import java.util.List;
import org.example.modelo.Reserva;

public interface IReservaDAO {
    void inserir(Reserva var1);

    Reserva buscarPorId(int var1);

    List<Reserva> listarTodas();

    boolean atualizar(Reserva var1);

    boolean deletar(int var1);

    List<Reserva> buscarPorIdPessoa(int var1);
}