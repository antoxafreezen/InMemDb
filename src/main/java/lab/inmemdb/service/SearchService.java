package lab.inmemdb.service;

import java.util.List;
import java.util.Map;

import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.domain.Value;

public interface SearchService {
    List<Record> findRecordsByPattern(Integer tableId, Map<TableAttribute, Value> values) throws ClassNotFoundException;
}
