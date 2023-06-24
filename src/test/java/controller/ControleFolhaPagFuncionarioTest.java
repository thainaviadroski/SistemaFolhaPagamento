package controller;

import modelo.Cargo;
import modelo.ConfiguracaoFolhaPag;
import modelo.FolhaPagFunc;
import modelo.Funcionario;
import org.junit.*;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControleFolhaPagFuncionarioTest {

    private ControleFolhaPagFuncionario controleFolha = new ControleFolhaPagFuncionario();
    private FolhaPagFunc folPagFuncionario;
    private FolhaPagFunc folPagFuncionarioTest;

    public ControleFolhaPagFuncionarioTest() {

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Cargo cargo = new Cargo(null, 220, "Vendedor");
        String ctps = "123456789";
        LocalDate dataAdmissao = LocalDate.of(2022, 1, 1);
        LocalDate dataDemissao = LocalDate.of(2023, 12, 31);
        double salario = 5000.0;
        boolean recebeValeTransporte = true;
        int numeroDependentes = 2;

        Funcionario funcionario = new Funcionario(cargo, ctps, dataAdmissao, dataDemissao, salario, recebeValeTransporte, numeroDependentes);

        AtomicReference<ConfiguracaoFolhaPag> configuracaoFolhaPag = new AtomicReference<>(new ConfiguracaoFolhaPag());
        configuracaoFolhaPag.get().setAnoVigencia(2023);
        configuracaoFolhaPag.get().setValorDiaValeAlimentacao(20.0);
        configuracaoFolhaPag.get().setValorDiaValeTransporte(10.0);
        configuracaoFolhaPag.get().setPercentualDescontoValeAlimentacao(0.1);
        configuracaoFolhaPag.get().setPercentualDescontoValeTransporte(0.05);

        Integer id = 1;
        int anoReferencia = 2023;
        int mesReferencia = 6;
        LocalDate dataPagamento = LocalDate.now();
        int horasTrabalhadas = 160;
        int faltasSemJustificativas = 2;
        double salarioBase = 5000.0;
        double valorHorasExtras = 200.0;
        double totalProventos = 5500.0;
        double valorValeTransporte = 100.0;
        double valorValeAlimentacao = 200.0;
        double descontoINSS = 450.0;
        double descontoIR = 250.0;
        double descontoValeTransporte = 50.0;
        double descontoValeAlimentacao = 100.0;
        double valorFGTS = 400.0;
        double totalDescontos = 850.0;
        double salarioLiquido = 4650.0;

        FolhaPagFunc folhaPagFunc = new FolhaPagFunc(id, anoReferencia, mesReferencia, dataPagamento, horasTrabalhadas, faltasSemJustificativas,
                salarioBase, valorHorasExtras, totalProventos, valorValeTransporte, valorValeAlimentacao, descontoINSS, descontoIR,
                descontoValeTransporte, descontoValeAlimentacao, valorFGTS, totalDescontos, salarioLiquido, configuracaoFolhaPag.get(),
                funcionario, faltasSemJustificativas, valorHorasExtras, cargo);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testeExemplo1() {

        folPagFuncionario.setFaltasSemJustificativa(1);
        folPagFuncionario.setHorasTrabalhadas(211);
        folPagFuncionario.setSalarioBase(2000);
        folPagFuncionario.setTotalProventos(1866.66);
        folPagFuncionario.setValorINSS(148.2);


        folPagFuncionarioTest.setFaltasSemJustificativa(1);
        folPagFuncionarioTest.setHorasTrabalhadas(211);

        controleFolha.calcularFolhadePagamento(folPagFuncionarioTest);

        System.out.println(folPagFuncionario);
        System.out.println(folPagFuncionarioTest);
        assertEquals(folPagFuncionario, folPagFuncionarioTest);
    }

    @Test
    public void testeExemplo2() {

        folPagFuncionario.setHorasTrabalhadas(230);
        folPagFuncionario.setSalarioBase(2000);
        folPagFuncionario.setTotalProventos(2136.35);
        folPagFuncionario.setValorHorasExtra(136.35);
        folPagFuncionario.setValorINSS(172.47);

        folPagFuncionarioTest.setHorasTrabalhadas(230);

        controleFolha.calcularFolhadePagamento(folPagFuncionarioTest);

        System.out.println(folPagFuncionario);
        System.out.println(folPagFuncionarioTest);
        assertEquals(folPagFuncionario, folPagFuncionarioTest);
    }
}
