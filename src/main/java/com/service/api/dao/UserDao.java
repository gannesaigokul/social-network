package com.service.api.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Singleton
public class UserDao {

    @Inject
    private DBI jdbi;

    @Inject
    private Connection connection;

    public boolean registerUser(String name, String password) {
        try (Handle handle = jdbi.open()) {
            List<Map<String, Object>> select = handle.select("SELECT id, name, quantity FROM Inventory");
            for (Map<String, Object> paramMap : select) {
                String name1 = (String) paramMap.get("name");
                name1 = name1.trim();
                int quantity = (int)paramMap.get("quantity");
                System.out.println(name1 + quantity);
            }
        }
        System.out.print("Reading data from table, press ENTER to continue...");
        String sql = "SELECT id, name, quantity FROM Inventory";
        try{
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
