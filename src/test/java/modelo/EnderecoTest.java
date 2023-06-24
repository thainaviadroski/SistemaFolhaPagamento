package modelo;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import modelo.Endereco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnderecoTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Set<ConstraintViolation<Endereco>> violations;
    private static Endereco endereco;

    public EnderecoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        endereco = new Endereco();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testarCidadeValida() {
        String cidade;

        cidade = "cascavel";
        endereco.setCidade(cidade);
        violations = getViolationsField(endereco, "cidade");
        assertFalse("validar cidade 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cidade 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cidade 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        cidade = "Casca vel";
        endereco.setCidade(cidade);
        violations = getViolationsField(endereco, "cidade");
        assertFalse("validar cidade 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cidade 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cidade 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }

    @Test
    public void testarCidadeInvalida() {
        String cidade;

        cidade = "cascavel 123";
        endereco.setCidade(cidade);
        violations = getViolationsField(endereco, "cidade");
        assertFalse("validar cidade 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cidade 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar cidade 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        cidade = "c@sc@v3l";
        endereco.setCidade(cidade);
        violations = getViolationsField(endereco, "cidade");
        assertFalse("validar cidade 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cidade 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar cidade 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        cidade = "";
        endereco.setCidade(cidade);
        violations = getViolationsField(endereco, "cidade");
        assertTrue("validar cidade 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar cidade 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar cidade 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));


    }


    @Test
    public void testarBairro() {
        String bairro;

        bairro = "centro";
        endereco.setBairro(bairro);
        violations = getViolationsField(endereco, "bairro");
        assertFalse("validar bairro 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar bairro 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

        bairro = "";
        endereco.setBairro(bairro);
        violations = getViolationsField(endereco, "bairro");
        assertTrue("validar bairro 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar bairro 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
    }

    @Test
    public void testarRua() {
        String rua;

        rua = "avenida assuncao";
        endereco.setRua(rua);
        violations = getViolationsField(endereco, "rua");
        assertFalse("validar rua 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar rua 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

        rua = "";
        endereco.setRua(rua);
        violations = getViolationsField(endereco, "rua");
        assertTrue("validar rua 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar rua 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
    }

    @Test
    public void testarNumeroValido() {
        String numero;

        numero = "123";
        endereco.setNumero(numero);
        violations = getViolationsField(endereco, "numero");
        assertFalse("validar numero 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar numero 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar numero 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        numero = "456";
        endereco.setNumero(numero);
        violations = getViolationsField(endereco, "numero");
        assertFalse("validar numero 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar numero 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar numero 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));


    }

    @Test
    public void testarNumeroInvalido() {
        String numero;

        numero = "";
        endereco.setNumero(numero);
        violations = getViolationsField(endereco, "numero");
        assertTrue("validar numero 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar numero 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar numero 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        numero = "casa 123";
        endereco.setNumero(numero);
        violations = getViolationsField(endereco, "numero");
        assertFalse("validar numero 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar numero 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar numero 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        numero = "123$$";
        endereco.setNumero(numero);
        violations = getViolationsField(endereco, "numero");
        assertFalse("validar numero 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar numero 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar numero 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    private Set<ConstraintViolation<Endereco>> getViolationsField(Endereco end, String field) {
        Set<ConstraintViolation<Endereco>> violationsModel = validator.validate(end);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }
}