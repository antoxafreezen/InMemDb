package lab.inmemdb.service.impl;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lab.inmemdb.domain.Database;
import lab.inmemdb.domain.Record;
import lab.inmemdb.domain.Table;
import lab.inmemdb.domain.TableAttribute;
import lab.inmemdb.domain.Value;
import lab.inmemdb.infrastructure.DataType;
import lab.inmemdb.infrastructure.exceptions.IncompatibleDataTypeException;
import lab.inmemdb.repository.TableDao;
import lab.inmemdb.service.RecordService;
import lab.inmemdb.service.SearchService;
import lab.inmemdb.service.TableAttributeService;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @Mock
    private RecordService recordService;

    @Mock
    private TableAttributeService attributeService;

    @InjectMocks
    private SearchServiceImpl searchService;


    TableAttribute ta1, ta2, ta3;
    Record record1, record2;

    @Before
    public void setUp(){
        Database database = new Database();
        database.setId(1);
        database.setName("MyDatabase");

        Table table = new Table();
        table.setId(1);
        table.setDatabase(database);
        table.setName("MyTable");

        ta1 = new TableAttribute();
        ta1.setId(1);
        ta1.setName("BookName");
        ta1.setType(DataType.CHAR);
        ta1.setTable(table);

        ta2 = new TableAttribute();
        ta2.setId(2);
        ta2.setName("Author");
        ta2.setType(DataType.CHAR);
        ta2.setTable(table);

        ta3 = new TableAttribute();
        ta3.setId(3);
        ta3.setName("Year");
        ta3.setType(DataType.INT);
        ta3.setTable(table);

        List<Value> valueForRecords = new ArrayList<>();
        Value book = new Value();
        book.setTableAttribute(ta1);
        book.setValue("Sherlock Holmes. Part 1");
        Value author = new Value();
        author.setTableAttribute(ta2);
        author.setValue("Arthur Conan Doyle");
        Value year = new Value();
        year.setTableAttribute(ta3);
        year.setValue("1888");

        valueForRecords.add(book);
        valueForRecords.add(author);
        valueForRecords.add(year);

        record1 = new Record();
        record1.setId(1);
        record1.setTable(table);
        record1.setValues(valueForRecords);

        book.setValue("Sherlock Holmes. Part 2");
        record2 = new Record();
        record2.setId(2);
        record2.setTable(table);
        record2.setValues(valueForRecords);
    }

    @Test
    public void shouldReturnEmptyResultInCaseOfWrongSearchParameters() throws IncompatibleDataTypeException,
            ClassNotFoundException {
        when(attributeService.getById(anyInt())).thenReturn(ta3);
        List<Record> records = searchService.findRecordsByPattern(1, "Arthur Conan Doyle");
        verify(recordService, never()).findAllByTableId(anyInt());
        Assert.assertThat("nothing to find", records.size(), is(0));
    }

    @Test
    public void shouldReturnResultWithTwoRecords() throws IncompatibleDataTypeException, ClassNotFoundException {
        when(attributeService.getById(anyInt())).thenReturn(ta2);
        when(recordService.findAllByTableId(anyInt())).thenReturn(Arrays.asList(record1, record2));
        List<Record> records = searchService.findRecordsByPattern(2, "Arthur Conan Doyle");
        verify(recordService).findAllByTableId(anyInt());
        Assert.assertThat("nothing to find", records.size(), is(2));
    }
}