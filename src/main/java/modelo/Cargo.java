package modelo;

import jakarta.validation.constraints.*;

import javax.xml.transform.Source;

public class Cargo implements Source {

    //@NotNull( message = "O Id do cargo nao pode estar nulo")
    private Integer idCargo;
    //@Min(value = 1, message = "A carga horária mensal não pode ser negativa")

    @Min(value = 1, message = "Value invalid")
    @Max(value = 200, message = "Value invalid")

    private int cargaHorariaMensal;
    @NotEmpty(message = "A descrição do cargo não pode estar vazia")
    @Size(min = 2)
    private String descricao;

    public Cargo() {
    }

    public Cargo(Integer idCargo, int cargaHorariaMensal, String descricao) {
        this.idCargo = idCargo;
        this.cargaHorariaMensal = cargaHorariaMensal;
        this.descricao = descricao;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public int getCargaHorariaMensal() {
        return cargaHorariaMensal;
    }

    public void setCargaHorariaMensal(int cargaHorariaMensal) {
        this.cargaHorariaMensal = cargaHorariaMensal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo=" + idCargo +
                ", cargaHorariaMensal=" + cargaHorariaMensal +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public void setSystemId(String s) {

    }

    @Override
    public String getSystemId() {
        return null;
    }
}