package com.service.api.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.service.api.beans.RegisterRequest;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static com.service.api.constants.SqlConstants.*;

@Singleton
public class UserDao {

    @Inject
    private DBI jdbi;

    @Inject
    private Connection connection;

    public boolean registerUser(RegisterRequest registerRequest) {
        int update;
        try (Handle handle = jdbi.open()) {
/*            List<Map<String, Object>> select = handle.select("SELECT id, name, quantity FROM Inventory");
            for (Map<String, Object> paramMap : select) {
                String name1 = (String) paramMap.get("name");
                name1 = name1.trim();
                int quantity = (int)paramMap.get("quantity");
                System.out.println(name1 + quantity);
            }*/
            update = handle.createStatement(USER_INSERT_QUERY).bindFromMap(mapData(registerRequest)).execute();
        }
/*        System.out.print("Reading data from table, press ENTER to continue...");
        String sql = "SELECT id, name, quantity FROM Inventory";
        try{
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }*/
        return update == 1;
    }

    private Map<String, String> mapData(RegisterRequest registerRequest) {
        Map<String, String> map = new HashMap<>();
        map.put(LAST_NAME, registerRequest.getLastName());
        map.put(FIRST_NAME, registerRequest.getFirstName());
        map.put(USERNAME, registerRequest.getUserName());
        map.put(PASSWORD, registerRequest.getPassword());
        map.put(USER_AGE, String.valueOf(registerRequest.getAge()));
        map.put(USER_BIO, registerRequest.getBio());
        //map.forEach((key, value) -> System.out.println(key + " " + value));
        return map;
    }

}
