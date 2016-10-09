package lab.inmemdb.repository;

import java.util.List;

import lab.inmemdb.domain.TableAttribute;

public interface TableAttributeDao {
    TableAttribute create(TableAttribute attribute);

    TableAttribute remove(TableAttribute attribute);

    TableAttribute update(TableAttribute attribute);

    TableAttribute getById(Integer id);

    List<TableAttribute> findAll();
}
