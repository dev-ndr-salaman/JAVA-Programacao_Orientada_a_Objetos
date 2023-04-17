import java.io.Serial;
import java.io.Serializable;

public abstract class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String nome;
    private String fornecedor;
    private String marca;
    protected String categoria;
    protected String embalagem;
    protected String volume;
    protected String massa;

    public Produto(int codigo, String nome, String fornecedor, String marca){
        this.codigo = codigo;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.marca = marca;
    }

    public String toString(){
        String retorno = "";
        String disponibilidade;
        if (isDisponivel()){
            disponibilidade = "SIM";
        }
        else{
            disponibilidade = "NÃO";
        }
        retorno += "Código: " + this.codigo + "\n";
        retorno += "Nome: " + this.nome + " \n";
        retorno += "Fornecedor: " + this.fornecedor + "\n";
        retorno += "Marca: " + this.marca + "\n";
        retorno += "Categoria: " + this.categoria + "\n";
        retorno += "Embalagem: " + this.embalagem + "\n";
        retorno += "Volume: " + this.volume + "\n";
        retorno += "Massa: " + this.massa + "\n";
        retorno += "Disponível: " + disponibilidade + "\n";
        return retorno;
    }

    public abstract boolean isDisponivel();
}
