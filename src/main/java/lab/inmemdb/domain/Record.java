package lab.inmemdb.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Record {

    private Table table;
    private Map<TableAttribute, Value> values = new HashMap<>();

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Map<TableAttribute, Value> getValues() {
        return values;
    }

    public void setValues(Map<TableAttribute, Value> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "Record{" +
                "table=" + table +
                ", values=" + values +
                '}';
    }
}
