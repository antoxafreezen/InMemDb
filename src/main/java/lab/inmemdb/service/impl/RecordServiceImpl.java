package lab.inmemdb.service.impl;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.Table;
import lab.inmemdb.repository.RecordDao;
import lab.inmemdb.service.RecordService;
import lab.inmemdb.service.TableService;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private TableService tableService;
    @Autowired
    private RecordDao recordDao;

    @Override
    public Record addRecordToTable(Record record, Integer tbId) {
        Table table = tableService.getTableById(tbId);
        record.setTable(table);
        return recordDao.create(record);
    }

    @Override
    public Record update(Record record) {
        return recordDao.update(record);
    }

    @Override
    public Record removeById(Integer id) {
        return recordDao.findAll().stream().filter(r -> r.getId().equals(id)).findAny().get();
    }

    @Override
    public Record getById(Integer id) {
        return recordDao.getById(id);
    }

    @Override
    public Iterable<Record> findAllByTableId(Integer id) {
        return recordDao.findAll().stream()
                .filter(a -> a.getTable().getId().equals(id))
                .collect(Collectors.toList());
    }
}
