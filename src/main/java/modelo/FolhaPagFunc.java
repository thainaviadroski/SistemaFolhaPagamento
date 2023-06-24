package modelo;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDate;

public class FolhaPagFunc extends Entidade implements Serializable {
    @NotNull(message = "O id da folha de pagamentos não pode ser nulo.")
    @NotBlank(message = "O id da folha de pagamento nao pode estar vazia.")
    @NotEmpty(message = "O Id da folha de pagamento nao pode estar vazio")
    private Integer id;
    @Range(min = 1900, max = 2023, message = "O ano deve conter 4 numeros")
    @Min(value = 1900, message = "O ano de referência deve ser igual ou superior a 1900.")
    private int anoReferencia;
    @Range(min = 01, max = 12, message = "O mes deve conter 2 numeros e ser entre 01 e 12")
    private int mesReferencia;

    @PastOrPresent
    @NotNull(message = "A data de pagamento não pode ser nula.")
    private LocalDate dataPagamento;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @PastOrPresent
    @Min(value = 0, message = "As horas trabalhadas não podem ser negativas.")
    private int horasTrabalhadas;
    @Range(min = 0, max = 31, message = "nao podem existir faltas negativas nem sao permitidas mais de 31 faltas")
    private int faltasSemJustificativas;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O salário base não pode ser negativo.")
    private double salarioBase;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O valor das horas extras não pode ser negativo.")
    private double valorHorasExtras;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O total de proventos não pode ser negativo.")
    private double totalProventos;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O valor do vale transporte não pode ser negativo.")
    private double valorValeTransporte;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O valor do vale alimentação não pode ser negativo.")
    private double valorValeAlimentacao;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O desconto do INSS não pode ser negativo.")
    private double descontoINSS;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O desconto do IR não pode ser negativo.")
    private double descontoIR;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O desconto do vale transporte não pode ser negativo.")
    private double descontoValeTransporte;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O desconto do vale alimentação não pode ser negativo.")
    private double descontoValeAlimentacao;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O valor do FGTS não pode ser negativo.")
    private double valorFGTS;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O total de descontos não pode ser negativo.")
    private double totalDescontos;
    @Pattern(regexp = "\\d+", message = "Digite apenas números.")
    @Min(value = 0, message = "O salário líquido não pode ser negativo.")
    private double salarioLiquido;

    private ConfiguracaoFolhaPag configuracaoFolhaPag;

    private Funcionario funcionario;

    private int faltasSemJustificativa;

    private double valorHorasExtra;

    private Cargo cargo;

    private double valorINSS;

    public FolhaPagFunc() {
    }

    public FolhaPagFunc(@NotNull(message = "O id da folha de pagamentos não pode ser nulo.") Integer id, int anoReferencia, int mesReferencia, @NotNull(message = "A data de pagamento não pode ser nula.") LocalDate dataPagamento, int horasTrabalhadas, int faltasSemJustificativas, double salarioBase, double valorHorasExtras, double totalProventos, double valorValeTransporte, double valorValeAlimentacao, double descontoINSS, double descontoIR, double descontoValeTransporte, double descontoValeAlimentacao, double valorFGTS, double totalDescontos, double salarioLiquido, ConfiguracaoFolhaPag configuracaoFolhaPag, Funcionario funcionario, int faltasSemJustificativa, double valorHorasExtra, Cargo cargo) {
        this.id = id;
        this.anoReferencia = anoReferencia;
        this.mesReferencia = mesReferencia;
        this.dataPagamento = dataPagamento;
        this.horasTrabalhadas = horasTrabalhadas;
        this.faltasSemJustificativas = faltasSemJustificativas;
        this.salarioBase = salarioBase;
        this.valorHorasExtras = valorHorasExtras;
        this.totalProventos = totalProventos;
        this.valorValeTransporte = valorValeTransporte;
        this.valorValeAlimentacao = valorValeAlimentacao;
        this.descontoINSS = descontoINSS;
        this.descontoIR = descontoIR;
        this.descontoValeTransporte = descontoValeTransporte;
        this.descontoValeAlimentacao = descontoValeAlimentacao;
        this.valorFGTS = valorFGTS;
        this.totalDescontos = totalDescontos;
        this.salarioLiquido = salarioLiquido;
        this.configuracaoFolhaPag = configuracaoFolhaPag;
        this.funcionario = funcionario;
        this.faltasSemJustificativa = faltasSemJustificativa;
        this.valorHorasExtra = valorHorasExtra;
        this.cargo = cargo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(int anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public int getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(int mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public int getFaltasSemJustificativas() {
        return faltasSemJustificativas;
    }

    public void setFaltasSemJustificativas(int faltasSemJustificativas) {
        this.faltasSemJustificativas = faltasSemJustificativas;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getValorHorasExtras() {
        return valorHorasExtras;
    }

    public void setValorHorasExtras(double valorHorasExtras) {
        this.valorHorasExtras = valorHorasExtras;
    }

    public double getTotalProventos() {
        return totalProventos;
    }

    public void setTotalProventos(double totalProventos) {
        this.totalProventos = totalProventos;
    }

    public double getValorValeTransporte() {
        return valorValeTransporte;
    }

    public void setValorValeTransporte(double valorValeTransporte) {
        this.valorValeTransporte = valorValeTransporte;
    }

    public double getValorValeAlimentacao() {
        return valorValeAlimentacao;
    }

    public void setValorValeAlimentacao(double valorValeAlimentacao) {
        this.valorValeAlimentacao = valorValeAlimentacao;
    }

    public double getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(double descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public double getDescontoIR() {
        return descontoIR;
    }

    public void setDescontoIR(double descontoIR) {
        this.descontoIR = descontoIR;
    }

    public double getDescontoValeTransporte() {
        return descontoValeTransporte;
    }

    public void setDescontoValeTransporte(double descontoValeTransporte) {
        this.descontoValeTransporte = descontoValeTransporte;
    }

    public double getDescontoValeAlimentacao() {
        return descontoValeAlimentacao;
    }

    public void setDescontoValeAlimentacao(double descontoValeAlimentacao) {
        this.descontoValeAlimentacao = descontoValeAlimentacao;
    }

    public double getValorFGTS() {
        return valorFGTS;
    }

    public void setValorFGTS(double valorFGTS) {
        this.valorFGTS = valorFGTS;
    }

    public double getTotalDescontos() {
        return totalDescontos;
    }

    public void setTotalDescontos(double totalDescontos) {
        this.totalDescontos = totalDescontos;
    }

    public double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public ConfiguracaoFolhaPag getConfiguracaoFolhaPag() {
        return configuracaoFolhaPag;
    }

    public void setConfiguracaoFolhaPag(ConfiguracaoFolhaPag configuracaoFolhaPag) {
        this.configuracaoFolhaPag = configuracaoFolhaPag;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getFaltasSemJustificativa() {
        return faltasSemJustificativa;
    }

    public void setFaltasSemJustificativa(int faltasSemJustificativa) {
        this.faltasSemJustificativa = faltasSemJustificativa;
    }

    public double getValorHorasExtra() {
        return valorHorasExtra;
    }

    public void setValorHorasExtra(double valorHorasExtra) {
        this.valorHorasExtra = valorHorasExtra;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getValorINSS() {
        return valorINSS;
    }

    public void setValorINSS(double valorINSS) {
        this.valorINSS = valorINSS;
    }
}
