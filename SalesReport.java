/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.salesreport;

/**
 *
 * @author Student
 */
public class SalesReport {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
public interface IProduct {

    int GetTotalProductsSold();

    double GetAverageProductsSold();

    String GetMostPopularProduct();
}

public class Product implements IProduct {

    private String[] productNames;
    private int[][] sales;

    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    // Calculate total products sold
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

    // Calculate average products sold
    @Override
    public double GetAverageProductsSold() {

        int total = GetTotalProductsSold();
        int count = 0;

        for (int i = 0; i < sales.length; i++) {
            count += sales[i].length;
        }

        if (count == 0) {
            return 0;
        }

        return (double) total / count;
    }

    // Find the most popular product
    @Override
    public String GetMostPopularProduct() {

        int highest = -1;
        int popularIndex = 0;

        for (int i = 0; i < sales.length; i++) {

            int productTotal = 0;

            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
            }

            if (productTotal > highest) {
                highest = productTotal;
                popularIndex = i;
            }
        }

        if (productNames.length == 0) {
            return "No products";
        }

        return productNames[popularIndex] + 
               "(" + highest + ")";
    }
}

public class PrintProduct {

    public void printReport(Product product) {

        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("********************************");

        System.out.println("TOTAL PRODUCT SALES: "
                + product.GetTotalProductsSold());

        System.out.println("AVERAGE PRODUCT SALES: "
                + (int) product.GetAverageProductsSold());

        System.out.println("MOST POPULAR PRODUCT: "
                + product.GetMostPopularProduct());
    }
}

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Single-dimensional array
        String[] productNames = {
            "Cosy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        // Two-dimensional array
        // 3 products and 3 months
        int[][] sales = new int[3][3];

        // Enter sales information
        for (int i = 0; i < productNames.length; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(
                    "Enter number of sales for month "
                    + (j + 1) + " for "
                    + productNames[i] + ": "
                );

                sales[i][j] = input.nextInt();
            }

            System.out.println();
        }

        // Create Product object
        Product product = new Product(productNames, sales);

        // Create PrintProduct object
        PrintProduct report = new PrintProduct();

        // Display report
        report.printReport(product);

        input.close();
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    String[] productNames = {
        "Cosy Nights Monarch mattress",
        "Restonic Barbados mattress",
        "Sealy Phuket mattress"
    };

    int[][] sales = {
        {25, 18, 20},
        {15, 10, 12},
        {18, 15, 20}
    };

    // Test 1: Total products sold
    @Test
    public void GetTotalProductsSales_WithValidData_ReturnsCorrectValue() {

        Product product = new Product(productNames, sales);

        assertEquals(153, product.GetTotalProductsSold());
    }

    // Test 2: Average products sold
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {

        Product product = new Product(productNames, sales);

        assertEquals(17.0, product.GetAverageProductsSold(), 0.001);
    }

    // Test 3: Most popular product
    @Test
    public void GetMostPopularProduct_WithValidData_ReturnsCorrectValue() {

        Product product = new Product(productNames, sales);

        assertEquals(
            "Cosy Nights Monarch mattress(63)",
            product.GetMostPopularProduct()
        );
    }
}

    
    
}
