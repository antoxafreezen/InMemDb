package lab.inmemdb.infrastructure;

import java.io.Serializable;

public enum DataType implements Serializable{
    INT,
    REAL,
    CHAR,
    TXT,
    INTERVAL_INT;


    private Integer left;
    private Integer right;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "DataType{}";
    }
}
