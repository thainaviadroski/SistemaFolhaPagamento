package controller;

import dao.DaoFolhaPagFuncionario;
import modelo.FolhaPagFunc;
import utils.Input;
import utils.NumberUtils;
import utils.ValidacaoUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andre
 */
public class ControleFolhaPagFuncionario {
    private DaoFolhaPagFuncionario dao;
    private ControleFuncionario controleFuncionario;
    private ControleConfiguracaoFolhaPag controleConfiguracaoFolhaPag;
    private ValidacaoUtil validator = new ValidacaoUtil(FolhaPagFunc.class);


    public ControleFolhaPagFuncionario(DaoFolhaPagFuncionario dao) {
        this.dao = dao;
    }

    public ControleFolhaPagFuncionario() {
        dao = new DaoFolhaPagFuncionario();
        controleFuncionario = new ControleFuncionario();
        controleConfiguracaoFolhaPag = new ControleConfiguracaoFolhaPag();
    }

    public void cadastrar() {

        FolhaPagFunc folhaPag = new FolhaPagFunc();
        setarDados(folhaPag);
        try {
            if (validator.validarEntidade(folhaPag)) {
                dao.saveOrUpdate(folhaPag);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "salvar");
        }

    }

    public void editar() {
        FolhaPagFunc folhaPag = pesquisar();
        setarDados(folhaPag);
        try {
            if (validator.validarEntidade(folhaPag)) {
                dao.saveOrUpdate(folhaPag);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "editar");
        }

    }


    public void setarDados(FolhaPagFunc folha) {
        System.out.println("informe o ano de referência da folha de pagamento: ");
        folha.setAnoReferencia(Input.nextInt());
        System.out.println("informe o mês de referência da folha de pagamento: ");
        folha.setMesReferencia(Input.nextInt());
        System.out.println("configuração da folha de pagamento: ");
        folha.setConfiguracaoFolhaPag(controleConfiguracaoFolhaPag.pesquisar());
        System.out.println("informe a data de pagamento: ");
        folha.setDataPagamento(Input.nextLocalDate());
        System.out.println("funcionário: ");
        folha.setFuncionario(controleFuncionario.pesquisar());
        System.out.println("informe a quantidade de horas trabalhadas pelo funcionário no mês: ");
        folha.setHorasTrabalhadas(Input.nextInt());
        System.out.println("informe a quantidade de faltas sem justificativa do funcionário no mês: ");
        folha.setFaltasSemJustificativa(Input.nextInt());
    }


    public void calcularFolhadePagamento(FolhaPagFunc folha) {
        folha.setSalarioBase(folha.getFuncionario().getSalario());
        double valorHora = NumberUtils.arredondar(folha.getSalarioBase() / folha.getFuncionario().getCargo().getCargaHorariaMensal(), 2);
        double valorhorasFaltantes = 0;
        double valorDescontoFaltasSemJustificativa = 0;


        if (folha.getHorasTrabalhadas() > folha.getFuncionario().getCargo().getCargaHorariaMensal()) {

            double qtdhorasExtras = folha.getHorasTrabalhadas() - folha.getFuncionario().getCargo().getCargaHorariaMensal();

            double valorHorasExtras = (qtdhorasExtras * valorHora) * 1.5;
            folha.setValorHorasExtra(NumberUtils.arredondar(valorHorasExtras, 2));

        } else {
            int horasFaltasSemJustificativa = folha.getFaltasSemJustificativa() * 9;
            if ((folha.getHorasTrabalhadas() + horasFaltasSemJustificativa) < folha.getFuncionario().getCargo().getCargaHorariaMensal()) {
                double qtdhorasFaltantes = folha.getFuncionario().getCargo().getCargaHorariaMensal() - folha.getHorasTrabalhadas();
                valorhorasFaltantes = NumberUtils.arredondar((qtdhorasFaltantes * valorHora), 2);
            }
        }

        if (folha.getFaltasSemJustificativa() > 0) {
            double valorDia = NumberUtils.arredondar(folha.getSalarioBase() / 30, 2);
            valorDescontoFaltasSemJustificativa = NumberUtils.arredondar((folha.getFaltasSemJustificativa() * valorDia * 2), 2);
        }

        double totalProventos = (folha.getSalarioBase() - valorhorasFaltantes - valorDescontoFaltasSemJustificativa) + folha.getValorHorasExtra();
        totalProventos = NumberUtils.arredondar(totalProventos, 2);
        folha.setTotalProventos(totalProventos);

        double valorINSS = 0;
        if (totalProventos <= 1320) {
            valorINSS = totalProventos * 0.075;
        } else if (totalProventos < 2571.30) {
            valorINSS = (totalProventos * 0.09) - 19.80;
        } else if (totalProventos < 3856.95) {
            valorINSS = (totalProventos * 0.12) - 96.94;
        } else if (totalProventos >= 3856.95) {
            if (totalProventos > 7507.49) {
                valorINSS = (7507.49 * 0.14) - 174.08;
            } else {
                valorINSS = (totalProventos * 0.14) - 174.08;
            }
        }

        folha.setValorINSS(NumberUtils.arredondar(valorINSS, 2));

        double ValorBaseCalculoIR = NumberUtils.arredondar((totalProventos - folha.getValorINSS()), 2);
    }

    public void calcularImpostoRenda(FolhaPagFunc folha) {
        double valorBaseCalculoIR = NumberUtils.arredondar(folha.getTotalProventos() - folha.getValorINSS(), 2);
        double valorIR = 0;

        if (valorBaseCalculoIR <= 1903.98) {
            valorIR = 0;
        } else if (valorBaseCalculoIR <= 2826.65) {
            valorIR = valorBaseCalculoIR * 0.075 - 142.8;
        } else if (valorBaseCalculoIR <= 3751.05) {
            valorIR = valorBaseCalculoIR * 0.15 - 354.8;
        } else if (valorBaseCalculoIR <= 4664.68) {
            valorIR = valorBaseCalculoIR * 0.225 - 636.13;
        } else {
            valorIR = valorBaseCalculoIR * 0.275 - 869.36;
        }
        folha.setDescontoIR(NumberUtils.arredondar(valorIR, 2));
    }


    public FolhaPagFunc pesquisar() {
        System.out.println("informe o código da Folha de Pagamento do funcionário que deseja pesquisar: ");
        int codigo = Input.nextInt();
        return carregarPorId(codigo);
    }

    public FolhaPagFunc carregarPorId(int id) {
        return (FolhaPagFunc) dao.findById(id);
    }

    public List<FolhaPagFunc> carregarTodos() {
        return (List<FolhaPagFunc>) dao.findAll().stream().map(e -> (FolhaPagFunc) e).collect(Collectors.toList());
    }

    public void remover() {
        FolhaPagFunc folhaPag = pesquisar();
        if (folhaPag == null) {
            ValidacaoUtil.msgAviso("Cadastro não encontrado", "A Folha de Pagamento do funcionário não foi encontrada na base de dados!");
        }
        dao.delete(folhaPag);
    }
}
