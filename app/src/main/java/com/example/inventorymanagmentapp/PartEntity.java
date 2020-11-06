package com.example.inventorymanagmentapp;
import androidx.room.Entity;


import androidx.room.PrimaryKey;

@Entity(tableName = "part_Entity")
public class PartEntity {

    @PrimaryKey
    int partInventoryID;
    String partID;
    private String partName;
    private  String partInventory;
    private String partPrice;
    private String partMax;
    private String partMin;
    private String companyName;


    public PartEntity(int partInventoryID, String partID, String partName, String partInventory, String partPrice, String partMax, String partMin, String companyName) {
        this.partInventoryID = partInventoryID;
        this.partID = partID;
        this.partName = partName;
        this.partInventory = partInventory;
        this.partPrice = partPrice;
        this.partMax = partMax;
        this.partMin = partMin;
        this.companyName = companyName;
    }


    public int getPartInventoryID() {
        return partInventoryID;
    }

    public void setPartInventoryID(int partInventoryID) {
        this.partInventoryID = partInventoryID;
    }

    public String getPartID() {
        return partID;
    }

    public void setPartID(String partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartInventory() {
        return partInventory;
    }

    public void setPartInventory(String partInventory) {
        this.partInventory = partInventory;
    }

    public String getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(String partPrice) {
        this.partPrice = partPrice;
    }

    public String getPartMax() {
        return partMax;
    }

    public void setPartMax(String partMax) {
        this.partMax = partMax;
    }

    public String getPartMin() {
        return partMin;
    }

    public void setPartMin(String partMin) {
        this.partMin = partMin;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }




    @Override
    public String toString() {
        return "PartEntity{" +
                "partInventoryID=" + partInventoryID+
                ", partName='" +  partName +'\'' +
                ", partID=" +  partID +
                ", partInventory=" + partInventory +
                ", partPrice=" + partPrice +
                '}';
    }
}


