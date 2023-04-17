import java.io.Serial;

public class Higiene extends Produto {

    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isDisponivel(){
        return true;
    }

    public Higiene(int codigo, String nome, String fornecedor, String marca){
        super(codigo, nome, fornecedor, marca);
        this.categoria = "Higiene";
        this.embalagem = "CAIXA";
        this.massa = "90G";
    }
}

