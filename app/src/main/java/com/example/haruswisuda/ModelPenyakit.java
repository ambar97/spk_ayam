package com.example.haruswisuda;

public class ModelPenyakit {
    String id, nama_penyakit, deskripsi;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public ModelPenyakit(String id, String nama_penyakit, String deskripsi) {
        this.id = id;
        this.nama_penyakit = nama_penyakit;
        this.deskripsi = deskripsi;
    }
}
