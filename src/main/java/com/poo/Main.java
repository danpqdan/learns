package com.poo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.poo.entities.Product;

public class Main {

    @Autowired

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Product newProduct = new Product();

        System.out.println("Qual seu nome: ");
        String user = sc.nextLine();

        System.out.println("Seja bem vindo: " + user);
        System.out.println("Gostaria de acessar? Aperte enter ou digite 9 para sair... ");
        String turnOff = sc.nextLine();

        //LIFECICLE DO SISTEMA 

        //START
        if (turnOff.isEmpty()) {
            //VALIDAÇÃO DE EXIT
            if(newProduct.choices() != 9){
            //NÃO RETORNAR PRODUTO VAZIO
            if(newProduct.addProduct() == null){
                sc.close();
            }
            }

        }

        sc.close();
    }

}