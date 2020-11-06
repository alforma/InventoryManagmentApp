package com.example.inventorymanagmentapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_entity")
public class ProductEntity {

    //Fields
    // public ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @PrimaryKey
    private int productIDInventory;
    private String prodName;
    private String prodInv;
    private String prodPrice;
    private String productID;
    private String prodMax;
    private String prodMin;

    public ProductEntity(int productIDInventory, String prodName, String prodInv, String prodPrice, String productID, String prodMax, String prodMin) {
        this.productIDInventory = productIDInventory;
        this.prodName = prodName;
        this.prodInv = prodInv;
        this.prodPrice = prodPrice;
        this.productID = productID;
        this.prodMax = prodMax;
        this.prodMin = prodMin;
    }

    public int getProductIDInventory() {
        return productIDInventory;
    }

    public void setProductIDInventory(int productIDInventory) {
        this.productIDInventory = productIDInventory;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdInv() {
        return prodInv;
    }

    public void setProdInv(String prodInv) {
        this.prodInv = prodInv;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProdMax() {
        return prodMax;
    }

    public void setProdMax(String prodMax) {
        this.prodMax = prodMax;
    }

    public String getProdMin() {
        return prodMin;
    }

    public void setProdMin(String prodMin) {
        this.prodMin = prodMin;
    }



    @Override
    public String toString() {
        return "ProductEntity{" +
                "productInventoryID=" +  productIDInventory+
                ", productName='" +  prodName + '\'' +
                ", productID=" +  productID +
                ", productInventory=" + prodInv +
                ", productPrice=" +prodPrice +

                '}';

    }






}