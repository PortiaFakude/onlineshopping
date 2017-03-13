/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.util;

import com.onlineshopping.entity.Category;
import com.onlineshopping.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.*;

/**
 *
 * @author HP
 */
public class Main {
    
    public static void main(String []args)
    {
//       Category cat = new Category(); 
//       cat.setCatName("Jesus1");
       
        Customer n=new Customer();
        
        
        
        n.setCustEmail("portiafakude@gmail.com");
        //n.setSd("1999-01-04");
        n.setCustName("firstName");
        n.setCustSurname("ffff");
        n.setCustGender("GFHGFHJ");
        n.setCustPassword("jhghg");
        n.setCustContact("hjfhfghh");
         n.setCustDOB("01/01/2007");
       
           String to = n.getCustEmail();
        if(n.sendEmail
        
            ("portiafakude@gmail.com",
                "Dibongz1@tut", 
                "message to recipients", 
           
                to)) System.out.println("email sent successfully");
            
                else System.out.println("Some error occured");
        
       
//       String[] to = {n.getCustEmail()};
//        if(n.sendEmail
//        
//            ("portiafakude@gmail.com",
//                "Dibongz1@tut", 
//                "message to recipients", 
//           
//                to)) System.out.println("email sent successfully");
//            
//                else System.out.println("Some error occured");
     
        
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.clear();
            session.flush();
            session.close();
        }
    }
    public List<Category> getAllCustomers() {
        List<Category> categories = new ArrayList<Category>();
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            categories = session.createQuery("select  catId from category").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return categories;   
   }
    
    
  
} 
        
