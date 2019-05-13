package cn.ddzu.shop.entity;

import java.util.List;

public class District implements Comparable<District> {

    private long id;
    private String name;
    private List<Zone> zoneList;

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

    public List<Zone> getZoneList() {
        return zoneList;
    }

    public void setZoneList(List<Zone> zoneList) {
        this.zoneList = zoneList;
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
