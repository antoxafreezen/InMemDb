package lab.inmemdb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.infrastructure.DataTypeUtils;
import lab.inmemdb.infrastructure.exceptions.IncompatibleDataTypeException;
import lab.inmemdb.service.RecordService;
import lab.inmemdb.service.SearchService;
import lab.inmemdb.service.TableAttributeService;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private TableAttributeService attributeService;

    @Autowired
    private RecordService recordService;

    @Override
    public List<Record> findRecordsByPattern(Integer attrId, String value) throws
            ClassNotFoundException {
        List<Record> result = new ArrayList<>();
        TableAttribute attribute = attributeService.getById(attrId);
        if (DataTypeUtils.isCorrespondedValue(attribute, value)) {
            List<Record> records = (List<Record>) recordService.findAllByTableId(attribute.getTable().getId());
            for (Record record : records) {
                result.addAll(record.getValues().stream().filter(v -> v.getTableAttribute().equals(attribute) && v
                        .getValue().equals(value)).map(v -> record).collect(Collectors.toList()));
            }
        }
        return result;
    }
}
