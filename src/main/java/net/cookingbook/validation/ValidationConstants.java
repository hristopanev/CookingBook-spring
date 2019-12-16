package net.cookingbook.validation;

import java.security.PublicKey;

public class ValidationConstants {

    /* USERS */

    public final static String USERNAME_ALREADY_EXISTS = "Username %s already exists!";

    public final static String USERNAME_LENGTH = "Username must be between 3 and 10 characters long!";

    public final static String PASSWORDS_DO_NOT_MATCH = "Passwords don't match!";

    public final static String EMAIL_ALREADY_EXISTS = "Email %s already exists";

    public final static String WRONG_PASSWORD = "Wrong password!";

    public final static String WRONG_USERNAME = "Wrong username!";

    public final static String NAME_LENGTH = "Name must contain at least 3 characters!";

    public final static String NAME_ALREADY_EXISTS = "%s with name %s already exists!";

    public final static String EMAIL_CANNOT_BE_EMPTY = "Email cannot be empty";

    public final static String PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty";


    /* POSTS */

    public final static String POST_NAME_LENGTH = "Recipe name must be between 2 and 30 characters long!";

    public final static String POST_IMAGE = "Recipe image can not be empty!";

    public final static String POST_PRODUCTS_LENGTH = "Recipe product must min 5 characters long!";

    public final static String POST_DESCRIPTION_LENGTH = "Recipe description must min 15 characters long!";

    /* NOTE */

    public final static String NOTE_TITLE_LENGTH = "Note title must contain at least 3 characters!";

    /* MESSAGE */
    public final static String MESSAGE_LENGTH = "Message must contain at least 2 characters!";

}
