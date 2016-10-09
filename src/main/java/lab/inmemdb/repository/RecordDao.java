package lab.inmemdb.repository;

import java.util.List;
import java.util.Set;

import lab.inmemdb.domain.Record;

public interface RecordDao {
    Record create(Record record);

    Record remove(Record record);

    Record update(Record record);

    Record getById(Integer id);

    List<Record> findAll();
}
