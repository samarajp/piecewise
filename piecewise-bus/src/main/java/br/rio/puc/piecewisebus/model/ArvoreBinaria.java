package br.rio.puc.piecewisebus.model;

import java.util.List;

import br.rio.puc.piecewisebus.variance.TreeVariance;
import br.rio.puc.piecewisebus.dao.DAO;
import br.rio.puc.piecewisebus.estimator.TreeEstimator;

public class ArvoreBinaria {
    
	private No raiz;
    private ArvoreBinaria arvoreEsquerda;
    private ArvoreBinaria arvoreDireita;

    public ArvoreBinaria() { }

    public ArvoreBinaria getArvoreDireita() {
        return arvoreDireita;
    }

    public void setArvoreDireita(ArvoreBinaria arvoreDireita) {
        this.arvoreDireita = arvoreDireita;
    }

    public ArvoreBinaria getArvoreEsquerda() {
        return arvoreEsquerda;
    }

    public void setArvoreEsquerda(ArvoreBinaria arvoreEsquerda) {
        this.arvoreEsquerda = arvoreEsquerda;
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public void insereBusData(TreeEstimator tree_list) {
        No no = new No(tree_list);
        inserir(no);
    }

    public void inserir(No no) {
        if (this.raiz == null) {
        	this.raiz = no;
        } else {
            if (no.getElements().getMiddleTime() > this.raiz.getElements().getMiddleTime()) {
                if (this.arvoreDireita == null) {
                    this.arvoreDireita = new ArvoreBinaria();
                }
                this.arvoreDireita.inserir(no);
            } else if (no.getElements().getMiddleTime() < this.raiz.getElements().getMiddleTime()) {
                if (this.arvoreEsquerda == null) {
                    this.arvoreEsquerda = new ArvoreBinaria();
                }
                this.arvoreEsquerda.inserir(no);
            }
        }
    }

    public void percorrerInOrder() {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerInOrder();
        }

        System.out.println("\nMiddleTime: " + this.raiz.getElements().getMiddleTime());
        System.out.println("Function: " + this.raiz.getElements().getFunction());
        System.out.println("Media: " + this.raiz.getElements().getMedia());
        System.out.println("Standard Deviation: " + this.raiz.getElements().getStandardDeviation());
        
        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerInOrder();
        }
    }

    public void percorrerPreOrder() {
        if (this.raiz == null) {
           return;
        }

        System.out.println("\nMiddleTime: " + this.raiz.getElements().getMiddleTime());
        System.out.println("Function: " + this.raiz.getElements().getFunction());
        System.out.println("Media: " + this.raiz.getElements().getMedia());
        System.out.println("Standard Deviation: " + this.raiz.getElements().getStandardDeviation());
       
        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPreOrder();
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPreOrder();
        }
    }

    public void percorrerPostOrder() {
        if (this.raiz == null) {
           return;
        }

        if (this.arvoreEsquerda != null) {
            this.arvoreEsquerda.percorrerPostOrder();
        }

        if (this.arvoreDireita != null) {
            this.arvoreDireita.percorrerPostOrder();
        }

        System.out.println("\nMiddleTime: " + this.raiz.getElements().getMiddleTime());
        System.out.println("Function: " + this.raiz.getElements().getFunction());
        System.out.println("Media: " + this.raiz.getElements().getMedia());
        System.out.println("Standard Deviation: " + this.raiz.getElements().getStandardDeviation());
       
    }

    public TreeEstimator busca(double timestamp) {
        if (this.raiz == null) {
            return null;
        } else {
            if (timestamp == this.raiz.getElements().getMiddleTime()) {
                return this.raiz.getElements();
            } else {
                if (timestamp > this.raiz.getElements().getMiddleTime()) {
                    if (this.arvoreDireita == null) {
                        return null;
                    }
                    return this.arvoreDireita.busca(timestamp);
                } else {
                    if (this.arvoreEsquerda == null) {
                        return null;
                    }
                    return this.arvoreEsquerda.busca(timestamp);
                }
            }
        }
    }
       
}