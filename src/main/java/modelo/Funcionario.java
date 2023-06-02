package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Funcionario extends Pessoa implements Serializable {

    private String cargo;

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

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + getId() +
                ", nome='" + getNome() + '\'' +
                ", cpf='" + getCpf() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefone='" + getTelefone() + '\'' +
                ", endereco=" + getEndereco() +
                ", cargo='" + cargo + '\'' +
                '}';
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