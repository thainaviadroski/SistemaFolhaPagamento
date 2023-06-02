public class Cargo {
    private Integer idCargo;
    private int cargaHorariaMensal;
    private String descricao;

    public Cargo() {
    }

    public Cargo(Integer idCargo, int cargaHorariaMensal, String descricao) {
        this.idCargo = idCargo;
        this.cargaHorariaMensal = cargaHorariaMensal;
        this.descricao = descricao;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public int getCargaHorariaMensal() {
        return cargaHorariaMensal;
    }

    public void setCargaHorariaMensal(int cargaHorariaMensal) {
        this.cargaHorariaMensal = cargaHorariaMensal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "idCargo=" + idCargo +
                ", cargaHorariaMensal=" + cargaHorariaMensal +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}