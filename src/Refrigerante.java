import java.io.Serial;

public class Refrigerante extends Produto {

    @Serial
    private static final long serialVersionUID = 1L;

    public boolean isDisponivel(){
        return true;
    }

    public Refrigerante(int codigo, String nome, String fornecedor, String marca){
        super(codigo, nome, fornecedor, marca);
        this.categoria = "Refrigerante";
        this.embalagem = "PET";
        this.volume = "2L";
    }
}


