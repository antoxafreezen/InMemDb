package lab.inmemdb.service;

import java.util.List;
import java.util.Set;

import lab.inmemdb.domain.Database;

public interface DatabaseService {

    Database saveDatabase(Database database);

    List<Database> findAll();

    Database update(Database database);

    Database removeById(Integer id);

    Database getById(Integer id);
}
