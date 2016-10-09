package lab.inmemdb.service;

import java.util.List;

import lab.inmemdb.domain.TableAttribute;

public interface TableAttributeService {

    TableAttribute addAttributeToTable(TableAttribute attribute, Integer tbId);

    List<TableAttribute> findAllByTableId(Integer id);

    TableAttribute updateName(TableAttribute tableAttribute);

    TableAttribute getById(Integer id);

    TableAttribute removeById(Integer id);
}
