package lab.inmemdb.repository.inmem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.inmemdb.domain.Table;
import lab.inmemdb.repository.SequenceManager;
import lab.inmemdb.repository.TableDao;

@Repository
public class InMemTableDao implements TableDao {

    @Autowired
    private SequenceManager sequenceManager;

    private ArrayList<Table> tables = new ArrayList<>();

    @PreDestroy
    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("tables.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(tables);
        oos.flush();
        oos.close();
    }

    @PostConstruct
    public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("tables.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            tables = (ArrayList) oin.readObject();
        } catch (Exception e) {
            tables = new ArrayList<>();
        }
    }

    @Override
    public Table create(Table table) {
        table.setId(sequenceManager.getTableSequence());
        tables.add(table);
        for (Table d : tables) {
            if (d.equals(table)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Table remove(Table table) {
        tables.remove(table);
        return table;
    }

    @Override
    public Table update(Table table) {
        for (Table d : tables) {
            if (d.equals(table)) {
                d.setName(table.getName());
                d.setDatabase(table.getDatabase());
                return d;
            }
        }
        return table;
    }

    @Override
    public Table getById(Integer id) {
        return tables.stream().filter(t -> id.equals(t.getId())).findAny().get();
    }

    @Override
    public List<Table> findAll() {
        return tables;
    }
}
