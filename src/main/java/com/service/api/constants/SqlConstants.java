package com.service.api.constants;

public final class SqlConstants {

    //Queries
    public static final String QUERY_USER_INSERT = "INSERT INTO Users " +
            "(LastName,FirstName,UserName,Password,Age,Bio) " +
            "VALUES (:lastName, :firstName, :userName, :password, :age, :bio)";
    public static final String QUERY_FRIEND_INSERT = "INSERT INTO Friends " +
            "(UserName,FollowerName) " +
            "VALUES (:userName, :followerName)";
    public static final String QUERY_POST_INSERT = "INSERT INTO Posts " +
            "(Post,UserName,CreationTime) " +
            "VALUES (:post, :userName, :creationTime)";
    public static final String QUERY_USER_LOGIN = "SELECT userId, firstName, lastName, userName, age, bio FROM Users " +
            "WHERE UserName = :userName AND Password = :password";
    public static final String QUERY_USER_DETAILS = "SELECT userId, firstName, lastName, userName, age, bio FROM Users " +
            "WHERE UserName = :userName";
    public static final String QUERY_USER_FEED = "SELECT postId, post, userName, creationTime FROM Posts " +
            "WHERE UserName = :userName";
    public static final String QUERY_FEED = "SELECT postId, post, userName, creationTime FROM Posts " +
            "WHERE UserName IN (SELECT followerName FROM Friends WHERE UserName = :userName) " +
            "ORDER by creationTime DESC OFFSET :offset ROWS FETCH NEXT :recordSize ROWS ONLY";

    //Variables
    public static final String USER_ID = "userId";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";
    public static final String USER_BIO = "bio";
    public static final String USER_AGE = "age";
    public static final String POST_ID = "postId";
    public static final String POST = "post";
    public static final String CREATION_TIME = "creationTime";
    public static final String FOLLOWER_NAME = "followerName";
    public static final String OFFSET = "offset";
    public static final String NO_OF_RECORDS = "recordSize";

}
