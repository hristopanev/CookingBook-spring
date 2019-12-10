//package net.cookingbook.errors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User or email exists!")
//public class UserAlreadyExistException extends RuntimeException {
//
//    private int statusCode;
//
//    public UserAlreadyExistException() {
//        this.statusCode = 409;
//    }
//
//    public UserAlreadyExistException(String  message) {
//        super(message);
//        this.statusCode = 409;
//    }
//
//    public int getStatusCode() {
//        return statusCode;
//    }
//}
