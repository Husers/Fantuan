package com.suz.fantuan.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.suz.fantuan.properties.Dao;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public class BaseDao {
    public MongoDatabase Connect(){
        MongoClientURI uri = new MongoClientURI(Dao.URI, MongoClientOptions.builder().cursorFinalizerEnabled(false));
        MongoClient client = new MongoClient(uri);
        return client.getDatabase(Dao.DATABASE);
    }
}
