package modelo;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class Funcionario extends Pessoa implements Serializable {
    @NotBlank(message = "O cargo não pode estar em branco.")
    private String cargo;

    private String ctps;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
    private double salario;
    private boolean recebeValeTransporte;
    private int numeroDependentes;

    public Funcionario() {
    }

    public Funcionario(Integer id, String nome, String cpf, String email, String telefone, Endereco endereco, String cargo) {
        super(id, nome, cpf, email, telefone, endereco);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isRecebeValeTransporte() {
        return recebeValeTransporte;
    }

    public void setRecebeValeTransporte(boolean recebeValeTransporte) {
        this.recebeValeTransporte = recebeValeTransporte;
    }

    public int getNumeroDependentes() {
        return numeroDependentes;
    }

    public void setNumeroDependentes(int numeroDependentes) {
        this.numeroDependentes = numeroDependentes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Funcionario other = (Funcionario) obj;
        return Objects.equals(getId(), other.getId()) &&
                Objects.equals(getNome(), other.getNome()) &&
                Objects.equals(getCpf(), other.getCpf());
    }
}