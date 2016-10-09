package lab.inmemdb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Database;
import lab.inmemdb.repository.DatabaseDao;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.DatabaseService;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private DatabaseDao databaseDao;

    @Autowired
    private TableDao tableDao;

    @Override
    public Database saveDatabase(Database database) {
        return databaseDao.create(database);
    }

    @Override
    public List<Database> findAll() {
        return databaseDao.findAll();
    }

    @Override
    public Database update(Database database) {
        return databaseDao.update(database);
    }

    @Override
    public Database removeById(Integer id) {
        tableDao.findAll().stream()
                .filter(table -> table.getDatabase().getId().equals(id))
                .forEach(table -> tableDao.remove(table));
        return databaseDao.removeById(id);
    }

    @Override
    public Database getById(Integer id) {
        return databaseDao.getById(id);
    }
}
