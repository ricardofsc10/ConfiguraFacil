package Business.Configur;

public class Cliente {
    private String nome;
    private String eMail;
    private String morada;
    private float pagamento;
    private String nif;

    public Cliente() {
        this.nome = "";
        this.eMail = "";
        this.morada = "";
        this.pagamento = (float) 0.0;
        this.nif = "";
    }

    public Cliente(String nome, String eMail, String morada, float pagamento, String nif) {
        this.nome = nome;
        this.eMail = eMail;
        this.morada = morada;
        this.pagamento = pagamento;
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public float getPagamento() {
        return pagamento;
    }

    public void setPagamento(float pagamento) {
        this.pagamento = pagamento;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }
}
