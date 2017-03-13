/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onlineshopping.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author HP
 */
@FacesValidator("contactValidator")
public class ContactValidator implements Validator{

    private Pattern pattern;
    private Matcher matcher;

    private static final String CONTACT_PATTERN = "^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$" ;
  
  public ContactValidator(){
	  pattern = Pattern.compile(CONTACT_PATTERN);
  }   
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){

			FacesMessage msg =
				new FacesMessage("Contact validation failed.",
						"Please re-enter your contact numbers eg.011-256-2356");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}
        
    }  
}
    


