package com.suz.fantuan.dao;

import com.suz.fantuan.model.Record;

import java.util.List;

/**
 * Created by Huliang
 * On 17.3.7.
 */
public interface RecordDao {
    void save(Record record);
    List<Record> findRecordByName(String username);
}
