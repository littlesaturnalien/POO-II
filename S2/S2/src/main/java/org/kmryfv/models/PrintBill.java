package org.kmryfv.models;

public class PrintBill {
    private Bill bill;

    public PrintBill(Bill bill) {
        this.bill = bill;
    }

    public void print(){
        System.out.println(bill.quantity + "x " + bill.book.title + "" + bill.book.price + "$");
        System.out.println("Tasa de descuento: " + bill.discountRate);
        System.out.println("Tasa de impuesto: " + bill.taxRate);
        System.out.println("Total: " + bill.total);
    }
}
