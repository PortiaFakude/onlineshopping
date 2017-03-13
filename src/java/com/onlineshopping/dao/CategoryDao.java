/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.onlineshopping.entity.Category;
import com.onlineshopping.util.HibernateUtil;
import org.hibernate.Query;

/**
 *
 * @author HP
 */
public class CategoryDao 
{
    public void addCategory(Category cat)
    {
        Transaction tx = null;
        Session session =HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(cat);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
      public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            categories = session.createQuery("select  catName from category").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return categories;
    }
      
      public List<Category> getCategoryById(String catId) {
        System.out.println(catId);
//        Customer cust = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from category where catName = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", catId);
            //cust = (Customer) query.uniqueResult();
            List<Category> list = query.list();
            if (list.size() > 0) {
                return list;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return null;
    }
}