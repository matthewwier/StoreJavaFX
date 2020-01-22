package factory;

import forms.UserForm;

/**
 * Factory for User Form
 *
 */
public class UserFormFactory implements AbstractFormFactory {
    @Override
    public UserForm createForm() {
        return new UserForm();
    }
}
