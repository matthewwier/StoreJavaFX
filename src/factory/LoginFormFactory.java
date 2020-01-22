package factory;

import forms.LoginForm;


/**
 * Factory for Login Form
 *
 */
public class LoginFormFactory implements AbstractFormFactory{
    @Override
    public LoginForm createForm() {
        return new LoginForm();
    }
}
