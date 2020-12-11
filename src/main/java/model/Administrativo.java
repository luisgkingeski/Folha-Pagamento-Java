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
public class Administrativo extends Funcionario {

    public Administrativo(String nome, double salario, String sexo) {
        super(nome, salario, sexo);
    }

    @Override
    public double getSalarioFinal() {
        return getSalario();
    }

    @Override
    public double getExtras() {
        return 0;
    }

}
