package model;

public class ClienteHasRequisito {
    private int valor;
    private int cliente_id;
    private int requisito_id;

    public ClienteHasRequisito(int valor, int idCliente, int idRequisito) {
        this.valor = valor;
        this.cliente_id = idCliente;
        this.requisito_id = idRequisito;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public int getRequisito_id() {
        return requisito_id;
    }
}
