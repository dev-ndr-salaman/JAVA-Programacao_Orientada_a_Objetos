import java.io.Serial;

public class Cerveja extends Produto {

    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isDisponivel(){
        return true;
    }

    public Cerveja(int codigo, String nome, String fornecedor, String marca){
        super(codigo, nome, fornecedor, marca);
        this.categoria = "Cerveja";
        this.embalagem = "LATA";
        this.volume = "350ML";
    }
}

