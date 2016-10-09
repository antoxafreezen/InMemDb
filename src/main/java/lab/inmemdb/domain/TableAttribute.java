package lab.inmemdb.domain;

import java.io.Serializable;

import lab.inmemdb.infrastructure.DataType;

public class TableAttribute implements Serializable{
    private Integer id;
    private String name;
    private Table table;
    private DataType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TableAttribute(){

    }

    public TableAttribute(String name, DataType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TableAttribute that = (TableAttribute) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}

