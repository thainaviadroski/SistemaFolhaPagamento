package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
public class ConfiguracaoFolhaPag extends Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "O Id da configuracao da folha de pagamentos nao pode estar em branco")
    @NotNull(message = "O Id da configuracao da folha de pagamentos nao pode estar nulo")
    private Integer idConfiguracaoFolhaPag;
    @Min(value = 1900, message = "O ano de vigência deve ser igual ou maior que 1900")
    private int anoVigencia;

    private boolean ativo;

    @Min(value = 0, message = "O valor do vale alimentação por dia não pode ser negativo")
    private double valorDiaValeAlimentacao;

    @Min(value = 0, message = "O valor do vale transporte por dia não pode ser negativo")
    private double valorDiaValeTransporte;

    @Min(value = 0, message = "O percentual de desconto do vale transporte não pode ser negativo")
    @Max(value = 100, message = "O percentual de desconto do vale transporte não pode ser maior que 100")
    private double percentualDescontoValeTransporte;

    @Min(value = 0, message = "O percentual de desconto do vale alimentação não pode ser negativo")
    @Max(value = 100, message = "O percentual de desconto do vale alimentação não pode ser maior que 100")
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

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
