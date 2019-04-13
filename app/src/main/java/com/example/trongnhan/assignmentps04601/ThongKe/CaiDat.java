package com.example.trongnhan.assignmentps04601.ThongKe;


public class CaiDat {
    private String khoanthukhoanchi;
    private String phanloai;

    public CaiDat(String khoanthukhoanchi, String phanloai) {
        this.khoanthukhoanchi = khoanthukhoanchi;
        this.phanloai = phanloai;
    }
    public CaiDat(){

    }

    public String getKhoanthukhoanchi() {
        return khoanthukhoanchi;
    }

    public void setKhoanthukhoanchi(String khoanthukhoanchi) {
        this.khoanthukhoanchi = khoanthukhoanchi;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }
}
