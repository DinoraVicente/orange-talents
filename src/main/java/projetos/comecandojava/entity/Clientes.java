package projetos.comecandojava.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @Column(name = "cpf")
    private String cpf;
    private String nome;
    private String email;
    private String nascimento;

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNascimento(){
        return this.nascimento;
    }

    public void setNascimento(String nascimento){
        this.nascimento = nascimento;
    }

}
