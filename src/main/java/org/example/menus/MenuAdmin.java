package org.example.menus;

import java.util.Scanner;
import org.example.modelo.Pessoa;
import org.example.modelo.Vaga;
import org.example.dao.VagaDAO;

public class MenuAdmin {
    public static void exibir(Pessoa admin) {
        Scanner sc = new Scanner(System.in);
        VagaDAO vagaDAO = new VagaDAO();
        System.out.println("Bem-vindo, ADMIN " + admin.getNome());

        while (true) {
            System.out.println("\n== Tela Administrativa ==");
            System.out.println("1. Cadastrar Vaga");
            System.out.println("2. Buscar Vaga");
            System.out.println("3. Atualizar Vaga");
            System.out.println("4. Remover Vaga");
            System.out.println("0. Sair");
            int opcao = sc.nextInt();
            sc.nextLine();
            if (opcao == 0) {
                return;
            }

            Vaga vaga = null;

            switch (opcao) {
                case 1:
                    System.out.println("Código da vaga: ");
                    String codigo = sc.nextLine();
                    vaga = new Vaga(codigo, true);
                    vagaDAO.inserir(vaga);
                    System.out.println("Vaga cadastrada com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o código ou nome da vaga: ");
                    String termoBusca = sc.nextLine();
                    vaga = vagaDAO.buscarPorCodigoOuNome(termoBusca);
                    if (vaga != null) {
                        System.out.println("Vaga encontrada: " + vaga.getCodigo());
                    } else {
                        System.out.println("Vaga não encontrada.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID da vaga a ser atualizada: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    vaga = vagaDAO.buscarPorId(id);
                    if (vaga != null) {
                        System.out.println("Novo código da vaga: ");
                        codigo = sc.nextLine();
                        vaga.setCodigo(codigo);
                        vagaDAO.atualizar(vaga);
                        System.out.println("Vaga atualizada com sucesso!");
                    } else {
                        System.out.println("Vaga não encontrada.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ID da vaga a ser removida: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if (vagaDAO.deletar(id)) {
                        System.out.println("Vaga removida com sucesso!");
                    } else {
                        System.out.println("Vaga não encontrada.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
