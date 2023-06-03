package TestPackages;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import modelo.Pessoa;
import org.junit.*;
import jakarta.validation.constraints.NotBlank;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;

public class PessoaTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static Pessoa pessoa;
    private static Set<ConstraintViolation<Pessoa>> violations;

    public PessoaTest() {
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
        pessoa = new Pessoa();
    }

    @After
    public void tearDown() {
    }

    private Set<ConstraintViolation<Pessoa>> getViolationsField(Pessoa p, String field) throws IOException, SAXException {
        Set<ConstraintViolation<Pessoa>> violationsModel = null;
        validator.validate(p);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

    @Test
    public void testarNomeEmBranco() throws IOException, SAXException {
        pessoa.setNome("    ");
        violations = getViolationsField(pessoa, "nome");
        assertTrue("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("");
        violations = getViolationsField(pessoa, "nome");
        assertTrue("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome(null);
        violations = getViolationsField(pessoa, "nome");
        assertTrue("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarQuantidadeCaracteresNome() throws IOException, SAXException {

        pessoa.setNome("ed");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar 2 caracteres  @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar 2 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar 2 caracteres @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("and");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar 3 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar 3 caracteres  @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar 3 caracteres  @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("AndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuizLu");
        System.out.println("size: " + pessoa.getNome().length());
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar mais de 90 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar mais de 90 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar mais de 90 caracteres @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarAcentuaçõesECaracteresEspeciais() throws IOException, SAXException {
        pessoa.setNome("áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar acentuções válidas @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar acentuções válidas @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar acentuções válidas @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Felipe D'Avila");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com acentos apóstrofo @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com acentos apóstrofo @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome com acentos apóstrofo @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Brian O'Connor");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com acento apóstrofo @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com acento apóstrofo @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome com acento apóstrofo @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Dom. João VI");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com ponto @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com ponto @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome com ponto @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("@ndr&");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar caracteres especiais @ndr& @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar caracteres especiais @ndr& @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar caracteres especiais @ndr& @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Ca$$!o");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar caracteres especiais Ca$$!o @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar caracteres especiais Ca$$!o @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar caracteres especiais Ca$$!o @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("!@#$%^&*()`~<>,.?/+={}[]|:;");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar caracteres especiais @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar caracteres especiais @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar caracteres especiais @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }

    @Test
    public void testarNomeComNumeros() throws IOException, SAXException {
        pessoa.setNome("asdaasda 14");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com números @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com números @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar nome com números @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("djsifdsf 6");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com o número 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com o número 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar nome com o número 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("666");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome 666 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome 666 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar nome 666 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }

    @Test
    public void testarNomeValido() throws IOException, SAXException {
        pessoa.setNome("Elisson Deveque");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome 1 válidas @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome 1 válidas @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Zé Eduardo Conceição Junior");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertEquals("validar nome 2 @Pattern", false, violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Daniel Molo");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Lucas Marques");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com 90 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com 90 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome com 90 caracteres @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }
}
