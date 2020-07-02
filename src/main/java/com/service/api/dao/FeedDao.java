package com.service.api.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.service.api.beans.DataFeed;
import com.service.api.beans.PostRequest;
import com.service.api.beans.RegisterRequest;
import com.service.api.beans.User;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.service.api.constants.SqlConstants.*;
import static java.util.stream.Collectors.toList;

@Singleton
public class FeedDao {

    @Inject
    private DBI jdbi;

    public boolean writePost(PostRequest postRequest) {
        int update;
        try {
            try (Handle handle = jdbi.open()) {
                update = handle.createStatement(QUERY_POST_INSERT).bindFromMap(mapData(postRequest)).execute();
            }
        } catch (Exception e) {
            update = 0;
        }
        return update == 1;
    }

    public List<DataFeed> getFeed(String username) {
        List<DataFeed> dataFeeds;
        try(Handle handle = jdbi.open()) {
            List<Map<String, Object>> response = handle.createQuery(QUERY_USER_FEED)
                    .bind(USERNAME, username)
                    .list();
            dataFeeds = response.stream().map(this::mapDataFeed).collect(toList());
        }
        return dataFeeds;
    }

    private DataFeed mapDataFeed(Map<String, Object> select) {
        DataFeed dataFeed = new DataFeed();
        dataFeed.setPostId((Integer) select.get(POST_ID));
        dataFeed.setUserName((String) select.get(USERNAME));
        dataFeed.setPost((String) select.get(POST));
        dataFeed.setCreationTime(((Timestamp) select.get(CREATION_TIME)).toString());
        return dataFeed;
    }

    private Map<String, String> mapData(PostRequest postRequest) {
        Map<String, String> map = new HashMap<>();
        map.put(POST, postRequest.getPost());
        map.put(USERNAME, postRequest.getUserName());
        map.put(CREATION_TIME, LocalDateTime.now().toString());
        return map;
    }

}
