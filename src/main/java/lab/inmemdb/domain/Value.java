package lab.inmemdb.domain;

import java.io.Serializable;

public class Value implements Serializable{

    private String value;
    private TableAttribute tableAttribute;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TableAttribute getTableAttribute() {
        return tableAttribute;
    }

    public void setTableAttribute(TableAttribute tableAttribute) {
        this.tableAttribute = tableAttribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Value value1 = (Value) o;

        if (value != null ? !value.equals(value1.value) : value1.value != null) {
            return false;
        }
        return tableAttribute != null ? tableAttribute.equals(value1.tableAttribute) : value1.tableAttribute == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (tableAttribute != null ? tableAttribute.hashCode() : 0);
        return result;
    }
}
