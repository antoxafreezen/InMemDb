package lab.inmemdb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.inmemdb.domain.Table;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.repository.TableAttributeDao;
import lab.inmemdb.service.TableAttributeService;
import lab.inmemdb.service.TableService;

@Service
public class TableAttributeServiceImpl implements TableAttributeService {

    @Autowired
    private TableAttributeDao tableAttributeDao;
    @Autowired
    private TableService tableService;

    @Override
    public TableAttribute addAttributeToTable(TableAttribute attribute, Integer tbId) {
        Table table = tableService.getTableById(tbId);
        attribute.setTable(table);
        return tableAttributeDao.create(attribute);
    }

    @Override
    public List<TableAttribute> findAllByTableId(Integer id) {
        return tableAttributeDao.findAll().stream()
                .filter(a -> a.getTable().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public TableAttribute updateName(TableAttribute tableAttribute) {
        TableAttribute attribute = tableAttributeDao.getById(tableAttribute.getId());
        attribute.setName(tableAttribute.getName());
        return tableAttributeDao.update(attribute);
    }

    @Override
    public TableAttribute getById(Integer id) {
        return tableAttributeDao.getById(id);
    }

    @Override
    public TableAttribute removeById(Integer id) {
        return tableAttributeDao.remove(tableAttributeDao.getById(id));
    }
}
