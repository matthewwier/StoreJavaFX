package factory;

import forms.LoginForm;
import forms.UserForm;

public interface AbstractFormFactory {
    LoginForm createLoginForm();
    UserForm createUserForm();
}
