package org.example.modelo;

public class Vaga {
    private int id;
    private String codigo;
    private boolean disponivel;

    public Vaga(int id, String codigo, boolean disponivel) {
        this.id = id;
        this.codigo = codigo;
        this.disponivel = disponivel;
    }

    public Vaga(String codigo, boolean disponivel) {
        this.codigo = codigo;
        this.disponivel = disponivel;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
