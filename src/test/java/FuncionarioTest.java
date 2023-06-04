import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import jakarta.validation.Validation;
import modelo.Funcionario;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DateUtils;


public class FuncionarioTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Set<ConstraintViolation<Funcionario>> violations;
    private static Funcionario funcionario;

    public FuncionarioTest() {
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
        funcionario = new Funcionario();
    }

    @After
    public void tearDown() {
    }


    private Set<ConstraintViolation<Funcionario>> getViolationsField(Funcionario f, String field) {
        Set<ConstraintViolation<Funcionario>> violationsModel = validator.validate(f);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

    @Test
    public void testarCtpsValido() {

        funcionario.setCtps("65349191352");
        violations = getViolationsField(funcionario, "ctps");
        assertFalse("validar ctps correto 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar ctps correto 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        funcionario.setCtps("03177085174");
        violations = getViolationsField(funcionario, "ctps");
        assertFalse("validar ctps correto 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar ctps correto 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        funcionario.setCtps("42889382497");
        violations = getViolationsField(funcionario, "ctps");
        assertFalse("validar ctps correto 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar ctps correto 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));


    }

    @Test
    public void testarCtpsInvalido() {
        funcionario.setCtps("   ");
        violations = getViolationsField(funcionario, "ctps");
        assertTrue("validar ctps incorreto @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        funcionario.setCtps("123@@");
        violations = getViolationsField(funcionario, "ctps");
        assertFalse("validar ctps incorreto @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar ctps incorreto @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        funcionario.setCtps("123ABC456");
        violations = getViolationsField(funcionario, "ctps");
        assertFalse("validar ctps incorreto @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar ctps incorreto @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));


    }

    @Test
    public void testarDataAdmissaoValida() {
        LocalDate data;

        data = DateUtils.parseDate("11/11/2022");
        funcionario.setDataAdmissao(data);
        violations = getViolationsField(funcionario, "dataAdmissao");
        assertFalse("validar data de admissao incorreta 2 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de admissao incorreta 2 @PastOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PastOrPresent));

        data = DateUtils.parseDate("11/11/2020");
        funcionario.setDataAdmissao(data);
        violations = getViolationsField(funcionario, "dataAdmissao");
        assertFalse("validar data de admissao incorreta 2 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de admissao incorreta 2 @PastOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PastOrPresent));


    }

    @Test
    public void testarDataAdmissaoInvalida() {
        LocalDate data;

        funcionario.setDataAdmissao(null);
        violations = getViolationsField(funcionario, "dataAdmissao");
        assertTrue("validar data de admissao incorreta 1 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de admissao incorreta 1 @PastOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PastOrPresent));

        data = DateUtils.parseDate("11/11/2023");
        funcionario.setDataAdmissao(data);
        violations = getViolationsField(funcionario, "dataAdmissao");
        assertFalse("validar data de admissao correta 3 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar data de admissao correta 3 @PastOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PastOrPresent));

        data = DateUtils.parseDate("13/12/2025");
        funcionario.setDataAdmissao(data);
        violations = getViolationsField(funcionario, "dataAdmissao");
        assertFalse("validar data de admissao correta 3 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar data de admissao correta 3 @PastOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PastOrPresent));
    }


    @Test
    public void testarDataDemissaoValida() {
        LocalDate data;

        data = DateUtils.parseDate("02/08/2023");
        funcionario.setDataDemissao(data);
        violations = getViolationsField(funcionario, "dataDemissao");
        assertFalse("validar data de admissao correta 1 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de demissao correta 1 @FutureOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof FutureOrPresent));

        data = DateUtils.parseDate("30/06/2023");
        funcionario.setDataDemissao(data);
        violations = getViolationsField(funcionario, "dataDemissao");
        assertFalse("validar data de admissao correta 2 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de demissao correta 2@FutureOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof FutureOrPresent));

    }

    @Test
    public void testarDataDemissaoInvalida() {
        LocalDate data;

        funcionario.setDataDemissao(null);
        violations = getViolationsField(funcionario, "dataDemissao");
        assertTrue("validar data de admissao correta 2 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar data de demissao incorreta @FutureOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof FutureOrPresent));


        data = DateUtils.parseDate("02/02/2023");
        funcionario.setDataDemissao(data);
        violations = getViolationsField(funcionario, "dataDemissao");
        assertFalse("validar data de admissao correta 2 @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar data de demissao incorreta @FutureOrPresent", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof FutureOrPresent));
    }

    @Test
    public void testarSalario() {
        double salario;

        salario = 100.00;
        funcionario.setSalario(salario);
        violations = getViolationsField(funcionario, "Salario");
        assertFalse("validar salario correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar salario correto @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        salario = 0.00;
        funcionario.setSalario(salario);
        violations = getViolationsField(funcionario, "Salario");
        assertFalse("validar salario correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar salario correto @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        salario = 3000000.00;
        funcionario.setSalario(salario);
        violations = getViolationsField(funcionario, "Salario");
        assertFalse("validar salario incorreto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertTrue("validar salario incorreto @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarRecebeValeTransporte() {
        boolean recebeValeTransporte;

        recebeValeTransporte = true;
        funcionario.setRecebeValeTransporte(recebeValeTransporte);
        violations = getViolationsField(funcionario, "recebeValeTransporte");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));

        recebeValeTransporte = false;
        funcionario.setRecebeValeTransporte(recebeValeTransporte);
        violations = getViolationsField(funcionario, "recebeValeTransporte");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
    }

    @Test
    public void testarRecebeValeAlimentacao() {
        boolean recebeValeAlimentacao;

        recebeValeAlimentacao = true;
        funcionario.setRecebeValeTransporte(recebeValeAlimentacao);
        violations = getViolationsField(funcionario, "recebeValeTransporte");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));

        recebeValeAlimentacao = false;
        funcionario.setRecebeValeTransporte(recebeValeAlimentacao);
        violations = getViolationsField(funcionario, "recebeValeTransporte");
        assertFalse("validar recebeValeTransporte correto @NotNull", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
    }

    @Test
    public void testarNumeroDependentes() {
        int numeroDependentes;

        numeroDependentes = 19;
        funcionario.setNumeroDependentes(numeroDependentes);
        violations = getViolationsField(funcionario, "numeroDependentes");
        assertFalse("validar numeroDependentes correto @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        numeroDependentes = 0;
        funcionario.setNumeroDependentes(numeroDependentes);
        violations = getViolationsField(funcionario, "numeroDependentes");
        assertFalse("validar numeroDependentes correto @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        numeroDependentes = -1;
        funcionario.setNumeroDependentes(numeroDependentes);
        violations = getViolationsField(funcionario, "numeroDependentes");
        assertTrue("validar numeroDependentes correto @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));
    }


}