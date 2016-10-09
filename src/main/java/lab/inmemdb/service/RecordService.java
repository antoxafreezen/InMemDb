package lab.inmemdb.service;

import lab.inmemdb.domain.Record;

public interface RecordService {

    Record addRecordToTable(Record record, Integer tbId);

    Record update(Record record);

    Record removeById(Integer id);

    Record getById(Integer id);

    Iterable<Record> findAllByTableId(Integer id);
}
