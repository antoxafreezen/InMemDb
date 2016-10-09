package lab.inmemdb.repository;

import java.util.List;

import lab.inmemdb.domain.Table;


public interface TableDao {

    Table create(Table table);

    Table remove(Table table);

    Table update(Table table);

    Table getById(Integer id);

    List<Table> findAll();
}
