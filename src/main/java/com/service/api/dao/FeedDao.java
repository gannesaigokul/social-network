package com.service.api.dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.skife.jdbi.v2.DBI;

@Singleton
public class FeedDao {

    @Inject
    private DBI jdbi;

}
