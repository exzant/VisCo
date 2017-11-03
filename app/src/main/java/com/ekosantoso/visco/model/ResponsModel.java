package com.ekosantoso.visco.model;

import java.util.List;

/**
 * Created by Eko PS on 10/26/2017.
 */

public class ResponsModel {
    String kode, status;
    List<DataModel> result;

    public List<DataModel> getResult() {
        return result;
    }

    public void setResult(List<DataModel> result) {
        this.result = result;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
