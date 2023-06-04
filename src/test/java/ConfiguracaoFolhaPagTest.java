import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.Validation;
import modelo.ConfiguracaoFolhaPag;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfiguracaoFolhaPagTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Set<ConstraintViolation<ConfiguracaoFolhaPag>> violations;
    private static ConfiguracaoFolhaPag configuracaoFolhaPag;

    public ConfiguracaoFolhaPagTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        configuracaoFolhaPag = new ConfiguracaoFolhaPag();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testarAnoVigencia() {
        int anoVigencia;

        anoVigencia = 2050;
        configuracaoFolhaPag.setAnoVigencia(anoVigencia);
        violations = getViolationsField(configuracaoFolhaPag, "anoVigencia");
        assertFalse("validar anoVigencia @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar anoVigencia @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        anoVigencia = 2001;
        configuracaoFolhaPag.setAnoVigencia(anoVigencia);
        violations = getViolationsField(configuracaoFolhaPag, "anoVigencia");
        assertFalse("validar anoVigencia @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar anoVigencia @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        anoVigencia = 2023;
        configuracaoFolhaPag.setAnoVigencia(anoVigencia);
        violations = getViolationsField(configuracaoFolhaPag, "anoVigencia");
        assertFalse("validar anoVigencia @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar anoVigencia @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarAtivo() {
        boolean ativo;

        ativo = true;
        configuracaoFolhaPag.setAtivo(ativo);
        violations = getViolationsField(configuracaoFolhaPag, "ativo");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));

        ativo = false;
        configuracaoFolhaPag.setAtivo(ativo);
        violations = getViolationsField(configuracaoFolhaPag, "ativo");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
    }

    @Test
    public void testarValorDiaValeAlimentacao() {
        double valorDiaValeAlimentacao;

        valorDiaValeAlimentacao = 150.00;
        configuracaoFolhaPag.setValorDiaValeAlimentacao(valorDiaValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeAlimentacao");
        assertFalse("validar valorDiaValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar valorDiaValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeAlimentacao = 50.00;
        configuracaoFolhaPag.setValorDiaValeAlimentacao(valorDiaValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeAlimentacao");
        assertFalse("validar valorDiaValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar valorDiaValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeAlimentacao = 100.00;
        configuracaoFolhaPag.setValorDiaValeAlimentacao(valorDiaValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeAlimentacao");
        assertFalse("validar valorDiaValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar valorDiaValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeAlimentacao = 0.00;
        configuracaoFolhaPag.setValorDiaValeAlimentacao(valorDiaValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeAlimentacao");
        assertFalse("validar valorDiaValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar valorDiaValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarValorDiaValeTransporte() {
        double valorDiaValeTransporte;

        valorDiaValeTransporte = 150.00;
        configuracaoFolhaPag.setValorDiaValeTransporte(valorDiaValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeTransporte");
        assertFalse("validar valorDiaValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar valorDiaValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeTransporte = 50.00;
        configuracaoFolhaPag.setValorDiaValeTransporte(valorDiaValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeTransporte");
        assertFalse("validar valorDiaValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar valorDiaValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeTransporte = 100.00;
        configuracaoFolhaPag.setValorDiaValeTransporte(valorDiaValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeTransporte");
        assertFalse("validar valorDiaValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar valorDiaValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        valorDiaValeTransporte = 0.00;
        configuracaoFolhaPag.setValorDiaValeTransporte(valorDiaValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "valorDiaValeTransporte");
        assertFalse("validar valorDiaValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar valorDiaValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarPercentualDescontoValeTransporte() {
        double percentualDescontoValeTransporte;

        percentualDescontoValeTransporte = 0;
        configuracaoFolhaPag.setPercentualDescontoValeTransporte(percentualDescontoValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeTransporte");
        assertFalse("validar percentualDescontoValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar percentualDescontoValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        percentualDescontoValeTransporte = 20;
        configuracaoFolhaPag.setPercentualDescontoValeTransporte(percentualDescontoValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeTransporte");
        assertFalse("validar percentualDescontoValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar percentualDescontoValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        percentualDescontoValeTransporte = 25;
        configuracaoFolhaPag.setPercentualDescontoValeTransporte(percentualDescontoValeTransporte);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeTransporte");
        assertFalse("validar percentualDescontoValeTransporte @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar percentualDescontoValeTransporte @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarPercentualDescontoValeAlimentacao() {
        double percentualDescontoValeAlimentacao;

        percentualDescontoValeAlimentacao = 0;
        configuracaoFolhaPag.setPercentualDescontoValeAlimentacao(percentualDescontoValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeAlimentacao");
        assertFalse("validar percentualDescontoValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar percentualDescontoValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        percentualDescontoValeAlimentacao = 6;
        configuracaoFolhaPag.setPercentualDescontoValeAlimentacao(percentualDescontoValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeAlimentacao");
        assertFalse("validar percentualDescontoValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar percentualDescontoValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        percentualDescontoValeAlimentacao = 20;
        configuracaoFolhaPag.setPercentualDescontoValeAlimentacao(percentualDescontoValeAlimentacao);
        violations = getViolationsField(configuracaoFolhaPag, "percentualDescontoValeAlimentacao");
        assertFalse("validar percentualDescontoValeAlimentacao @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar percentualDescontoValeAlimentacao @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    private Set<ConstraintViolation<ConfiguracaoFolhaPag>> getViolationsField(ConfiguracaoFolhaPag c, String field) {
        Set<ConstraintViolation<ConfiguracaoFolhaPag>> violationsModel = validator.validate(c);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

}