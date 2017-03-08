package com.suz.fantuan.dao.Impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.suz.fantuan.dao.BaseDao;
import com.suz.fantuan.dao.UserDao;
import com.suz.fantuan.model.User;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    MongoCollection<Document> userCollection = Connect().getCollection("user");

    @Override
    public int findPasswordByName(User user) {
        List<Document> list = userCollection.find(Filters
                .and(Filters.eq("username", user.getUsername()), Filters.eq("password", user.getPassword()))).into(new ArrayList<Document>());
        return list.size();
    }

    @Override
    public void updatePasswordByName(User user) {
        userCollection.findOneAndUpdate(Filters.eq("username", user.getUsername()), new Document("$set", new Document("password", user.getPassword())));
    }

    @Override
    public void save(User user) {
        Document document = new Document("username", user.getUsername()).append("password", user.getPassword()).append("meals", user.getMeals());
        userCollection.insertOne(document);
    }
}
