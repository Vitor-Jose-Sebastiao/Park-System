package org.example.menus;

import java.util.Scanner;
import org.example.modelo.Cargo;
import org.example.modelo.Pessoa;
import org.example.dao.PessoaDAO;

public class MenuPrincipal {
    public static void exibir() {
        Scanner sc = new Scanner(System.in);
        PessoaDAO dao = new PessoaDAO();

        try {
            while (true) {
                System.out.println("\n== Menu Principal ==");
                System.out.println("1. Cadastrar Novo Usuário");
                System.out.println("2. Login de Usuário");
                System.out.println("3. Login Administrativo");
                System.out.println("0. Sair");
                int opcao = sc.nextInt();
                sc.nextLine();
                if (opcao == 0) {
                    break;
                }

                String login = null;
                String senha = null;

                switch (opcao) {
                    case 1:
                        System.out.println("Nome: ");
                        String nome = sc.nextLine();
                        System.out.println("Sobrenome: ");
                        String sobrenome = sc.nextLine();
                        System.out.println("Login: ");
                        login = sc.nextLine();
                        System.out.println("Senha: ");
                        senha = sc.nextLine();
                        System.out.println("Confirme a senha: ");
                        String confirma = sc.nextLine();
                        if (!senha.equals(confirma)) {
                            System.out.println("Senhas não coincidem!");
                        } else {
                            Pessoa p = new Pessoa(0, nome, sobrenome, login, senha, Cargo.CLIENTE);
                            dao.inserir(p);
                            System.out.println("Usuário cadastrado!");
                        }
                        break;
                    case 2:
                    case 3:
                        System.out.println("Login: ");
                        login = sc.nextLine();
                        System.out.println("Senha: ");
                        senha = sc.nextLine();
                        Pessoa p = dao.buscarPorLoginSenha(login, senha);
                        if (p != null) {
                            if (opcao == 2) {
                                MenuCliente.exibir(p);
                            } else {
                                MenuAdmin.exibir(p);
                            }
                        } else {
                            System.out.println("Login ou senha inválidos.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
