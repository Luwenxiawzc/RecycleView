package com.com.jnu.recycleview.data;

public class ShopLocation {//解析Josn数据
    String name;
    Double latitude;
    Double longitude;
    String memo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getMemo() { return memo; }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
