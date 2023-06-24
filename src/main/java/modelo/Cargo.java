package modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import javax.xml.transform.Source;

public class Cargo extends Entidade implements Source {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    //@Min(value = 1, message = "A carga horária mensal não pode ser negativa")

    @Min(value = 1, message = "Value invalid")
    @Max(value = 200, message = "Value invalid")

    private int cargaHorariaMensal;
    @NotEmpty(message = "A descrição do cargo não pode estar vazia")
    @Size(min = 2)
    private String descricao;

    public Cargo() {
    }

    public Cargo(Integer id, int cargaHorariaMensal, String descricao) {
        this.id = id;
        this.cargaHorariaMensal = cargaHorariaMensal;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setIdCargo(Integer idCargo) {
        this.id = idCargo;
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
                "idCargo=" + id +
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