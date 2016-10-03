package lab.inmemdb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Table;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.DatabaseService;
import lab.inmemdb.service.TableService;
import lab.inmemdb.web.dto.TableDto;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;

    @Autowired
    private DatabaseService databaseService;

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
}
