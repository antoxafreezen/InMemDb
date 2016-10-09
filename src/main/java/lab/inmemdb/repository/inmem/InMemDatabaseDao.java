package lab.inmemdb.repository.inmem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.inmemdb.domain.Database;
import lab.inmemdb.repository.DatabaseDao;
import lab.inmemdb.repository.SequenceManager;

@Repository
public class InMemDatabaseDao implements DatabaseDao{

    @Autowired
    private SequenceManager sequenceManager;

    private ArrayList<Database> databases;

    @PreDestroy
    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("databases.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(databases);
        oos.flush();
        oos.close();
    }

    @PostConstruct
    public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("databases.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            databases = (ArrayList) oin.readObject();
        } catch (Exception e) {
           databases = new ArrayList<>();
        }
    }

    @Override
    public Database getById(Integer id) {
        return databases.stream().filter(db -> id.equals(db.getId())).findAny().get();
    }

    @Override
    public List<Database> findAll() {
        return databases;
    }

    @Override
    public Database removeById(Integer id) {
        for (Database d : databases) {
            if (d.getId().equals(id)) {
                databases.remove(d);
                return d;
            }
        }
        return null;
    }

    @Override
    public Database create(Database db) {
        db.setId(sequenceManager.getDatabaseSequence());
        databases.add(db);
        for (Database d : databases) {
            if (d.equals(db)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Database remove(Database db) {
        databases.remove(db);
        return db;
    }

    @Override
    public Database update(Database db) {
        for (Database d : databases) {
            if (d.equals(db)) {
                d.setName(db.getName());
                return d;
            }
        }
        return db;
    }
}
