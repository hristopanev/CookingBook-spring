package net.cookingbook.validation;

import java.security.PublicKey;

public class ValidationConstants {

    /* USERS */

    public final static String USERNAME_ALREADY_EXISTS = "Username %s already exists!";

    public final static String USERNAME_EMPTY = "Username can not be empty!";

    public final static String USERNAME_LENGTH = "Username must be between 3 and 10 characters long!";

    public final static String PASSWORD_LENGTH = "Password must be between 5 and 10 characters long!";

    public final static String PASSWORDS_DO_NOT_MATCH = "Passwords don't match!";

    public final static String EMAIL_ALREADY_EXISTS = "Email %s already exists!";

    public final static String WRONG_PASSWORD = "Wrong password!";

    public final static String WRONG_USERNAME = "Wrong username!";

    public final static String NAME_LENGTH = "Name must contain at least 3 characters!";

    public final static String NAME_ALREADY_EXISTS = "%s with name %s already exists!";

    public final static String EMAIL_CANNOT_BE_EMPTY = "Email can not be empty!";

    public final static String PASSWORD_CANNOT_BE_EMPTY = "Password can not be empty!";


    /* POSTS */

    public final static String POST_NAME_LENGTH = "Recipe name must be between 2 and 30 characters long!";

    public final static String POST_IMAGE = "Recipe image can not be empty!";

    public final static String POST_IMAGE_LARGER = "Image can not be larger at 1,5MB";

    public final static String POST_INGREDIENTS = "Recipe ingredients can not be empty";

    public final static String POST_PREPARATION = "Recipe preparation can not be empty";

    public final static String POST_PRODUCTS_LENGTH = "Recipe ingredients must be min 5 characters long!";

    public final static String POST_DESCRIPTION_LENGTH = "Recipe preparation must be min 15 characters long!";

    /* GROUP*/

    public final static String GROUP_NAME_LENGTH = "Group name must contain at least 3 characters!";


    /* NOTE */

    public final static String NOTE_TITLE_LENGTH = "Note title must contain at least 3 characters!";

    /* MESSAGE */
    public final static String MESSAGE_LENGTH = "Message must contain at least 2 characters!";

}
