package org.example.menus;

import java.time.LocalDateTime;
import java.util.Scanner;
import org.example.modelo.Pessoa;
import org.example.modelo.Reserva;
import org.example.modelo.Vaga;
import org.example.dao.ReservaDAO;
import org.example.dao.VagaDAO;

public class MenuCliente {
    public static void exibir(Pessoa cliente) {
        Scanner sc = new Scanner(System.in);
        VagaDAO vagaDAO = new VagaDAO();
        ReservaDAO reservaDAO = new ReservaDAO();
        System.out.println("Bem-vindo, " + cliente.getNome());

        while (true) {
            System.out.println("\n== Tela do Cliente ==");
            System.out.println("1. Buscar Vaga");
            System.out.println("2. Reservar Vaga");
            System.out.println("3. Cancelar Reserva");
            System.out.println("4. Confirmar Reserva");
            System.out.println("0. Sair");
            int opcao = sc.nextInt();
            sc.nextLine();
            if (opcao == 0) {
                return;
            }

            Vaga vaga = null;

            switch (opcao) {
                case 1:
                    System.out.println("Digite o código ou nome da vaga: ");
                    String termoBusca = sc.nextLine();
                    vaga = vagaDAO.buscarPorCodigoOuNome(termoBusca);
                    if (vaga != null) {
                        System.out.println("Vaga encontrada: " + vaga.getCodigo());
                    } else {
                        System.out.println("Vaga não encontrada.");
                    }
                    break;
                case 2:
                    System.out.println("Digite o ID da vaga a ser reservada: ");
                    int idVaga = sc.nextInt();
                    sc.nextLine();
                    vaga = vagaDAO.buscarPorId(idVaga);
                    if (vaga != null && vaga.isDisponivel()) {
                        Reserva reserva = new Reserva(cliente.getId(), idVaga, LocalDateTime.now(), "ATIVA");
                        reservaDAO.inserir(reserva);
                        vaga.setDisponivel(false);
                        vagaDAO.atualizar(vaga);
                        System.out.println("Vaga reservada com sucesso!");
                    } else {
                        System.out.println("Vaga não disponível.");
                    }
                    break;
                case 3:
                    System.out.println("Digite o ID da reserva a ser cancelada: ");
                    int idReserva = sc.nextInt();
                    sc.nextLine();
                    Reserva reserva = reservaDAO.buscarPorId(idReserva);
                    if (reserva != null && reserva.getIdPessoa() == cliente.getId()) {
                        reservaDAO.deletar(idReserva);
                        vaga = vagaDAO.buscarPorId(reserva.getIdVaga());
                        if (vaga != null) {
                            vaga.setDisponivel(true);
                            vagaDAO.atualizar(vaga);
                        }
                        System.out.println("Reserva cancelada com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada ou não pertence ao cliente.");
                    }
                    break;
                case 4:
                    System.out.println("Digite o ID da reserva a ser confirmada: ");
                    idReserva = sc.nextInt();
                    sc.nextLine();
                    reserva = reservaDAO.buscarPorId(idReserva);
                    if (reserva != null && reserva.getIdPessoa() == cliente.getId()) {
                        reserva.setStatus("CONFIRMADA");
                        reservaDAO.atualizar(reserva);
                        System.out.println("Reserva confirmada com sucesso!");
                    } else {
                        System.out.println("Reserva não encontrada ou não pertence ao cliente.");
                    }
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
