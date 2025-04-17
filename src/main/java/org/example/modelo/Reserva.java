package org.example.modelo;

import java.time.LocalDateTime;

public class Reserva {
    private int id;
    private int idPessoa;
    private int idVaga;
    private LocalDateTime dataReserva;
    private String status;

    public Reserva(int id, int idPessoa, int idVaga, LocalDateTime dataReserva, String status) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idVaga = idVaga;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    public Reserva(int idPessoa, int idVaga, LocalDateTime dataReserva, String status) {
        this.idPessoa = idPessoa;
        this.idVaga = idVaga;
        this.dataReserva = dataReserva;
        this.status = status;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return this.idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdVaga() {
        return this.idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public LocalDateTime getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
