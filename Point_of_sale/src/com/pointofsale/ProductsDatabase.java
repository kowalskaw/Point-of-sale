package com.pointofsale;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ProductsDatabase {
    private static LinkedList<Product> products;

    //adds products to the database
    static {
        products = new LinkedList<>();
        products.add(newProduct("Ananas", 111, 4.50));
        products.add(newProduct("Awokado", 112, 5.00));
        products.add(newProduct("Chleb tostowy", 113, 2.50));
        products.add(newProduct("Kawa ziarnista", 115, 15.00));
    }

    static private Product newProduct(String name, Integer barcode, Double price){
        return new Product(name, barcode, price);
    }

    //checks if product with a given barcode exists in database
    static boolean checkIfExists(Integer barcode){
        for(Product p : products){
            if(p.getBarcode().equals(barcode)) return true;
        }
        return false;
    }

    //checks if given barcode is valid or not
    static boolean checkIfValid(Integer barcode){
        Pattern patt = Pattern.compile("[0-9]{3}");
        Matcher match = patt.matcher(barcode.toString());
        return match.matches();
    }

    //returns Product with a given barcode
    static Product getByBarcode(Integer barcode){
        for(Product p : products){
            if(p.getBarcode().equals(barcode))
                return p;
        }
        return null;
    }
}
