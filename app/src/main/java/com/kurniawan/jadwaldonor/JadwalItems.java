package com.kurniawan.jadwaldonor;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class JadwalItems implements Parcelable {
    private String namaIntansi;
    private String Alamat;
    private String jam;
    private int jumlahRencanaDonor;

    public String getNamaIntansi() {
        return namaIntansi;
    }

    public void setNamaIntansi(String namaIntansi) {
        this.namaIntansi = namaIntansi;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getJumlahRencanaDonor() {
        return jumlahRencanaDonor;
    }

    public void setJumlahRencanaDonor(int jumlahRencanaDonor) {
        this.jumlahRencanaDonor = jumlahRencanaDonor;
    }

    public JadwalItems (JSONObject jsonObject) {

        try {
            String namaInstansi = jsonObject.getString("instansi");
            this.namaIntansi = namaInstansi;

            String Alamat = jsonObject.getString("alamat");
            this.Alamat = Alamat;

            String jam = jsonObject.getString("jam");
            this.jam = jam;

            int jumlahRencanaDonor = jsonObject.getInt("rencana_donor");
            this.jumlahRencanaDonor = jumlahRencanaDonor;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.namaIntansi);
        dest.writeString(this.Alamat);
        dest.writeString(this.jam);
        dest.writeInt(this.jumlahRencanaDonor);
    }

    protected JadwalItems(Parcel in) {
        this.namaIntansi = in.readString();
        this.Alamat = in.readString();
        this.jam = in.readString();
        this.jumlahRencanaDonor = in.readInt();
    }

    public static final Parcelable.Creator<JadwalItems> CREATOR = new Parcelable.Creator<JadwalItems>() {
        @Override
        public JadwalItems createFromParcel(Parcel source) {
            return new JadwalItems(source);
        }

        @Override
        public JadwalItems[] newArray(int size) {
            return new JadwalItems[size];
        }
    };
}