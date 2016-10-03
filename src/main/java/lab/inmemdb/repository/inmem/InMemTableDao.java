package lab.inmemdb.repository.inmem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lab.inmemdb.domain.Table;
import lab.inmemdb.repository.TableDao;

@Repository
public class InMemTableDao implements TableDao{

    private List<Table> tables = new ArrayList<>();


    @Override
    public Table create(Table table) {
        tables.add(table);
        for (Table d : tables){
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
        return create(table);
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
