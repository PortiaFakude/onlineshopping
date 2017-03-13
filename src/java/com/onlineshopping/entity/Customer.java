/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.entity;


import com.onlineshopping.dao.CustomerDAO;
import com.sun.istack.tools.DefaultAuthenticator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="customer" ,catalog="onlineshopping")
@ManagedBean(name="customer")
@RequestScoped

public class Customer implements java.io.Serializable{
    
   
    private Integer custId;
    private String custName;
    private String custSurname;
    private String custDOB;
    private String custGender;
    private String custContact;
    private String custEmail;
    private String custPassword;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String sd, msg, selectedname;
    private Object pattern;
    
    public Customer() {
    }

    public Customer(String custName, String custSurname, String custDOB, String custGender, String custContact, String custEmail, String custPassword, String msg, String sd) {
        this.custName = custName;
        this.custSurname = custSurname;
        this.custDOB = custDOB;
        this.custGender = custGender;
        this.custContact = custContact;
        this.custEmail = custEmail;
        this.custPassword = custPassword;
        this.sd = sd;
        this.msg = msg;
        //this.selectedname = selectedname;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="custId", unique=true, nullable=false)
    public Integer getCustId() {
        return custId;
    }
     
    
    public void setCustId(Integer custId) {
        this.custId = custId;
    }
   
    
    public String getSd() {
        return sd;
    }
 
    public void setSd(String sd) {
        this.sd = sd;
    }
 
  
    @Column(name="custName", nullable=false, length=225)
    public String getCustName() {
        return custName;
    }
 
    public void setCustName(String firstName) {
        this.custName = firstName;
    }
 
    @Column(name="custSurname", nullable=false, length=225)
    public String getCustSurname() {
        return custSurname;
    }
 
    public void setCustSurname(String lastName) {
        this.custSurname = lastName;
    }
 
    @Column(name="custEmail", nullable=false, length=225)
    public String getCustEmail() {
        return custEmail;
    }
 
    public void setCustEmail(String email) {
        this.custEmail = email;
    }
    
     @Column(name="custPassword", nullable=false, length=225)
    public String getCustPassword() {
        return custPassword;
    }
 
    public void setCustPassword(String password) {
        this.custPassword = password;
    }
    
     @Column(name="custContact", nullable=false, length=225)
    public String getCustContact() {
        return this.custContact;
    }
 
    public void setCustContact(String contact) {
        this.custContact = contact;
    }
 
     @Column(name="custGender", nullable=false, length=225)
    public String getCustGender() {
        return custGender;
    }
 
    public void setCustGender(String gender) {
        this.custGender = gender;
    }
    @Column(name="custDOB", nullable=false, length=225)
    public String getCustDOB() {
        return custDOB;
    }
 
    public void setCustDOB(String dob) {
        this.custDOB = dob;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
//    public String getSelectedname() {
//        return selectedname;
//    }
// 
//    public void setSelectedname(String selectedname) {
//        this.selectedname = selectedname;
//    }
 
    
    
    public boolean sendEmail(String from,String password ,String message,String to)
    {
        
        from = "portiafakude@gmail.com";
        password = "Dibongz1@tut";
        message = "Congratulations,you have registered successfully!";
        to = custEmail;
        
        String host  = "smtp.gmail.com";
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",from);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props,null);
        MimeMessage mimeMessage = new MimeMessage(session);
        
        
//        try{
//            mimeMessage.setFrom(new InternetAddress(from));
//            InternetAddress[] toAddress = new InternetAddress[to.length];
//            
//            //now get the address of recipients
//            for(int i = 0;i<to.length;i++){
//                toAddress[i] = new InternetAddress(to[i]);
//                
//            }
//            
//            //Now add all the toAddress elements to mimeMessage 
//            for(int i =0;i<toAddress.length;i++){
//                
//                mimeMessage.addRecipient(Message.RecipientType.TO, toAddress[i]);
//                
//            }

            try{
              mimeMessage.setFrom(new InternetAddress(from));
              InternetAddress toAddress = new InternetAddress();
              toAddress = new InternetAddress(to);
              mimeMessage.addRecipient(Message.RecipientType.TO, toAddress);
            
            //add subject
            mimeMessage.setSubject("mail using javamail");
            //set message 
            mimeMessage.setText(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(host,from,password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
            return true;
            
        }catch (MessagingException me){
            me.printStackTrace();
        }
        
        
        return false;
    }
    
    public void saveCustomer() {
//        try {
//           
//            Date d = sdf.parse(sd);
//            //System.out.println(d);
//            
//            //String demo="1960-01-01";
//            
//            //setCustDOB(new Date());
//            //this.dob = ;
//                    } catch (Exception e) 
//        {
//            System.out.println(e);
//            e.printStackTrace();
//        }
        //this managed property will read value from request parameter pageId
     
        
         //condional navigation based on pageId
   //if pageId is 1 show page1.xhtml,
   //if pageId is 2 show page2.xhtml
   //else show home.xhtml
  
        CustomerDAO dao = new CustomerDAO();
        dao.addCustomer(this);
        
        //if()
        clearAll();
    }
   
 
    private void clearAll() {
        this.custName = "";
        this.custSurname = "";
        this.custGender = "";
        this.custContact="";
        this.custPassword="";
        this.sd = "";
        this.custEmail = "";
        this.custId=0;
        this.custDOB = "";
     
    }
    

    
   
    
}
