package cn.ddzu.shop.entity;

public class District implements Comparable<District> {

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
        return obj instanceof District
                && compareTo((District) obj) == 0;
    }

    @Override
    public int compareTo(District o) {
        return Long.compare(id, o.id);
    }
}
