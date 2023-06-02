package modelo;

public class ConfiguracaoFolhaPag {
    private Integer idConfiguracaoFolhaPag;
    private int anoVigencia;
    private boolean ativo;
    private double valorDiaValeAlimentacao;
    private double valorDiaValeTransporte;
    private double percentualDescontoValeTransporte;
    private double percentualDescontoValeAlimentacao;

    public ConfiguracaoFolhaPag() {
    }

    public ConfiguracaoFolhaPag(Integer idConfiguracaoFolhaPag, int anoVigencia, boolean ativo, double valorDiaValeAlimentacao,
                                double valorDiaValeTransporte, double percentualDescontoValeTransporte,
                                double percentualDescontoValeAlimentacao) {
        this.idConfiguracaoFolhaPag = idConfiguracaoFolhaPag;
        this.anoVigencia = anoVigencia;
        this.ativo = ativo;
        this.valorDiaValeAlimentacao = valorDiaValeAlimentacao;
        this.valorDiaValeTransporte = valorDiaValeTransporte;
        this.percentualDescontoValeTransporte = percentualDescontoValeTransporte;
        this.percentualDescontoValeAlimentacao = percentualDescontoValeAlimentacao;
    }

    public Integer getIdConfiguracaoFolhaPag() {
        return idConfiguracaoFolhaPag;
    }

    public void setIdConfiguracaoFolhaPag(Integer idConfiguracaoFolhaPag) {
        this.idConfiguracaoFolhaPag = idConfiguracaoFolhaPag;
    }

    public int getAnoVigencia() {
        return anoVigencia;
    }

    public void setAnoVigencia(int anoVigencia) {
        this.anoVigencia = anoVigencia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public double getValorDiaValeAlimentacao() {
        return valorDiaValeAlimentacao;
    }

    public void setValorDiaValeAlimentacao(double valorDiaValeAlimentacao) {
        this.valorDiaValeAlimentacao = valorDiaValeAlimentacao;
    }

    public double getValorDiaValeTransporte() {
        return valorDiaValeTransporte;
    }

    public void setValorDiaValeTransporte(double valorDiaValeTransporte) {
        this.valorDiaValeTransporte = valorDiaValeTransporte;
    }

    public double getPercentualDescontoValeTransporte() {
        return percentualDescontoValeTransporte;
    }

    public void setPercentualDescontoValeTransporte(double percentualDescontoValeTransporte) {
        this.percentualDescontoValeTransporte = percentualDescontoValeTransporte;
    }

    public double getPercentualDescontoValeAlimentacao() {
        return percentualDescontoValeAlimentacao;
    }

    public void setPercentualDescontoValeAlimentacao(double percentualDescontoValeAlimentacao) {
        this.percentualDescontoValeAlimentacao = percentualDescontoValeAlimentacao;
    }
}
