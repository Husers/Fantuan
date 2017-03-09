package com.suz.fantuan.dao.Impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.suz.fantuan.dao.BaseDao;
import com.suz.fantuan.dao.MealsDao;
import com.suz.fantuan.model.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Repository
public class MealsDaoImpl extends BaseDao implements MealsDao {
    MongoCollection<Document> userCollection = Connect().getCollection("user");

    private Gson gson;

    @Autowired
    public MealsDaoImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String findMealsByName(String username) {
        List<Document> list = userCollection.find(Filters.eq("username", username)).into(new ArrayList<Document>());
        User user = gson.fromJson(list.get(0).toJson(), User.class);
        return user.getMeals();
    }

    @Override
    public void updateMealsByName(String username, String meals) {
        userCollection.updateOne(Filters.eq("username", username), new Document("$set", new Document("meals", meals)));
    }

    @Override
    public String findSumMeals() {
        List<Document> list = userCollection.find().into(new ArrayList<Document>());
        BigDecimal sumMeals = BigDecimal.valueOf(0);
        for (Document document : list) {
            sumMeals = sumMeals.add(new BigDecimal(gson.fromJson(document.toJson(), User.class).getMeals()));
        }
        return sumMeals.toString();
    }
}
