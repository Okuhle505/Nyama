/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.salesreport;

/**
 *
 * @author Student
 */
public class GUIapplication {
public interface IProductCalculations {
    double GetVat();
    double GetCommission();
    double GetTotal();
}
import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private JTextField txtCustomerName;
    private JComboBox<String> cmbProduct;
    private JTextField txtPrice;
    private JButton btnProcess;

    public ProductApp() {
        setTitle("Product App");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 20));

        // Q.2.1 / Q.2.2 - Form components
        JLabel lblCustomer = new JLabel("Customer Name:");
        txtCustomerName = new JTextField();

        JLabel lblProduct = new JLabel("Select Product:");
        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };
        cmbProduct = new JComboBox<>(products);

        JLabel lblPrice = new JLabel("Enter Price:");
        txtPrice = new JTextField();

        btnProcess = new JButton("PROCESS");
        btnProcess.addActionListener(e -> processSale());

        add(lblCustomer);
        add(txtCustomerName);
        add(lblProduct);
        add(cmbProduct);
        add(lblPrice);
        add(txtPrice);
        add(new JLabel(""));
        add(btnProcess);

        // Q.2.4 - Menu System
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> processSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Q.2.3 - PROCESS logic
    private void processSale() {
        try {
            String customerName = txtCustomerName.getText().trim();
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText().trim());

            if (customerName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter customer name");
                return;
            }

            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Popup
            String message = "Customer Name: " + customerName +
                    "\nProduct: " + product +
                    "\nVAT: R " + vat +
                    "\nCOMMISSION: R " + commission +
                    "\nTOTAL: R " + total;

            JOptionPane.showMessageDialog(this, message, "Message",
                    JOptionPane.INFORMATION_MESSAGE);

            // Save to data.txt
            try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
                out.println("DATA LOG");
                out.println("******************************************");
                out.println("Customer Name: " + customerName);
                out.println("Product: " + product);
                out.println("VAT: R " + vat);
                out.println("COMMISSION: R " + commission);
                out.println("TOTAL: R " + total);
                out.println("******************************************");
                out.println();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving to file: " + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price");
        }
    }

    private void clearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp());
    }
}
public class ProductCalculations implements IProductCalculations {
    private double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * 0.15;
    }

    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCalculationsTest {

    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calc = new ProductCalculations(3500);
        double result = calc.GetVat();
        assertEquals(525.0, result, 0.001);
    }

    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calc = new ProductCalculations(3500);
        double result = calc.GetTotal();
        assertEquals(4200.0, result, 0.001);
    }
}
    
    
}

