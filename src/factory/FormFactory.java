package factory;

import forms.LoginForm;
import forms.UserForm;

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
