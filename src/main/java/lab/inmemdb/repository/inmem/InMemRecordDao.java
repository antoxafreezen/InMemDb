package lab.inmemdb.repository.inmem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.inmemdb.domain.Record;
import lab.inmemdb.repository.RecordDao;
import lab.inmemdb.repository.SequenceManager;

@Repository
public class InMemRecordDao implements RecordDao {

    @Autowired
    private SequenceManager sequenceManager;

    private ArrayList<Record> records = new ArrayList<>();

    @PreDestroy
    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("records.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(records);
        oos.flush();
        oos.close();
    }

    @PostConstruct
    public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("records.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            records = (ArrayList) oin.readObject();
        } catch (Exception e) {
            records = new ArrayList<>();
        }
    }


    @Override
    public Record create(Record record) {
        record.setId(sequenceManager.getRecordSequence());
        records.add(record);
        for (Record d : records) {
            if (d.equals(record)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Record remove(Record record) {
        records.remove(record);
        return record;
    }

    @Override
    public Record update(Record record) {
        for (Record d : records) {
            if (d.equals(record)) {
                d.setTable(record.getTable());
                d.setValues(record.getValues());
                return d;
            }
        }
        return record;
    }

    @Override
    public Record getById(Integer id) {
        return records.stream().filter(r -> id.equals(r.getId())).findAny().get();
    }

    @Override
    public List<Record> findAll() {
        return records;
    }
}
