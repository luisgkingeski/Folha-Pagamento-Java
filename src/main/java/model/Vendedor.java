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
public class Vendedor extends Funcionario {

    double vendas;
    static double comissao = 0.04;

    /**
     *
     * @param nome Nome do vendedor
     * @param salario Salario do vendedor >= 954.00
     * @param sexo Sexo do vendedor (F ou M)
     * @param vendas > 0
     */
    public Vendedor(String nome, double salario, String sexo, double vendas) {
        super(nome, salario, sexo);
        setVendas(vendas);
    }

    public double getVendas() {
        return vendas;
    }

    private void setVendas(double vendas) {
        if (vendas >= 0) {
            this.vendas = vendas;
        } else {
            this.vendas = 0;
        }
    }

    public double getExtras() {
        return (getVendas() * this.comissao);
    }

    @Override
    public double getSalarioFinal() {
        return (getVendas() * this.comissao) + getSalario();
    }
}
