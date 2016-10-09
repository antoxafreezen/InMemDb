package lab.inmemdb.service;

import java.util.List;
import java.util.Map;

import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.domain.Value;
import lab.inmemdb.infrastructure.exceptions.IncompatibleDataTypeException;

public interface SearchService {
    List<Record> findRecordsByPattern(Integer attrId, String value) throws ClassNotFoundException, IncompatibleDataTypeException;
}
