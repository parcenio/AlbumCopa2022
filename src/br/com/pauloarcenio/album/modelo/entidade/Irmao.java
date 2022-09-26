
package br.com.pauloarcenio.album.modelo.entidade;

public class Irmao {
    private byte id;
    private String nome, contato;

    public Irmao(String nome,String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public Irmao(byte id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    
    
    public byte getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getContato() {
        return contato;
    }
    
    
    
}
