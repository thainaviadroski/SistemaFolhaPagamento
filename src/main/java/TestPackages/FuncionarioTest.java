package TestPackages;



import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import modelo.FolhaPagFunc;
import modelo.Pessoa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class FuncionarioTest {

    private static ValidatorFactory factory;
    private static Validator validator;
    private static FolhaPagFunc folhaPag;
    private static Set<ConstraintViolation<Pessoa>> violations;

    public FuncionarioTest() {
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
        folhaPag = new FolhaPagFunc();
    }

    @After
    public void tearDown() {
    }



}
