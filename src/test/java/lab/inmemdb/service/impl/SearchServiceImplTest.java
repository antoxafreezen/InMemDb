package lab.inmemdb.service.impl;

import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.Table;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.domain.Value;
import lab.inmemdb.infrastructure.DataType;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.SearchService;

@ContextConfiguration(locations = {"classpath:/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchServiceImplTest {

    @Autowired
    private SearchService searchService;

    @Autowired
    private TableDao tableDao;

    @Test
    public void test(){

    }

    @Test
    public void findRecordsByPattern_whenResultNotEmpty() throws ClassNotFoundException {
        Database db = new Database();

        Table table = new Table();
        table.setName("Cars");
        table.setDatabase(db);
        table.setId(1);

        List<TableAttribute> tableAttributes = new ArrayList<>();
        TableAttribute t1 = new TableAttribute();
        t1.setName("Model");
        t1.setTable(table);
        t1.setType("String");

        TableAttribute t2 = new TableAttribute();
        t2.setName("Engine Power");
        t2.setTable(table);
        t2.setType("Integer");

        table.setTableAttributes(tableAttributes);

        Map<TableAttribute, Value> bmw = new HashMap<>();
        bmw.put(t1, new Value("BMW", "String"));
        bmw.put(t2, new Value(200, "Integer"));

        Record r1 = new Record();
        r1.setValues(bmw);
        r1.setTable(table);

        Value<Integer> expectedValue = new Value("Merc", "String");
        Map<TableAttribute, Value> merc = new HashMap<>();
        merc.put(t1, expectedValue);
        merc.put(t2, new Value(300, "Integer"));



        Record r2 = new Record();
        r2.setValues(merc);
        r2.setTable(table);

        tableAttributes.add(t1);
        tableAttributes.add(t2);
        table.setTableAttributes(tableAttributes);
        table.setRecords(Arrays.asList(r1, r2));


        Map<TableAttribute, Value> find = new HashMap<>();
        TableAttribute ta = new TableAttribute("Engine Power", "Integer");
        find.put(ta, new Value(300, "Integer"));

        tableDao.create(table);
        System.out.println(searchService.findRecordsByPattern(1, find));
        Assert.assertThat("One record was found",
                searchService.findRecordsByPattern(1, find).get(0).getValues().get(t1), is(expectedValue));

    }
}