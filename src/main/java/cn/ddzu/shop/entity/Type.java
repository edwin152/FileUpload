package cn.ddzu.shop.entity;

public class Type implements Comparable<Type> {

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Type
                && compareTo((Type) obj) == 0;
    }

    @Override
    public int compareTo(Type o) {
        return Long.compare(id, o.id);
    }
}
