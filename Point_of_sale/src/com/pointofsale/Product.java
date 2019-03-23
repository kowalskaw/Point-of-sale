package com.pointofsale;

class Product {
    private String name;
    private Integer barcode;
    private Double price;

    Product(String name, Integer barcode, Double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    Integer getBarcode() {
        return barcode;
    }

    Double getPrice() {
        return price;
    }

    void print(){
        System.out.println(name + " " + price);
    }

}
