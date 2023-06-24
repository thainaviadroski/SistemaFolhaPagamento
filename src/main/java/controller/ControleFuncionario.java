package controller;

import dao.DaoFuncionario;
import modelo.Funcionario;
import modelo.Pessoa;
import utils.Input;
import utils.ValidacaoUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ControleFuncionario extends ControlePessoa {

    private DaoFuncionario dao;
    private ValidacaoUtil validator = new ValidacaoUtil(Funcionario.class);
    private ControleCargo controleCargo;

    public ControleFuncionario() {
        dao = new DaoFuncionario();
        controleCargo = new ControleCargo();
    }

    @Override
    public void cadastrar() {

        Funcionario p = new Funcionario();
        setarDados(p);
        try {
            if (validator.validarEntidade(p)) {
                controleEndereco.salvar(p.getEndereco());
                dao.saveOrUpdate(p);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "salvar");
        }

    }

    public void editar() {
        Funcionario p = pesquisar();
        setarDados(p);
        try {
            if (validator.validarEntidade(p)) {
                controleEndereco.salvar(p.getEndereco());
                dao.saveOrUpdate(p);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "editar");
        }

    }

    public void setarDados(Funcionario p) {
        super.setarDados(p);
        System.out.println("informe o CTPS: ");
        p.setCtps(Input.next());
        System.out.println("informe o Cargo: ");
        p.setCargo(controleCargo.pesquisarCargo());
        System.out.println("informe a data de Adimissão: ");
        p.setDataAdmissao(Input.nextLocalDate());

    }

    @Override
    public Funcionario pesquisar() {
        System.out.println("informe o código do funcionário: ");
        int codigo = Input.nextInt();
        return carregarPorId(codigo);
    }

    @Override
    public Funcionario carregarPorId(int id) {
        return (Funcionario) dao.findById(id);
    }

    @Override
    public List<Pessoa> carregarTodos() {
        return (List<Pessoa>) dao.findAll().stream().map(e -> {
            return (Funcionario) e;
        }).collect(Collectors.toList());
    }

    public void remover(Funcionario p) {
        dao.delete(p);
    }
}
