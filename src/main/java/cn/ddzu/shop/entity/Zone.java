package cn.ddzu.shop.entity;

public class Zone implements Comparable<Zone> {

    private long id;
    private long district_id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(long district_id) {
        this.district_id = district_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Zone
                && compareTo((Zone) obj) == 0;
    }

    @Override
    public int compareTo(Zone o) {
        return Long.compare(id, o.id);
    }
}
