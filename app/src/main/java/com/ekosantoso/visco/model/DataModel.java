package com.ekosantoso.visco.model;

/**
 * Created by Eko PS on 10/26/2017.
 */

public class DataModel {
    String key, kd_cus, nm_cus, tp_lok, nm_pasar, lok_pasar, al_cus, no, rt, rw, daerah, nm_pemilik, no_hp;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKd_cus() {
        return kd_cus;
    }

    public void setKd_cus(String kd_cus) {
        this.kd_cus = kd_cus;
    }

    public String getNm_cus() {
        return nm_cus;
    }

    public void setNm_cus(String nm_cus) {
        this.nm_cus = nm_cus;
    }

    public String getTp_lok() {
        return tp_lok;
    }

    public void setTp_lok(String tp_lok) {
        this.tp_lok = tp_lok;
    }

    public String getNm_pasar() {
        return nm_pasar;
    }

    public void setNm_pasar(String nm_pasar) {
        this.nm_pasar = nm_pasar;
    }

    public String getLok_pasar() {
        return lok_pasar;
    }

    public void setLok_pasar(String lok_pasar) {
        this.lok_pasar = lok_pasar;
    }

    public String getAl_cus() {
        return al_cus;
    }

    public void setAl_cus(String al_cus) {
        this.al_cus = al_cus;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getNm_pemilik() {
        return nm_pemilik;
    }

    public void setNm_pemilik(String nm_pemilik) {
        this.nm_pemilik = nm_pemilik;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public DataModel(String key, String kd_cus, String nm_cus, String tp_lok, String nm_pasar, String lok_pasar, String al_cus, String no, String rt, String rw, String daerah, String nm_pemilik, String no_hp) {
        this.key = key;
        this.kd_cus = kd_cus;
        this.nm_cus = nm_cus;
        this.tp_lok = tp_lok;
        this.nm_pasar = nm_pasar;
        this.lok_pasar = lok_pasar;
        this.al_cus = al_cus;
        this.no = no;
        this.rt = rt;
        this.rw = rw;
        this.daerah = daerah;
        this.nm_pemilik = nm_pemilik;
        this.no_hp = no_hp;
    }
}
