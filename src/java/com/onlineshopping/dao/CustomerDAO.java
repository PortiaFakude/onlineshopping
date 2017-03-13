/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.dao;

import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.Customer;
import com.onlineshopping.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
 
/**
 *
 * @author javaknowledge
 */
public class CustomerDAO{
 
    public void addCustomer(Customer cust) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(cust);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
 
    }
    
    
     public List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<Customer>();
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            customers = session.createQuery("select  * from category").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return customers;
    }
     
     
     public List<Customer> getCustomerById(String custId) {
        System.out.println(custId);
//        Customer cust = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from customer where custName = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", custId);
            //cust = (Customer) query.uniqueResult();
            List<Customer> list = query.list();
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

