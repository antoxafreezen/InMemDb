package lab.inmemdb.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import lab.inmemdb.domain.Database;

public interface DatabaseDao {

    Database create(Database db);

    Database remove(Database db);

    Database update(Database db);

    Database getById(Integer id);

    List<Database> findAll();

    Database removeById(Integer id);
}
