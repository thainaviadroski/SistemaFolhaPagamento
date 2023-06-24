package controller;

import dao.DaoCargo;
import modelo.Cargo;
import utils.Input;
import utils.ValidacaoUtil;


import java.util.List;

public class ControleCargo {
    private DaoCargo dao;
    private ValidacaoUtil validator = new ValidacaoUtil(Cargo.class);

    public ControleCargo() {
        dao = new DaoCargo();
    }

    public void cadastrar() {
        Cargo cargo = new Cargo();
        setDados(cargo);
        try {
            if (validator.validarEntidade(cargo)) {
                boolean b = dao.saveOrUpdate(cargo);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "salvar");
        }
    }

    public void editar() {
        Cargo cargo = pesquisarCargo();
        setDados(cargo);
        try {
            if (validator.validarEntidade(cargo)) {
                dao.saveOrUpdate(cargo);
            }
        } catch (Exception e) {
            validator.msgErroCadastro(e, "editar");
        }
    }

    public void setDados(Cargo cargo) {
        System.out.println("Informe a descrição do cargo: ");
        cargo.setDescricao(Input.next());
        System.out.println("Informe a carga horária mensal: ");
        cargo.setCargaHorariaMensal(Input.nextInt());
    }

    public Cargo pesquisarCargo() {
        System.out.println("Informe o código do cargo que deseja pesquisar: ");
        int codigo = Input.nextInt();
        return carregarPorId(codigo);
    }

    public Cargo carregarPorId(int id) {
        return (Cargo) dao.findById(id);
    }

    public List<Cargo> carregarTodos() {
        return dao.findAll();
    }

    public void remover() {
        Cargo cargo = pesquisarCargo();
        if (cargo == null) {
            validator.msgAviso("Cadastro não encontrado", "O cargo não foi localizado no banco de dados!");
        }
        dao.delete(cargo);
    }
}
