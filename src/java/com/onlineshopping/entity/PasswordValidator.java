
package com.onlineshopping.entity;

/**
 *
 * @authorPortia
 */
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

          private Pattern pattern;
	  private Matcher matcher;
          
          private static final String PASSWORD_PATTERN =
              "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	  public PasswordValidator(){
		  pattern = Pattern.compile(PASSWORD_PATTERN);
	  }
          
          /**
	   * Validate password with regular expression
	   * @param password password for validation
	   * @return true valid password, false invalid password
	   */
          
          public boolean validate(final String password){

		  matcher = pattern.matcher(password);
		  return matcher.matches();

	  }
          
	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
            
            matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){

			FacesMessage msg =
				new FacesMessage("Password validation failed.",
						"Invalid password,must contain 1 digit,lowercase,uppercase,special symbols(@#$%),at least 6 characters and maximum of 20 characters ");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	  String password = value.toString();

	  UIInput uiInputConfirmPassword = (UIInput) component.getAttributes()
		.get("confirmPassword");
	  String confirmPassword = uiInputConfirmPassword.getSubmittedValue()
		.toString();

	  // Let required="true" do its job.
	  if (password == null || password.isEmpty() || confirmPassword == null
		|| confirmPassword.isEmpty()) {
			return;
	  }

	  if (!password.equals(confirmPassword)) {
		uiInputConfirmPassword.setValid(false);
		throw new ValidatorException(new FacesMessage(
			"Password must match confirm password."));
	  }

	}
}