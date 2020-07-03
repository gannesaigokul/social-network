package com.service.core;

import com.google.inject.Inject;
import com.service.ServiceConfiguration;
import com.service.api.beans.*;
import com.service.api.dao.FeedDao;
import com.service.api.dao.UserDao;

import java.util.Comparator;
import java.util.List;

public class FeedServiceImpl implements IFeedService{

    @Inject
    private FeedDao feedDao;
    @Inject
    private UserDao userDao;
    @Inject
    private ServiceConfiguration serviceConfiguration;

    @Override
    public ProfileResponse profile(String username) {
        User user = userDao.getUserDetails(username);
        List<DataFeed> feedList = feedDao.getUserFeed(username);
        feedList.sort(Comparator.comparing(DataFeed::getCreationTime).reversed());
        /*return generateMeta(new ProfileResponse(user, feedList));*/
        return new ProfileResponse(user, feedList);
    }

    @Override
    public ProfileResponse getFeed(String username, int page) {
        int offset = page * serviceConfiguration.getRecordSize();
        List<DataFeed> dataFeeds = feedDao.getFeed(username, offset, serviceConfiguration.getRecordSize());
        /*return generateMeta(new ProfileResponse(null, dataFeeds));*/
        return new ProfileResponse(null, dataFeeds);
    }

    @Override
    public PostResponse write(PostRequest postRequest) {
        boolean status = false;
        if(requestValidator(postRequest)) {
            status = feedDao.writePost(postRequest);
        }
        /*return generateMeta(new PostResponse(status));*/
        return new PostResponse(status);
    }

    private boolean requestValidator(PostRequest postRequest) {
        return true;
    }

}
