package lab.inmemdb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.Table;
import lab.inmemdb.domain.Value;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TableDao tableDao;

    @Override
    public List<Record> findRecordsByPattern(Integer tableId, Map<TableAttribute, Value> values) throws
            ClassNotFoundException {

        List<Record> result = new ArrayList<>();

        Set<TableAttribute> attributes = values.keySet();

        Table table = tableDao.getById(tableId);

        if (!table.getTableAttributes().containsAll(attributes)) {
            return result;
        }

        for (Record record : table.getRecords()){
            boolean addToResult = true;
            for (TableAttribute tableAttribute : attributes){
                if (!record.getValues().get(tableAttribute).equals(values.get(tableAttribute))){
                    addToResult = false;
                    break;
                }
            }
            if (addToResult) {
                result.add(record);
            }
        }

        return result;
    }
}
