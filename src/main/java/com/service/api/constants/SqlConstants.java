package com.service.api.constants;

public final class SqlConstants {

    //Queries
    public static final String USER_INSERT_QUERY = "INSERT INTO Users " +
            "(LastName,FirstName,UserName,Password,Age,Bio) " +
            "VALUES (:lastName, :firstName, :userName, :password, :age, :bio)";

    //Variables
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String USER_BIO = "bio";
    public static final String USER_AGE = "age";

}
