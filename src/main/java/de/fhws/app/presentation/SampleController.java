package de.fhws.app.presentation;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SampleController {

    private String data1;
    private String data2;

    @PostConstruct
    void init() {
        data1 = "blub blub";
    }

    public void commit() {
        System.out.println(data1);
        System.out.println(data2);
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

}
