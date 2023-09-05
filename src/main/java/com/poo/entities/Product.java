package com.poo.entities;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;

//ENTIDADE
@Entity
public class Product {

    //VALORES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    float value;
    int amount;

    Scanner sc = new Scanner(System.in);

    public Product() {
    }

    @Autowired
    ProductInterface product; //INTERFACE PARA MANIPULAÇÃO DE ENTIDADE
    ArrayList<Product> productList = new ArrayList<>(); //LISTA DE PRODUTO GLOBAL
    String select; //VARIAVEL DE CONTROLE DE ESTADO


    //METODO DE ADIÇÃO DE PRODUTO
    public Product addProduct() {

        //INTANCIA NOVA DO PRODUTO
        Product newProduct = new Product();

        System.out.println("");
        System.out.println("Qual o nome do produto?: ");
        newProduct.setName(sc.next());
        System.out.println("Adicione a quantidade");

        newProduct.setAmount(sc.nextInt());
        System.out.println("Adicione o valor como informado($.$)");
        newProduct.setValue(sc.nextFloat());

        System.out.println("Nome: " + newProduct.name);
        System.out.println("Valor: " + newProduct.value);
        System.out.println("Quantidade: " + newProduct.amount);
        System.out.println("Gostaria de salvar?");
        System.out.println("1: Sim || 2: Não");
        select = sc.next();
        //VALIDACAO DA INSERCAO DE DADOS DO PRODUTO **EM CONSTRUCAO**
        //AINDA ESTOU TENDO UM POUCO DE DIFICULDADE NA CONSTRUCAO DO METODO SEM UM FRAMEWORK ^^
        if (select != "1") {
            //ESTRUTURA PARA PERCORRER OS DADOS SALVOS
            for (int i = productList.size(); i <= 5; i++) {
                productList.add(i, newProduct);
                System.out.println(productList.get(i).name);
                System.out.println(productList.get(i));
            }
        }

        System.out.println("Gostaria de adicionar outro produto? ");
        System.out.println("1: Sim || 2: Não");
        select = sc.next();
        if (select != "1") {
            System.out.println("Gostaria de voltar ao inicio?");
            System.out.println("1: Sim || 2: Não");
            select = sc.next();
            //INJETA O MEDOTO ATRAVES DE VALIDACAO
            if (select != "1") {
                choices();
            } else { //RETORNA UM NOVO METODO DE ADICIONAR PRODUTO
                new Product().addProduct();
            }
        } else {
            // RETORNA O PRODUTO ADICIONADO
            return addProduct();
        }
        return newProduct;

    }

    //METODO QUE FAZ A VERIFICACAO DO ESTADO DO USUARIO
    public int choices() {
        System.out.println("1: add product".toUpperCase());
        System.out.println("2: all products".toUpperCase());
        System.out.println("3: remove product".toUpperCase());
        System.out.println("9: exit".toUpperCase());
        int x = sc.nextInt();
        //OLHA ESSA FOI A ESTRUTURA MAIS RAPIDA E SIMPLES QUE PUDE IMAGINAR.
        //MAS CONCORDO QUE É PESSIMA KKK
        if (x == 1) {
            addProduct();
        }
        if (x == 2) {
            getProduct();
        }
        if (x == 9) {

        } else {
            return 404;
        }

        return 404;
    }

    public Product(String name, float value, int amount, ProductInterface product) {
        this.name = name;
        this.value = value;
        this.amount = amount;
        this.product = product;
    }

    public Product(String string, int i, int j) {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    //METODO QUE RETORNA OS PRODUTOS
    public ArrayList<Product> getProduct() {

        //VERIFICACAO SE EXISTE PRODUTO
        if (!productList.isEmpty()) {
            //SE EXISTE ELE RETORNA OS ELEMENTOS ATÉ O VALOR DESEJADO
            for (int i = productList.size(); i <= 10; i++) {
                productList.addAll(i, product.findAll(this, Direction.ASC));//REPOSITORIO FOI ADICIONADO UM METODO DE RETORNAR EM FORMA DIRECIONAL. MAS PDOE SAER ALTERADA.
                for ( Product prod : productList) {// TESTEI UMA UTILIZAÇÃO DE FOREACH MAS NÃO TIVE UMA BOA EXPERIENCIA NESSE MOMENTO...
                    System.out.println(prod.getProduct());
                }
            }

        } else {
            System.out.println("Lista vazia! ");
        }
        System.out.println("Gostaria de verificar novamente? 1: Sim || 2: Não");
        
        if(select != "1"){
            getProduct();
        }else{
            choices();
        }
        return productList;
    }

    public void setProduct(ProductInterface product) {
        this.product = product;
    }

    public void setId(long id) {
        this.id = id;
    }

}
