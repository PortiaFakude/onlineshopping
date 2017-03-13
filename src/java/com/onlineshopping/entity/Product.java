/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.entity;

import com.onlineshopping.dao.ProductDao;
import java.sql.Blob;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name ="product", catalog ="onlineshopping")
@ManagedBean(name ="product")
@RequestScoped
public class Product implements java.io.Serializable
{

    private Integer proId;
    private Integer catId;
    private String proDesc;
    private String proImage; 
    private String proLocation;
    private String proName;
    private double proPrice;
    private Integer proQty;

    public Product()
    {
        
    }
    
    @Column(name="proAval", nullable=false, length=225)
    public String getProAval() {
        return proAval;
    }

    public void setProAval(String proAval) {
        this.proAval = proAval;
    }
    private String proAval;
    
    @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="proId", unique=true, nullable=false)
    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }
    @Column(name="catId", nullable=false, length=225)
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }
    @Column(name="proDesc", nullable=false, length=225)
    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
    @Column(name="proImage", nullable=false, length=225)
    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }
    @Column(name="proLocation", nullable=false,columnDefinition="mediumblob")
    public String getProLocation() {
        return proLocation;
    }

    public void setProLocation(String proLocation) {
        this.proLocation = proLocation;
    }
    @Column(name="proName", nullable=false, length=225)
    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
    @Column(name="proPrice", nullable=false, precision=22, scale=0)
    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }
    @Column(name="proQty", nullable=false)
    public Integer getProQty() {
        return proQty;
    }

    public void setProQty(Integer proQty) {
        this.proQty = proQty;
    }
    
    public void saveProduct()
    {
        ProductDao prod = new ProductDao();
        prod.addProduct(this);
        
    }
    
}
