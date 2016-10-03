package lab.inmemdb.service;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Table;
import lab.inmemdb.web.dto.TableDto;

public interface TableService {
    Iterable<Table> findAllByDbId(Integer id);

    Table addTableToDb(String name, Integer dbId);
}
