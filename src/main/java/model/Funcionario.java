/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Murie
 *
 */
public abstract class Funcionario {

    /**
     * @var String nome: Nome do funcionario
     * @var double salario: Salario do funcionario
     * @var String sexo: Pode ser F ou M
     */
    String nome;
    double salario;
    String sexo;

    public abstract double getSalarioFinal();

    public abstract double getExtras();

    public String getTipo() {
        return this.getClass().getSimpleName();
    }

    public Funcionario(String nome, double salario, String sexo) {
        this.nome = nome;
        setSalario(salario);
        setSexo(sexo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario >= 954.00) {
            this.salario = salario;
        } else {
            this.salario = 0;
        }
    }

    public String getSexo() {
        if (sexo.equals("M")) {
            return "Masculino";
        } else if (sexo.equals("F")) {
            return "Feminino";
        } else {
            return "Não informado";
        }

    }

    public void setSexo(String sexo) {
        if (!sexo.equals("")) {
            this.sexo = sexo.toUpperCase();
        }
    }

}
