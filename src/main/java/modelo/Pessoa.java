package modelo;

import annotations.CPF;
import jakarta.validation.constraints.*;

import javax.xml.transform.Source;
import java.util.Objects;


public class Pessoa extends Entidade implements Source {

    private Integer id;

    @NotBlank(message = "Invalid Field")
    @NotNull(message = "Invalid Field")
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]{2,90}$\n")
    @Size(min = 2, max = 90, message = "Invalid Field")
    private String nome;

    @NotBlank(message = "Invalid Field")
    @Size(min = 11, max = 14, message = "Invalid field")
    @Pattern(regexp = "[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}", message = "Invalid field")
    @CPF
    private String cpf;

    @NotBlank(message = "Invalid Field")
    @NotNull(message = "Invalid Field")
    @Email(message = "Invalid Field")
    private String email;

    @NotBlank(message = "Invalid Field")
    @NotNull(message = "Invalid Field")
    @Size(min = 8, max = 13, message = "Invalid field")
    @Pattern(regexp = "\\(?[0-9]{2,3}\\)?\\s[0-9]{4,5}\\-?[0-9]{4}", message = "Invalid field")
    private String telefone;
    private Endereco endereco;

    public Pessoa() {
        this.endereco = new Endereco();
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + ", endereco=" + endereco + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.cpf);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }


    @Override
    public void setSystemId(String s) {

    }

    @Override
    public String getSystemId() {
        return null;
    }

    public Boolean login(String mail, String senha) {

        if (mail != "" && senha != "") {
            return true;
        }
        return false;
    }
}
