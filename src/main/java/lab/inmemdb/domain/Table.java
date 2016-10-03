package lab.inmemdb.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Table {

    private static Integer count = 1;

    private Integer id = count++;
    private Database database;
    private String name;
    private List<TableAttribute> tableAttributes = new ArrayList<>();
    private List<Record> records = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TableAttribute> getTableAttributes() {
        return tableAttributes;
    }

    public void setTableAttributes(List<TableAttribute> tableAttributes) {
        this.tableAttributes = tableAttributes;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Table table = (Table) o;

        if (id != null ? !id.equals(table.id) : table.id != null) {
            return false;
        }
        if (database != null ? !database.equals(table.database) : table.database != null) {
            return false;
        }
        return name != null ? name.equals(table.name) : table.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (database != null ? database.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
