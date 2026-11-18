/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

/**
 *
 * @author Student
 */
public class Mavenproject2 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    package com.mycompany.salesreport;

import java.util.Scanner;

public class SalesReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Single dimensional array - Q.1.1
        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        // Two dimensional array - [3 products][3 months]
        int[][] sales = new int[3][3];

        for (int i = 0; i < products.length; i++) {
            for (int month = 1; month <= 3; month++) {
                System.out.print("Enter number of sales for month " + month + " for " + products[i] + " mattress: ");
                sales[i][month - 1] = sc.nextInt();
            }
            System.out.println();
        }

        Product product = new Product(products, sales);
        PrintProduct printer = new PrintProduct();

        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("******************************************");
        printer.printTotalProductSales(product.GetTotalProductsSold());
        printer.printAverageProductSales(product.GetAverageProductSold());
        printer.printMostPopularProduct(product.GetMostPopularProduct());

        sc.close();
    }
}
package com.mycompany.salesreport;

public interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
    
    package com.mycompany.salesreport;

public class Product implements IProduct {
    private String[] products;
    private int[][] sales; // [product][month]

    public Product(String[] products, int[][] sales) {
        this.products = products;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        int count = sales.length * sales[0].length; // 3 x 3 = 9
        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {
        // Matches screenshot: highest SINGLE month sale
        int max = sales[0][0];
        int maxIndex = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                if (sales[i][j] > max) {
                    max = sales[i][j];
                    maxIndex = i;
                }
            }
        }
        return products[maxIndex] + "(" + max + ")";
    }
}
package com.mycompany.salesreport;

public class PrintProduct {

    public void printTotalProductSales(int total) {
        System.out.println("TOTAL PRODUCT SALES: " + total);
    }

    public void printAverageProductSales(double average) {
        System.out.println("AVERAGE PRODUCT SALES: " + (int) average);
    }

    public void printMostPopularProduct(String popular) {
        System.out.println("MOST POPULAR PRODUCT: " + popular);
    }
}
package com.mycompany.salesreport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    // Test data from screenshot
    String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
    int[][] sales = {
        {25, 18, 20},
        {15, 10, 12},
        {18, 15, 20}
    };

    @Test
    public void GetTotalProductsSold_WithValidData_ReturnsCorrectValue() {
        Product p = new Product(products, sales);
        assertEquals(153, p.GetTotalProductsSold());
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product p = new Product(products, sales);
        assertEquals(17.0, p.GetAverageProductSold(), 0.001);
    }
}
}
}
