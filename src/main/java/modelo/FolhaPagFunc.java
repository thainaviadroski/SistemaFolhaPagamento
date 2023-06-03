package modelo;

import java.time.LocalDate;

public class FolhaPagFunc {
    private Integer idFolhaPagFunc;
    private int anoReferencia;
    private int mesReferencia;
    private LocalDate dataPagamento;
    private int horasTrabalhadas;
    private int faltasSemJustificativas;
    private double salarioBase;
    private double valorHorasExtras;
    private double totalProventos;
    private double valorValeTransporte;
    private double valorValeAlimentacao;
    private double descontoINSS;
    private double descontoIR;
    private double descontoValeTransporte;
    private double descontoValeAlimentacao;
    private double valorFGTS;
    private double totalDescontos;
    private double salarioLiquido;

    public FolhaPagFunc() {
    }

    public FolhaPagFunc(Integer idFolhaPagFunc, int anoReferencia, int mesReferencia, LocalDate dataPagamento, int horasTrabalhadas,
                        int faltasSemJustificativas, double salarioBase, double valorHorasExtras, double totalProventos,
                        double valorValeTransporte, double valorValeAlimentacao, double descontoINSS, double descontoIR,
                        double descontoValeTransporte, double descontoValeAlimentacao, double valorFGTS,
                        double totalDescontos, double salarioLiquido) {
        this.idFolhaPagFunc = idFolhaPagFunc;
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
    }

    public Integer getIdFolhaPagFunc() {
        return idFolhaPagFunc;
    }

    public void setIdFolhaPagFunc(Integer idFolhaPagFunc) {
        this.idFolhaPagFunc = idFolhaPagFunc;
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
}
