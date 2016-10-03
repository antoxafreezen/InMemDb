package lab.inmemdb.repository.inmem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Repository;

import lab.inmemdb.domain.Database;
import lab.inmemdb.repository.DatabaseDao;

@Repository
public class InMemDatabaseDao implements DatabaseDao {

    private List<Database> databases = new ArrayList<>();

    @Override
    public Database getById(Integer id){
        return databases.stream().filter(db -> id.equals(db.getId())).findAny().get();
    }

    @Override
    public List<Database> findAll() {
        return databases;
    }

    @Override
    public Database removeById(Integer id) {
        for (Database d : databases){
            if (d.getId().equals(id)) {
                databases.remove(d);
                return d;
            }
        }
        return null;
    }

    @Override
    public Database create(Database db){
        databases.add(db);
        for (Database d : databases){
            if (d.equals(db)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Database remove(Database db){
        databases.remove(db);
        return db;
    }

    @Override
    public Database update(Database db) {
        return create(db);
    }
}
