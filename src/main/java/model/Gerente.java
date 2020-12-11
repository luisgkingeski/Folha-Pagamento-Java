/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Murie
 */
public class Gerente extends Funcionario {

    double bonificacao;

    public Gerente(String nome, double salario, String sexo, double bonificacao) {
        super(nome, salario, sexo);
        setBonificacao(bonificacao);
    }

    @Override
    public double getSalarioFinal() {
        return getBonificacao() + getSalario();
    }

    public double getBonificacao() {
        return bonificacao;
    }

    public double getExtras() {
        return getBonificacao();
    }

    public void setBonificacao(double bonificacao) {
        if (bonificacao >= 0) {
            this.bonificacao = bonificacao;
        } else {
            this.bonificacao = 0;
        }
    }
}
