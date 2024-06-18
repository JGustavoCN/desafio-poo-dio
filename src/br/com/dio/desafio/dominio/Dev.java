package br.com.dio.desafio.dominio;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Dev {

    private static int QUANTIDADE_ID;
    private final int ID;
    private String nome;
    private final Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private final Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
    private final Set<Bootcamp> bootcampInscritos = new LinkedHashSet<>();
    private final Set<Bootcamp> bootcampConcluidos = new LinkedHashSet<>();

    public Dev() {
        ID = ++QUANTIDADE_ID;
    }

    public void inscreverBootcamp(Bootcamp bootcamp) {
        bootcampInscritos.add(bootcamp);
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void aprenderConteudo(Conteudo conteudo) {

        if (this.conteudosInscritos.contains(conteudo)) {
            this.conteudosConcluidos.add(conteudo);
            this.conteudosInscritos.remove(conteudo);
        } else {
            System.err.println("Você já aprendeu esse conteúdo!!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while (iterator.hasNext()) {
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public int getID() {
        return ID;
    }

    public Set<Bootcamp> getBootcampInscritos() {
        return bootcampInscritos;
    }

    public Set<Bootcamp> getBootcampConcluidos() {
        return bootcampConcluidos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.ID;
        hash = 23 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dev other = (Dev) obj;
        return this.ID == other.ID;
    }

    @Override
    public String toString() {
        return "Dev{" + "ID=" + ID + ", nome=" + nome + '}';
    }

}
