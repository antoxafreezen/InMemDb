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

import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.repository.SequenceManager;
import lab.inmemdb.repository.TableAttributeDao;

@Repository
public class InMemTableAttributeDao implements TableAttributeDao {

    @Autowired
    private SequenceManager sequenceManager;

    private ArrayList<TableAttribute> attributes = new ArrayList<>();

    @PreDestroy
    public void serialize() throws IOException {
        FileOutputStream fos = new FileOutputStream("attributes.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(attributes);
        oos.flush();
        oos.close();
    }

    @PostConstruct
    public void deserialize() throws IOException, ClassNotFoundException {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("attributes.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            attributes = (ArrayList) oin.readObject();
        } catch (Exception e) {
            attributes = new ArrayList<>();
        }
    }

    @Override
    public TableAttribute create(TableAttribute attribute) {
        attribute.setId(sequenceManager.getAttributeSequence());
        attributes.add(attribute);
        for (TableAttribute d : attributes) {
            if (d.equals(attribute)) {
                return d;
            }
        }
        return null;
    }

    @Override
    public TableAttribute remove(TableAttribute attribute) {
        attributes.remove(attribute);
        return attribute;
    }

    @Override
    public TableAttribute update(TableAttribute attribute) {
        for (TableAttribute d : attributes) {
            if (d.equals(attribute)) {
                d.setName(attribute.getName());
                d.setTable(attribute.getTable());
                d.setType(attribute.getType());
                return d;
            }
        }
        return attribute;
    }

    @Override
    public TableAttribute getById(Integer id) {
        return attributes.stream().filter(a -> id.equals(a.getId())).findAny().get();
    }

    @Override
    public List<TableAttribute> findAll() {
        return attributes;
    }
}
