package modelo;

import annotations.DESCONTOINSS;
import jakarta.validation.Validation;
import modelo.FolhaPagFunc;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import utils.DateUtils;

import org.hibernate.validator.constraints.Range;
import org.junit.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import annotations.DESCONTOIR;
import validations.DescontoIRValidator;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FolhaPagFuncTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static FolhaPagFunc folhaPag;
    private static Set<ConstraintViolation<FolhaPagFunc>> violations;

    public FolhaPagFuncTest() {
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
        folhaPag = new FolhaPagFunc();
    }

    @After
    public void tearDown() {
    }

    private Set<ConstraintViolation<FolhaPagFunc>> getViolationsField(FolhaPagFunc fPag, String field) {
        Set<ConstraintViolation<FolhaPagFunc>> violationsModel = validator.validate(fPag);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

    @Test
    public void testAnoTamanho() {

        folhaPag.setAnoReferencia(190);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar 3 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(1910);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar 4 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(19000);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar 5 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testAnoValido() {

        folhaPag.setAnoReferencia(1999);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar ano valido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(2010);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar ano valido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(2020);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar ano valido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testAnoInvalido() {

        folhaPag.setAnoReferencia(1600);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar ano valido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(1710);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar ano valido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setAnoReferencia(1880);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar ano valido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarTamanhoMes() {

        folhaPag.setMesReferencia(1);
        violations = validator.validateProperty(folhaPag, "mesReferencia");
        assertFalse("validar 1 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(01);
        violations = validator.validateProperty(folhaPag, "mesReferencia");
        assertFalse("validar 2 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(111);
        violations = validator.validateProperty(folhaPag, "mesReferencia");
        assertTrue("validar 3 numeros @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }

    @Test
    public void testarMesValido() {

        folhaPag.setMesReferencia(01);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar mes valido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(06);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar mes valido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(12);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertFalse("validar mes valido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
    }

    @Test
    public void testarMesInvalido() {

        folhaPag.setMesReferencia(13);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar mes invalido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(133);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar mes invalido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setMesReferencia(1333);
        violations = validator.validateProperty(folhaPag, "anoReferencia");
        assertTrue("validar mes invalido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }


    @Test
    public void testarHorasZeradas() {
        //LocalTime horas = folhaPag.setHorasTrabalhadas(10:00);

    }

    @Test
    public void testarNumeroNegativoFaltas() {

        folhaPag.setFaltasSemJustificativas(-2);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar num negativo 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertTrue("validar num negativo 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(-40);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar num negativo 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertTrue("validar num negativo 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(-222);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar num negativo 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertTrue("validar num negativo 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

    }

    @Test
    public void testarFaltasValidas() {

        folhaPag.setFaltasSemJustificativas(0);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertFalse("validar faltas validas 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertFalse("validar faltas validas 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(15);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertFalse("validar faltas validas 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertFalse("validar faltas validas 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(30);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertFalse("validar faltas validas 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertFalse("validar faltas validas 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

    }

    @Test
    public void testarFaltasInvalidas() {

        folhaPag.setFaltasSemJustificativas(32);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar faltas invalidas 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertFalse("validar faltas invalidas 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(40);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar faltas invalidas 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertFalse("validar faltas invalidas 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setFaltasSemJustificativas(-10);
        violations = validator.validateProperty(folhaPag, "faltasSemJustificativas");
        assertTrue("validar faltas invalidas 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        assertTrue("validar faltas invalidas 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

    }

    @Test
    public void testarSalarioValido() {

        folhaPag.setSalarioBase(1500);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertFalse("validar salario valido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setSalarioBase(3000);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertFalse("validar salario valido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        folhaPag.setSalarioBase(6000);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertFalse("validar salario valido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));


    }

    @Test
    public void testarSalarioInvalido() {

        folhaPag.setSalarioBase(0);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario invalido 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setSalarioBase(500);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario invalido 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        folhaPag.setSalarioBase(1000);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario invalido 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));


    }

    @Test
    public void testarSalarioNegativo() {

        folhaPag.setSalarioBase(-100);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario negativo 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setSalarioBase(-1000);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario negativo 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        folhaPag.setSalarioBase(-10000);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario negativo 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }


    @Test
    public void testarSalarioComPontuacao() {

        folhaPag.setSalarioBase(1550.00);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertFalse("validar salario com pontuacao 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setSalarioBase(3001.00);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertFalse("validar salario com pontuacao 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));
        folhaPag.setSalarioBase(0.00);
        violations = validator.validateProperty(folhaPag, "salarioBase");
        assertTrue("validar salario com pontuacao 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));


    }

    @Test
    public void testarValorHorasExtrasValidos() {

        folhaPag.setValorHorasExtras(50);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertFalse("validar hora extra valida 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(50.50);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertFalse("validar hora extra valida 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(15);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertFalse("validar hora extra valida 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }

    @Test
    public void testarValorHorasExtrasInvalidos() {

        folhaPag.setValorHorasExtras(-10);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(4);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(4.5);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }

    @Test
    public void testarValorHorasExtrasNegativas() {

        folhaPag.setValorHorasExtras(-10);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 1 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(-20.5);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 2 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

        folhaPag.setValorHorasExtras(-0.05);
        violations = validator.validateProperty(folhaPag, "valorHorasExtras");
        assertTrue("validar hora extra invalida 3 @Range", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Range));

    }

    @Test
    public void testarValorValeAlimentacaoValido() {

        folhaPag.setValorValeAlimentacao(100);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(100.00);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(1000);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(0);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(0.00);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));


    }

    @Test
    public void testarValorValeAlimentacaoInvalido() {

        folhaPag.setValorValeAlimentacao(100);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(100.00);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeAlimentacao(1000);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale alimentacao valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));


    }

    @Test
    public void testarValorValeTransporteValido() {

        folhaPag.setValorValeTransporte(100);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(100.00);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(1000);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(0);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 4 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(0.00);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 5 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));


    }

    @Test
    public void testarValorValeTransporteInvalido() {

        folhaPag.setValorValeTransporte(100);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(100.00);
        violations = validator.validateProperty(folhaPag, "valorValeTransporte");
        assertFalse("validar vale transporte valido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));

        folhaPag.setValorValeTransporte(1000);
        violations = validator.validateProperty(folhaPag, "valorValeAlimentacao");
        assertFalse("validar vale transporte valido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof PositiveOrZero));


    }

    @Test
    public void testarDescontoINSSValido() {

        folhaPag.setDescontoINSS(7.5);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertFalse("validar desconto inss valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(9);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertFalse("validar desconto inss valido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(12);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertFalse("validar desconto inss valido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(14);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertFalse("validar desconto inss valido 4 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));
    }

    @Test
    public void testarDescontoINSSInvalido() {

        folhaPag.setDescontoINSS(10);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertTrue("validar desconto inss invalido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(11);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertTrue("validar desconto inss invalido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(12.5);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertTrue("validar desconto inss invalido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));

        folhaPag.setDescontoINSS(0);
        violations = validator.validateProperty(folhaPag, "descontoINSS");
        assertTrue("validar desconto inss invalido 4 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOINSS));
    }


    @Test
    public void testarDescontoIRValido() {

        folhaPag.setDescontoIR(0);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertFalse("validar desconto ir valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(7.5);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertFalse("validar desconto ir valido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(15);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertFalse("validar desconto ir valido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(22.5);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertFalse("validar desconto ir valido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(27.5);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertFalse("validar desconto ir valido 4 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));
    }

    @Test
    public void testarDescontoIRInvalido() {

        folhaPag.setDescontoIR(0.1);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertTrue("validar desconto ir invalido 1 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(9);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertTrue("validar desconto ir invalido 2 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(13);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertTrue("validar desconto ir invalido 3 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));

        folhaPag.setDescontoIR(17);
        violations = validator.validateProperty(folhaPag, "descontoIR");
        assertTrue("validar desconto ir invalido 4 @PositiveOrZero", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof DESCONTOIR));
    }
}