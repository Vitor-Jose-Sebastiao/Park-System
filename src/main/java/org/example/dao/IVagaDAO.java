package org.example.dao;

import java.util.List;
import org.example.modelo.Vaga;

public interface IVagaDAO {
    void inserir(Vaga var1);

    Vaga buscarPorId(int var1);

    List<Vaga> listarTodas();

    boolean atualizar(Vaga var1);

    boolean deletar(int var1);

    Vaga buscarPorCodigoOuNome(String var1);
}