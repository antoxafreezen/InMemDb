package lab.inmemdb.service;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Table;
import lab.inmemdb.web.dto.TableDto;

public interface TableService {
    Iterable<Table> findAllByDbId(Integer id);

    Table addTableToDb(String name, Integer dbId);

    Table updateName(Table table);

    Table removeById(Integer id);

    Table getTableById(Integer tbId);
}
