package controle;

import java.util.List;
import java.util.stream.Collectors;
import modelo.FolhaPagFuncionario;
import persistencia.DaoFolhaPagFuncionario;
import util.Input;
import util.NumberUtils;
import util.validacoes.ValidacaoUtil;

/**
 *
 * @author Andre
 */
public class ControleFolhaPagFuncionario {
     private DaoFolhaPagFuncionario dao;
     private ControleFuncionario controleFuncionario;
     private ControleConfiguracaoFolhaPag controleConfiguracaoFolhaPag;
    private ValidacaoUtil validator = new ValidacaoUtil(FolhaPagFuncionario.class);

    public ControleFolhaPagFuncionario() {
        dao = new DaoFolhaPagFuncionario();
        controleFuncionario = new ControleFuncionario();
        controleConfiguracaoFolhaPag = new  ControleConfiguracaoFolhaPag();
    }

    public void cadastrar() {

        FolhaPagFuncionario folhaPag = new FolhaPagFuncionario();
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
        FolhaPagFuncionario folhaPag = pesquisar();
        setarDados(folhaPag);
        try {
            if (validator.validarEntidade(folhaPag)) {
                dao.saveOrUpdate(folhaPag);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "editar");
        }

    }
           

    public void setarDados(FolhaPagFuncionario folha) {
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

    /**
     * Função que realiza o cálculo de folha de pagamento para um determinado funcionário.
     * A folha de pagamento consiste nos seguintes cálculos:
     * Valor de horas extras:
     * Valor total de Proventos:
     * Valor vale-transporte:
     * Valor vale-alimentação:
     * Valor de desconto do vale-transporte:
     * Valor de desconto do vale-alimentação:
     * Valor de desconto INSS:
     * Valor de desconto IR:
     * Valor total de Descontos
     * Valor do sálario Líquido:
     * Valor do FGTS:
     * @param folha o parâmetro folha deverá conter as informações de configuração da folha de pagamento, ano e mês de referência,
     *              data prevista para pagamento, o funciánario, a quantidade de horas trabalhadas no mês e a quantidade de faltas sem justificativa.
     * @see
     * <a href="https://blog.genialinvestimentos.com.br/salario-liquido-e-bruto/"> como calcular salário líquido</a>
     * <a href="https://valorinveste.globo.com/ferramentas/calculadoras/calculadora-salario-liquido/">calculadora-salario-liquido</a>
     */
    public void calcularFolhadePagamento(FolhaPagFuncionario folha){
        //Implementar o método para calcular folha de pagamento.

        folha.setSalarioBase(folha.getFuncionario().getSalario());

        //Para calcular o valor da hora deve-se dividir o salário pela carga horária mensal ( salário/cargahorariaMensal)
        double valorHora = NumberUtils.arredondar(folha.getSalarioBase()/ folha.getFuncionario().getCargo().getCargaHorariaMensal(), 2);
        double valorhorasFaltantes = 0;
        double valorDescontoFaltasSemJustificativa = 0;

        //Verifica se quantidade de horas tabalhadas é maior que carga horária mensal, caso seja verdadeiro, o funcionário tem direito a horas extras
        if(folha.getHorasTrabalhadas() > folha.getFuncionario().getCargo().getCargaHorariaMensal()){
           // obter a quantidade de horas extras
            double qtdhorasExtras = folha.getHorasTrabalhadas() - folha.getFuncionario().getCargo().getCargaHorariaMensal();
            //calcular horas extras 50%
            double valorHorasExtras = (qtdhorasExtras * valorHora) * 1.5;
            folha.setValorHorasExtra(NumberUtils.arredondar(valorHorasExtras, 2));

        }else {
            // calcular às horas das faltas sem justificativa
            int horasFaltasSemJustificativa = folha.getFaltasSemJustificativa() * 9;

            //acrescentar as horas das faltas sem justificativas as horas trabalhadas, pois as faltas serão descontadas em outro cálculo mais adiante.
            if((folha.getHorasTrabalhadas() + horasFaltasSemJustificativa) < folha.getFuncionario().getCargo().getCargaHorariaMensal()){ // verifica se o funcinário tem horas faltantes
                // obter a quantidade de horas faltantes
                double qtdhorasFaltantes = folha.getFuncionario().getCargo().getCargaHorariaMensal() - folha.getHorasTrabalhadas();
                // calcula o valor de desconto das horas faltantes
                valorhorasFaltantes = NumberUtils.arredondar((qtdhorasFaltantes * valorHora) , 2);
            }
        }

        //Verifica se há faltas sem justificativas
        if(folha.getFaltasSemJustificativa() > 0){
            //calcula o valor do dia
            double valorDia = NumberUtils.arredondar(folha.getSalarioBase() / 30, 2);
            // calcula o valor de desconto das faltas, (mulutiplica os dias pelo valor do dia vezes 2, para descontar o dia que faltou e o DSR )
            valorDescontoFaltasSemJustificativa = NumberUtils.arredondar( (folha.getFaltasSemJustificativa() * valorDia * 2), 2);
        }

        //calcula o total de proventos
        double totalProventos = (folha.getSalarioBase() - valorhorasFaltantes - valorDescontoFaltasSemJustificativa) + folha.getValorHorasExtra() ;
        totalProventos = NumberUtils.arredondar(totalProventos, 2);
        folha.setTotalProventos(totalProventos);

        //calcular desconto do INSS
//      Salário de Contribuição (R$)	Alíquota (%)	Parcela a Deduzir
//      até R$ 1.320,00	                    7,5 %	            –
//      de R$ 1.320,01 até R$ 2.571,29	    9,0 %	        19,80
//      de R$ 2.571,30 até R$ 3.856,94	   12,0 %	        96,94
//      de R$ 3.856,95 até R$ 7.507,49	   14,0 %	        174,08

        double valorINSS = 0;
        if(totalProventos <= 1320) {
            valorINSS =  totalProventos * 0.075;
        }
        else if(totalProventos < 2571.30) {
            valorINSS = (totalProventos * 0.09) - 19.80;
        }
        else if(totalProventos < 3856.95) {
            valorINSS = (totalProventos * 0.12) - 96.94;
        }
        else if(totalProventos >= 3856.95) {
            if(totalProventos > 7507.49){ // verifica se o total de proventos é superior ao teto de contribuição
                valorINSS = (7507.49 * 0.14) - 174.08;
            } else {
                valorINSS = (totalProventos * 0.14) - 174.08;
            }
        }
        folha.setValorINSS(NumberUtils.arredondar(valorINSS, 2));

        double ValorBaseCalculoIR = NumberUtils.arredondar((totalProventos - folha.getValorINSS()), 2);
    }
    
    public FolhaPagFuncionario pesquisar(){
        System.out.println("informe o código da Folha de Pagamento do funcionário que deseja pesquisar: ");
        int codigo = Input.nextInt();
        return carregarPorId(codigo);
    }
    
    public FolhaPagFuncionario carregarPorId(int id) {
        return (FolhaPagFuncionario) dao.findById(id);
    }
    
    public List<FolhaPagFuncionario> carregarTodos() {
        return dao.findAll().stream().map(e -> (FolhaPagFuncionario) e).collect(Collectors.toList());
    }

    public void remover() {
        FolhaPagFuncionario folhaPag = pesquisar();
        if(folhaPag == null){
            ValidacaoUtil.msgAviso("Cadastro não encontrado", "A Folha de Pagamento do funcionário não foi encontrada na base de dados!");
        }
        dao.delete(folhaPag);
    }
}
