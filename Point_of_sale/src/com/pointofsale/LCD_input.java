package com.pointofsale;

import java.util.LinkedList;
import java.util.Scanner;

class LCD_input {
    private LinkedList<Product> cart;

    //empty cart
    LCD_input() {
        this.cart =  new LinkedList<>();
    }

    //User Interface
    void UI(){
        while(true) {
            Scanner input = new Scanner(System.in);
            String data = input.nextLine();
            try {
                //when the input is empty
                if(data.trim().isEmpty());
                //when no more products will be added to the cart
                if (data.equals("exit")) break;
                Integer.valueOf(data);
            } catch(NumberFormatException nfe) {
                System.out.println("Invalid input.");
                continue;
            }
            Integer barcode = Integer.valueOf(data);
            input(barcode);
        }
        System.out.println();
        printReceipt();
    }

    //prints message depending on given barcode
    private void input(Integer barcode){
        switch(checkProduct(barcode)) {
            case 1:
                System.out.println("Invalid bar-code.");
                break;
            case 2:
                System.out.println("Product not found.");
                break;
            case 3:
                //if barcode is valid, adds product to the cart
                addToCart(barcode);
                //prints out name and price of the product with a given barcode
                ProductsDatabase.getByBarcode(barcode).print();
                break;
        }
    }

    //adds product with a given barcode to the cart
    private void addToCart(Integer barcode){
        cart.add(ProductsDatabase.getByBarcode(barcode));
    }

    private Integer checkProduct(Integer barcode){
        if(!ProductsDatabase.checkIfValid(barcode))
            return 1;   //invalid format of a barcode
        else if(!ProductsDatabase.checkIfExists(barcode))
            return 2;   //product with a given barcode is not in the database
        else
            return 3;   //proper barcode and product exists
    }

    private void printReceipt(){
        Double summary = 0.0;
        System.out.println("Receipt:\n");
        for(Product p : cart){
            p.print();
            summary += p.getPrice();
        }
        System.out.println("\nIn total: " + summary);
    }
}
