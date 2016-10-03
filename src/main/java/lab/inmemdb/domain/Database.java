package lab.inmemdb.domain;

import java.util.HashSet;
import java.util.Set;

public class Database {

    private static Integer count = 1;

    private Integer id = count++;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Database database = (Database) o;

        if (id != null ? !id.equals(database.id) : database.id != null) {
            return false;
        }
        return name != null ? name.equals(database.name) : database.name == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
