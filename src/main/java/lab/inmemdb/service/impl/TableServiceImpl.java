package lab.inmemdb.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.Table;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.DatabaseService;
import lab.inmemdb.service.RecordService;
import lab.inmemdb.service.TableAttributeService;
import lab.inmemdb.service.TableService;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;

    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private TableAttributeService attributeService;

    @Autowired
    private RecordService recordService;

    @Override
    public Iterable<Table> findAllByDbId(Integer id) {
        List<Table> tables = tableDao.findAll();
        Database database = databaseService.getById(id);
        return tables.stream()
                .filter(t -> t.getDatabase().equals(database))
                .collect(Collectors.toList());
    }

    @Override
    public Table addTableToDb(String name, Integer dbId) {
        Table table = new Table();
        table.setName(name);
        table.setDatabase(databaseService.getById(dbId));
        return tableDao.create(table);
    }

    @Override
    public Table updateName(Table table) {
        Table updateTable = tableDao.getById(table.getId());
        updateTable.setName(table.getName());
        tableDao.update(updateTable);
        return updateTable;
    }

    @Override
    public Table removeById(Integer id) {
        List<Record> records = (List<Record>) recordService.findAllByTableId(id);
        records.forEach(r -> recordService.removeById(r.getId()));
        List<TableAttribute> attributes = attributeService.findAllByTableId(id);
        attributes.forEach(r -> attributeService.removeById(r.getId()));
        return tableDao.remove(tableDao.getById(id));
    }

    @Override
    public Table getTableById(Integer tbId) {
        return tableDao.getById(tbId);
    }
}
