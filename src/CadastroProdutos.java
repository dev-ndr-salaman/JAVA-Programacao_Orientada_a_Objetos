import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class CadastroProdutos {
    private ArrayList<Produto>produtos;

    public CadastroProdutos(){
        this.produtos = new ArrayList<Produto>();
    }
    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for(int i = 0;i<dadosIn.length;i++)
            dadosOut[i] = JOptionPane.showInputDialog("Entre com " + dadosIn[i] + ": ");

        return dadosOut;
    }

    public Refrigerante leRefrigerante(){
        String [] valores;
        String [] nomeVal = {"Código","Nome","Fonecedor","Marca"};
        valores = leValores(nomeVal);

        int codigo = this.retornaInteiro(valores[0]);
        Refrigerante refrigerante = new Refrigerante(codigo,valores[1],valores[2],valores[3]);
        return refrigerante;
    }

    public Cerveja leCerveja(){
        String [] valores = new String[6];
        String [] nomeVal = {"Código","Nome","Fonecedor","Marca"};
        valores = leValores(nomeVal);

        int codigo = this.retornaInteiro(valores[0]);
        Cerveja cerveja = new Cerveja(codigo,valores[1],valores[2],valores[3]);
        return cerveja;
    }

    public Chocolate leChocolate(){
        String [] valores = new String[6];
        String [] nomeVal = {"Código","Nome","Fonecedor","Marca"};
        valores = leValores(nomeVal);

        int codigo = this.retornaInteiro(valores[0]);
        Chocolate Chocolate = new Chocolate(codigo,valores[1],valores[2],valores[3]);
        return Chocolate;
    }

    public Higiene leHigiene(){
        String [] valores = new String[6];
        String [] nomeVal = {"Código","Nome","Fonecedor","Marca"};
        valores = leValores(nomeVal);

        int codigo = this.retornaInteiro(valores[0]);
        Higiene Higiene = new Higiene(codigo,valores[1],valores[2],valores[3]);
        return Higiene;
    }

    private boolean intValido(String s){
        try{
            Integer.parseInt(s); //Tenta transformar uma string em inteiro
            return true;
        }
        catch (NumberFormatException e){ //Se não consegue transformar, erro
            return false;
        }
    }
    public int retornaInteiro(String entrada){ //retorna um valor inteiro

        //Tenta converter o valor de entrada para inteiro, senão permanece no loop
        while(!this.intValido(entrada)){
            entrada = JOptionPane.showInputDialog(null,"Valor incorreto.\nDigite um número inteiro para o código de produto:");
        }
        return Integer.parseInt(entrada);
    }

    public void salvaProdutos(ArrayList<Produto>produtos){
        ObjectOutputStream outputStream = null;
        try{
            outputStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\andre\\OneDrive\\_OLD\\Documents\\GitHub\\POO-ATP\\src\\CadastroProdutos.dados"));
            for(int i = 0; i <produtos.size();i++)
                outputStream.writeObject(produtos.get(i));
        }
        catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo.");
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally { //fecha arquivo objectoutputstream
            try{
                if (outputStream != null){
                    outputStream.flush();
                    outputStream.close();
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
    @SuppressWarnings("finally")

    public ArrayList<Produto> recuperaProdutos (){
        ArrayList<Produto>produtosTemp = new ArrayList<Produto>();

        ObjectInputStream inputStream = null;

        try{
            inputStream = new ObjectInputStream(new FileInputStream("C:\\Users\\andre\\OneDrive\\_OLD\\Documents\\GitHub\\POO-ATP\\src\\CadastroProdutos.dados"));
            Object obj = null;
            while((obj = inputStream.readObject()) != null){
                if(obj instanceof Produto){
                    produtosTemp.add((Produto) obj);
                }
            }
        }
        catch (EOFException ex){ //quando EOF é alcancado
            System.out.println("Fim de arquivo.");
        }
        catch (ClassNotFoundException ex){ //quando EOF é alcançado
            ex.printStackTrace();
        }
        catch (FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Arquivo com produtos não existe.");
            ex.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally { //fecha arquivo objectinputstream
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }
            catch(final IOException ex){
                ex.printStackTrace();
            }
            return produtosTemp;
        }
    }

    public void menuCadastroProdutos(){
        String menu = "";
        String entrada;
        int opc1, opc2;
        do{
            menu = "-- CENTRAL DE CONTROLE --\n" +
                    "- CADASTRO DE PRODUTOS INDÚSTRIA -\n\n" +
                    "Selecione uma opção: \n\n" +
                    "1. Cadastrar um novo produto\n" +
                    "2. Exibir produtos cadastrados\n" +
                    "3. Limpar produtos cadastrados\n" +
                    "4. Salvar produtos \n" +
                    "5. Recuperar produtos salvos\n\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog(menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1){

                case 1: //Entrar dados
                    menu = "CADASTRO DE PRODUTOS\n\n" +
                            "Selecione uma opção: \n\n" +
                            "1. Refrigerante\n" +
                            "2. Cerveja\n" +
                            "3. Higiene\n" +
                            "4. Chocolate\n";

                    entrada = JOptionPane.showInputDialog(menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2){
                        case 1:produtos.add((Produto)leRefrigerante());
                            break;
                        case 2:produtos.add((Produto)leCerveja());
                            break;
                        case 3:produtos.add((Produto)leHigiene());
                            break;
                        case 4:produtos.add((Produto)leChocolate());
                            break;

                        default:
                            JOptionPane.showMessageDialog(null,"Entrada não válida.");
                    }
                    break;

                case 2: //Exibir dados
                    if(produtos.size()==0){
                        JOptionPane.showMessageDialog(null,"Insira um produto: ");
                        break;
                    }
                    String dados = "";

                    for(int i=0; i<produtos.size();i++){
                        dados +=produtos.get(i).toString() + "---------------\n";
                    }
                    JOptionPane.showMessageDialog(null, dados);
                    break;

                case 3: //Limpar dados
                    if (produtos.size()==0){
                        JOptionPane.showMessageDialog(null,"Insira um produto: ");
                        break;
                    }
                    produtos.clear();
                    JOptionPane.showMessageDialog(null,"Dados limpos com sucesso.");
                    break;

                case 4: //Grava dados
                    if(produtos.size()==0){
                        JOptionPane.showMessageDialog(null,"Insira um produto: ");
                        break;
                    }
                    salvaProdutos(produtos);
                    JOptionPane.showMessageDialog(null,"Dados salvos com sucesso.");
                    break;

                case 5: //Recupera Dados
                    produtos = recuperaProdutos();
                    if (produtos.size()==0){
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar");
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Dados recuperados com sucesso.");
                    break;

                case 9:
                    JOptionPane.showMessageDialog(null,"Aplicação encerrada pelo usuário.");
                    break;
            }
        }
        while (opc1 != 9);
    }

    public static void main(String[] args) {
        CadastroProdutos pet = new CadastroProdutos();
        pet.menuCadastroProdutos();
    }
}