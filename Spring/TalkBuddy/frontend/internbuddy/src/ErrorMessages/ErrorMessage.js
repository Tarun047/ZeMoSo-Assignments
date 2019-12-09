const ErrorMessage ={
    NAME_ERROR : "Invalid name field",
    EMAIL_ERROR : "Please enter a valid email address",
    AUTHENTICATION_ERROR : "Incorrect email address or password",
    FORGOT_PASSWORD_ERROR : "We will send a verification mail, if we find a user assosciated with the email",
    WEAK_PASSWORD_ERROR: `Too weak password, please ensure password meets the requirements:- 
    Must start with an alphabet or a number
    , At least 8 characters long
    , At least 1 capital letter
    , At least 1 small letter
    , At lest 1 number (@,$,#,etc)
    `
}
export default ErrorMessage;