/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import com.onlineshopping.dao.CategoryDao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name ="category", catalog ="onlineshopping")
@ManagedBean(name ="category")
@RequestScoped
public class Category implements java.io.Serializable
{
  
    private Integer catId;
    private String catName;
    private String catSelected;
    
    public Category()
    {}
    public Category(String Catname)
    { catName =Catname;}
    
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="catId", unique=true, nullable=false)
    public Integer getCatId() {
        return catId;
    }

    public void setCatId(int catid) {
        this.catId = catid;
    }
    @Column(name ="catName", unique=true,nullable =false)
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
    
    public String getCatSelected() {
        return catSelected;
    }

    public void setCatSelected(String catSelected) {
        this.catSelected = catSelected;
    }
    public void saveCategory()
    {
        CategoryDao cd = new CategoryDao();
        cd.addCategory(this);
        
    }
    public List<Category> getAllCategories()
    {
        List<Category> categories = new ArrayList<Category>();
        CategoryDao dao = new CategoryDao();
        categories = dao.getCategories();
        return categories;
    }
    public void fullInfo() {
        CategoryDao dao = new CategoryDao();
        List<Category> lc =dao.getCategoryById(catSelected);
        System.out.println(lc.get(0).catName);
        this.catId = lc.get(0).catId;
        this.catName = lc.get(0).getCatName();
    }
}
