package com.service.api.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.service.api.beans.RegisterRequest;
import com.service.api.beans.User;
import com.service.api.beans.UserResponse;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.HashMap;
import java.util.Map;

import static com.service.api.constants.SqlConstants.*;

@Singleton
public class UserDao {

    @Inject
    private DBI jdbi;

    public boolean insertUser(RegisterRequest registerRequest) {
        int update;
        try (Handle handle = jdbi.open()) {
            update = handle.createStatement(QUERY_USER_INSERT).bindFromMap(mapData(registerRequest)).execute();
            if(update == 1)
                update = handle.createStatement(QUERY_FRIEND_INSERT)
                        .bind(USERNAME, registerRequest.getUserName())
                        .bind(FOLLOWER_NAME, registerRequest.getUserName())
                        .execute();
        }
        return update == 1;
    }

    public UserResponse validateUser(String username, String password) {
        UserResponse user;
        try(Handle handle = jdbi.open()) {
            Map<String, Object> select = handle.createQuery(QUERY_USER_LOGIN)
                    .bind(USERNAME, username)
                    .bind(PASSWORD, password)
                    .first();
            user = (UserResponse) mapUserData(select);
        }
        return user;
    }

    public User getUserDetails(String username) {
        User user;
        try(Handle handle = jdbi.open()) {
            Map<String, Object> select = handle.createQuery(QUERY_USER_DETAILS)
                    .bind(USERNAME, username)
                    .first();
            user = mapUserData(select);
        }
        return user;
    }

    public boolean insertFriend(String username, String followerUsername) {
        int update;
        try (Handle handle = jdbi.open()) {
            update = handle.createStatement(QUERY_FRIEND_INSERT)
                    .bind(USERNAME, username)
                    .bind(FOLLOWER_NAME, followerUsername)
                    .execute();
        }
        return update == 1;
    }

    private User mapUserData(Map<String, Object> select) {
        User user = new User();
        user.setUserId((Integer) select.get(USER_ID));
        user.setUserName((String) select.get(USERNAME));
        user.setLastName((String) select.get(LAST_NAME));
        user.setFirstName((String) select.get(FIRST_NAME));
        user.setAge((Integer) select.get(USER_AGE));
        user.setBio((String) select.get(USER_BIO));
        return user;
    }

    private Map<String, String> mapData(RegisterRequest registerRequest) {
        Map<String, String> map = new HashMap<>();
        map.put(LAST_NAME, registerRequest.getLastName());
        map.put(FIRST_NAME, registerRequest.getFirstName());
        map.put(USERNAME, registerRequest.getUserName());
        map.put(PASSWORD, registerRequest.getPassword());
        map.put(USER_AGE, String.valueOf(registerRequest.getAge()));
        map.put(USER_BIO, registerRequest.getBio());
        return map;
    }

}
