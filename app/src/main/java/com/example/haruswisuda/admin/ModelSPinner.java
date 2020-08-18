package com.example.haruswisuda.admin;

public class ModelSPinner {
    String penyakit, id;

    public ModelSPinner(String penyakit, String id) {
        this.penyakit = penyakit;
        this.id = id;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
