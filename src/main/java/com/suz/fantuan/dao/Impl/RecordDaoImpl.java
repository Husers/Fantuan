package com.suz.fantuan.dao.Impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.suz.fantuan.dao.BaseDao;
import com.suz.fantuan.dao.RecordDao;
import com.suz.fantuan.model.Record;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.misc.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
@Repository
public class RecordDaoImpl extends BaseDao implements RecordDao {
    MongoCollection<Document> recordCollection = Connect().getCollection("record");
    private final Gson gson;

    @Autowired
    public RecordDaoImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void save(Record record) {
        Document document = new Document("username", record.getUsername()).append("date", record.getDate()).append("type", record.getType()).append("money", record.getMoney());
        recordCollection.insertOne(document);
    }

    @Override
    public List<Record> findRecordByName(String username) {
        List<Document> list = recordCollection.find(Filters.eq("username", username)).sort(Sorts.orderBy(Sorts.ascending("date"))).limit(7).into(new ArrayList<Document>());
        List<Record> beanList = new ArrayList<>();
        for (Document document : list) {
            beanList.add(gson.fromJson(document.toJson(), Record.class));
        }
        return beanList;
    }
}
