package lab.inmemdb.domain;

import lab.inmemdb.infrastructure.DataType;

public class Value<T> {

    private T value;
    private String type;

    public Value(T value, String type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Value{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Value<?> value1 = (Value<?>) o;

        if (value != null ? !value.equals(value1.value) : value1.value != null) {
            return false;
        }
        return type == value1.type;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
