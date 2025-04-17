package org.example.dao;

import java.util.List;
import org.example.modelo.Pessoa;

public interface IPessoaDAO {
    void inserir(Pessoa var1);

    Pessoa buscarPorId(int var1);

    List<Pessoa> listarTodas();

    boolean atualizar(Pessoa var1);

    boolean deletar(int var1);

    Pessoa buscarPorLoginSenha(String var1, String var2);
}
