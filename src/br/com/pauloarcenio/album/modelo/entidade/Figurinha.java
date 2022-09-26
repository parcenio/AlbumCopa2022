
package br.com.pauloarcenio.album.modelo.entidade;


public class Figurinha {
    
    private final int numero;
    private final byte pagina;
    private String descricao;
    private int qtde;

    public Figurinha(int numero, byte pagina) {
        this.numero = numero;
        this.pagina = pagina;
    }
    
    
    public Figurinha(int numero, byte pagina, String descricao, int qtde) {
        this.numero = numero;
        this.pagina = pagina;
        this.descricao = descricao;
        this.qtde = qtde;
    }

    
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQtde(int qtde) {
        if (qtde >=0){
            this.qtde = qtde;
        }
    }

    
    
    public int getNumero() {
        return numero;
    }

    public byte getPagina() {
        return pagina;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQtde() {
        return qtde;
    }
    
    
}
