package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
public class Endereco extends Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotBlank(message = "O Id do endereco nao pode estar em branco")
    @NotNull(message = "O Id do endereco nao pode estar nulo")
    @NotEmpty(message = "O Id do endereco nao pode estar vazio")
    private Integer id;

    @Size(max = 50, message = "O nome da cidade deve ter no máximo {max} caracteres")
    private String cidade;

    @Size(max = 50, message = "O nome do bairro deve ter no máximo {max} caracteres")
    private String bairro;

    @Size(max = 100, message = "O nome da rua deve ter no máximo {max} caracteres")
    private String rua;

    @Size(max = 10, message = "O número deve ter no máximo {max} caracteres")
    private String numero;

    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
    private String cep;

    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "O telefone deve estar no formato 000-000-0000")
    private String telefone;

    public Endereco() {
    }

    public Endereco(Integer id, String cidade, String bairro, String rua, String numero, String cep, String telefone) {
        this.id = id;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.telefone = telefone;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", cep=" + cep + ", telefone=" + telefone + '}';
    }
}