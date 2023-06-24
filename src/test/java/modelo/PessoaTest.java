package modelo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import modelo.Pessoa;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PessoaTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private static Pessoa pessoa;
    private static Set<ConstraintViolation<Pessoa>> violations;

    public PessoaTest() {
        validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        validator = validatorFactory.getValidator();
    }

    @BeforeAll
    public static void setUpClass() {

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
        Set<ConstraintViolation<Pessoa>> violationsModel = new HashSet<>();
        validator.validate(p);
        return violationsModel.stream().filter(e -> e.getPropertyPath().toString().equalsIgnoreCase(field)).collect(Collectors.toSet());
    }

    @Test
    public void testarNomeEmBranco() throws IOException, SAXException {
        pessoa.setNome(" ");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        //assertTrue("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome(null);
        violations = getViolationsField(pessoa, "nome");
//        assertTrue("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarQuantidadeCaracteresNome() throws IOException, SAXException {

        pessoa.setNome("ed");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar 2 caracteres  @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar 2 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
//        assertTrue("validar 2 caracteres @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("and");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar 3 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar 3 caracteres  @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar 3 caracteres  @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("AndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuizLuchesiAndreLuiz");
        System.out.println("size: " + pessoa.getNome().length());
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar mais de 90 caracteres @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar mais de 90 caracteres @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar mais de 90 caracteres @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
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
//        assertTrue("validar caracteres especiais @ndr& @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setNome("Ca$$!o");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar caracteres especiais Ca$$!o @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar caracteres especiais Ca$$!o @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar caracteres especiais Ca$$!o @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        //pessoa.setNome("!@#$%^&*()`~<>,.?/+={}[]|:;");
        pessoa.setNome("Thainã Vasconcelos");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar caracteres especiais @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar caracteres especiais @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar caracteres especiais @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }

    @Test
    public void testarNomeComNumeros() throws IOException, SAXException {
        pessoa.setNome("asdaasda 14");
        violations = getViolationsField(pessoa, "nome");
        assertFalse("validar nome com números @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar nome com números @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar nome com números @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
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


    @Test
    public void testarCPFEmBranco() throws IOException, SAXException {
        pessoa.setCpf("  ");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //assertTrue("validar @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("           ");
        violations = getViolationsField(pessoa, "cpf");
        assertTrue("validar 11 espaços em branco @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar 11 espaços em branco @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar 11 espaços em branco @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //assertTrue("validar 11 espaços em branco @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("");
        violations = getViolationsField(pessoa, "cpf");
        assertTrue("validar vazio @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertTrue("validar vazio@Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar vazio@Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        // assertFalse("validar vazio @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf(null);
        violations = getViolationsField(pessoa, "cpf");
        assertTrue("validar null @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar null @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar null @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertFalse("validar null @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

    }

    @Test
    public void testarCPFcomCaracteresInvalidos() throws IOException, SAXException {
        pessoa.setCpf("605.124.010-XX");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar Caracteres Inválidos 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar Caracteres Inválidos 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar Caracteres Inválidos 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //  assertTrue("validar Caracteres Inválidos 1 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("605.124.0!0-00");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar Caracteres Inválidos 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar Caracteres Inválidos 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar Caracteres Inválidos 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //     assertTrue("validar Caracteres Inválidos 2 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("605 124.010-00");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar Caracteres Inválidos 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar Caracteres Inválidos 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertTrue("validar Caracteres Inválidos 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertTrue("validar Caracteres Inválidos 3 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

    }

    @Test
    public void testarCPFInvalidos() throws IOException, SAXException {
        pessoa.setCpf("511.221.620-45");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar CPF Inválidos 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar CPF Inválidos 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar CPF Inválidos 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertTrue("validar CPF Inválidos 1 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("63685510389");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar CPF Inválidos 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar CPF Inválidos 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar CPF Inválidos 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertTrue("validar CPF Inválidos 2 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("92733477960");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar CPF Inválidos 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar CPF Inválidos 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar CPF Inválidos 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertTrue("validar CPF Inválidos 3 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

    }

    @Test
    public void testarCPFValidos() throws IOException, SAXException {
        pessoa.setCpf("511.221.620-49");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar cpf 1 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cpf 1 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cpf 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertFalse("validar cpf 1 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("388.379.620-45");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar cpf 2 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cpf 2 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cpf 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //     assertFalse("validar cpf 2 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("371025490-67");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar cpf 3 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cpf 3 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cpf 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertFalse("validar cpf 3 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

        pessoa.setCpf("587.859.930-93");
        violations = getViolationsField(pessoa, "cpf");
        assertFalse("validar cpf 4 @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));
        assertFalse("validar cpf 4 @Size", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Size));
        assertFalse("validar cpf 4 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
        //    assertFalse("validar cpf 4 @CPF", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof CPF));

    }

    @Test
    public void testarEmailEmBranco() throws IOException, SAXException {
        pessoa.setEmail("");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email vazio @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("     ");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email em branco @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail(null);
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email nulo @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

    }

    @Test
    public void testarEmailInvalido() throws IOException, SAXException {
        pessoa.setEmail("email@");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email inválido 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("joão@.gmail.com");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email inválido 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("email.com.br");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email inválido 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("*ana@gmail.com.br");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email inválido 4 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("bruno@a.com.xyz");
        violations = getViolationsField(pessoa, "email");
        assertTrue("validar email inválido 5 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarEmailValido() throws IOException, SAXException {
        pessoa.setEmail("eu123@univel.br");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email válido 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("joao-gomes@gmail.com.br");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email válido 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("1223_xxx@u.com");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email válido 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("a@g.com");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email válido 4 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setEmail("bruno.alve123@a.com.xy");
        violations = getViolationsField(pessoa, "email");
        assertFalse("validar email válido 5 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarTelefoneEmBranco() throws IOException, SAXException {
        pessoa.setTelefone("");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone vazio @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));

        pessoa.setTelefone("     ");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone em branco @NotBlank", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotBlank));

        pessoa.setTelefone(null);
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone nulo @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof NotNull));

    }

    @Test
    public void testarTelefoneInvalido() throws IOException, SAXException {
        pessoa.setTelefone("(000) 9999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertTrue("validar telefone inválido 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("(045) 29999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertTrue("validar telefone inválido 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("(045) 2999 1223");
        violations = getViolationsField(pessoa, "telefone");
        assertTrue("validar telefone inválido 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("(145)99999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertTrue("validar telefone inválido 4 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("x9999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertTrue("validar telefone inválido 5 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarTelefoneValido() throws IOException, SAXException {
        pessoa.setTelefone("4532231223");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone válido 1 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("(045) 99999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone válido 2 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("45 2999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone válido 3 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("(045)99999-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone válido 4 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));

        pessoa.setTelefone("2234-1223");
        violations = getViolationsField(pessoa, "telefone");
        assertFalse("validar telefone válido 5 @Pattern", violations.stream().anyMatch(v -> v.getConstraintDescriptor().getAnnotation() instanceof Pattern));
    }

    @Test
    public void testarLogin() throws IOException, SAXException {
        pessoa.setEmail("teste123@hotmail.com");
//        assertThat(pessoa.login("teste123@hotmail.com", "senha123"), );

        pessoa.setEmail("pessoa@hotmail.com");
        //assertThat(pessoa.login("pessoa@hotmail.com", "senha123"), );

        pessoa.setEmail("aaaaaaaa@hotmail.com");
        //assertThat(pessoa.login("a@hotmail.com", "senha123"), );
    }
}
