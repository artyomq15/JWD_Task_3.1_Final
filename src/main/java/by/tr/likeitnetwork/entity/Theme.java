package by.tr.likeitnetwork.entity;

import java.io.Serializable;

public class Theme implements Serializable {
    private int id;
    private String name;

    public Theme(){}

    public Theme(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Theme theme = (Theme) object;

        if (id != theme.id) return false;
        return name != null ? name.equals(theme.name) : theme.name == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
