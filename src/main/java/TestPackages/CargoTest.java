
package TestPackages;


import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import modelo.Cargo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.validation.Validator;

import static org.junit.Assert.*;


public class CargoTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Cargo cargo;
    private static Set<ConstraintViolation<Cargo>> violations;

    public CargoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
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
        Set<ConstraintViolation<Cargo>> violationsModel = null;
        validator.validate(c);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

    @Test
    public void testarDescricaoEmBranco() throws IOException, SAXException {
        cargo.setDescricao("    ");
        violations = getViolationsField(cargo, "descricao");
        assertTrue("validar @NotBlank 1", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

        cargo.setDescricao("");
        violations = getViolationsField(cargo, "descricao");
        assertTrue("validar @NotBlank 2", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));

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


}
