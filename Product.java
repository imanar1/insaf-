/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insafproject;
import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author asus
 */
@Entity
@Table(name = "Product")
 
public class Product implements Serializable {
@Column(name = "productName")
@Id
private String productName ;//VARCHAR(20) not null,
@Column(name = "productImage")
private String productImage; //VARCHAR(200),
@Column(name = "productPrice")
private int productPrice ;//DECIMAL(3,0),
@Column(name = "No_of_Product")
private int No_of_Product;// INT(2),
@Column(name = "totalAmount")
private int totalAmount; //INT(3)unique,


    public Product() {
    }
 public Product(String productName, String productImage, int productPrice, int No_of_Product, int totalAmount) {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.No_of_Product = No_of_Product;
        this.totalAmount = totalAmount;
 }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public void setNo_of_Product(int No_of_Product) {
        this.No_of_Product = No_of_Product;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getNo_of_Product() {
        return No_of_Product;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

   
    
    
    
    
    
}