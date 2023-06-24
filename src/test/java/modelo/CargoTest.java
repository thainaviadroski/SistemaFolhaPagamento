package modelo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;
import modelo.Cargo;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CargoTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Cargo cargo;
    private static Set<ConstraintViolation<Cargo>> violations;

    public CargoTest() {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        validator = validatorFactory.getValidator();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        cargo = new Cargo();
    }

    @After
    public void tearDown() {
    }


    private Set<ConstraintViolation<Cargo>> getViolationsField(Cargo c, String field) throws IOException, SAXException {
        Set<ConstraintViolation<Cargo>> violationsModel = new HashSet<>();
        validator.validate(c);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }


    @Test
    public void testarDescricaoEmBranco() throws IOException, SAXException {
        cargo.setDescricao("    ");
        violations = getViolationsField(cargo, "descricao");
        assertTrue("validar @NotBlank 1", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

        cargo.setDescricao("");
        violations = getViolationsField(cargo, "descricao");
        assertFalse("validar @NotBlank 2", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotEmpty));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

        cargo.setDescricao(null);

        violations = getViolationsField(cargo, "descricao");
        assertTrue("validar @NotBlank 3", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

    }

    @Test
    public void testarTamanhoDescricao() throws IOException, SAXException {
        cargo.setDescricao("ed");
        violations = getViolationsField(cargo, "descricao");
        assertFalse("validar 2 caracteres  @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar 2 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));


        cargo.setDescricao("and");
        violations = getViolationsField(cargo, "descricao");
        assertFalse("validar 3 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar 3 caracteres  @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));


        cargo.setDescricao("DiretorDiretorDiretorDiretorDiretorDiretorDiretor");
        violations = getViolationsField(cargo, "descricao");
        assertFalse("validar mais de 90 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar mais de 90 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

    }


    @Test
    public void testCargaHorariaMensalValidation() throws IOException, SAXException {
        Cargo cargo = new Cargo();
        cargo.setCargaHorariaMensal(-10);

        violations = getViolationsField(cargo, "cargaHorariaMensal");

        cargo.setCargaHorariaMensal(-10);
        assertTrue("Deve haver uma violação de validação para a carga horária mensal negativa", validator.validate(cargo).size() > 0);

        cargo.setCargaHorariaMensal(0);
        assertFalse("Valor invalido", validator.validate(cargo).size() < 0);
        assertFalse("Valor invalido", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Max));
        assertFalse("Valor invalido", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Min));

        cargo.setCargaHorariaMensal(40);
        assertFalse("Valor invalido", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Max));
        assertFalse("Valor invalido", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Min));


    }

    @Test
    public void testDescricaoValidation() {
        Cargo cargo = new Cargo();
        cargo.setDescricao(null);
        assertEquals(false, validator.validate(cargo) == null, "Deve haver uma violação de validação para a descrição nula");

        cargo.setDescricao("");
        assertEquals(false, validator.validate(cargo).isEmpty(), "Deve haver uma violação de validação para a descrição vazia");

        cargo.setDescricao("Descrição do cargo");
        assertEquals(1, validator.validate(cargo).size(), "Não deve haver violações de validação para a descrição válida");
    }
}
