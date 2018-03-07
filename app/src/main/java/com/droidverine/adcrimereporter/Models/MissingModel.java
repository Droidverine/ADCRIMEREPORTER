package com.droidverine.adcrimereporter.Models;

/**
 * Created by DELL on 04-03-2018.
 */

public class MissingModel {
    String name,Address,conatct,otherinfo,url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MissingModel() {
    }

    public MissingModel(String name, String address, String conatct, String otherinfo, String url) {
        this.name = name;
        Address = address;
        this.conatct = conatct;
        this.otherinfo = otherinfo;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getConatct() {
        return conatct;
    }

    public void setConatct(String conatct) {
        this.conatct = conatct;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }
}
