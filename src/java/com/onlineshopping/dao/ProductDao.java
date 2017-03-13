/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.dao;

import com.onlineshopping.entity.Product;
import com.onlineshopping.util.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import javax.transaction.Synchronization;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.spi.LocalStatus;

/**
 *
 * @author HP
 */
public class ProductDao 
{
  public void addProduct(Product prod)
    {
       
        Transaction tx = null;
        Session session =HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(prod);
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
}
