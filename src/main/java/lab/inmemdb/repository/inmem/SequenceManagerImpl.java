package lab.inmemdb.repository.inmem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Repository;

import lab.inmemdb.repository.SequenceManager;

@Repository
public class SequenceManagerImpl implements SequenceManager, Serializable {

    private Integer databaseSequence;
    private Integer tableSequence;
    private Integer recordSequence;
    private Integer attributeSequence;

    public Integer getDatabaseSequence() {
        return databaseSequence++;
    }

    public Integer getTableSequence() {
        return tableSequence++;
    }

    public Integer getRecordSequence() {
        return recordSequence++;
    }

    public Integer getAttributeSequence() {
        return attributeSequence++;
    }

    @PreDestroy
    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("sequence.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    @PostConstruct
    public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("sequence.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            SequenceManagerImpl manager = (SequenceManagerImpl) oin.readObject();
            this.databaseSequence = manager.databaseSequence;
            this.tableSequence = manager.tableSequence;
            this.recordSequence = manager.recordSequence;
            this.attributeSequence = manager.attributeSequence;
        } catch (Exception e) {
            this.databaseSequence = 1;
            this.tableSequence = 1;
            this.recordSequence = 1;
            this.attributeSequence = 1;
        }
    }
}
