package factory;

import forms.LoginForm;
import forms.UserForm;


/**
 * Factory is responsible for creation of login and user forms.
 *
 *
 */
public class FormFactory implements AbstractFormFactory {
    @Override
    public LoginForm createLoginForm() {
        return new LoginForm();
    }

    @Override
    public UserForm createUserForm() {
        return new UserForm();
    }
}
