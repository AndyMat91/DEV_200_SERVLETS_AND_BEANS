package com.example.dev_200_1_network_client_data_storage_system.models;

public class Address {
    private String ip;
    private String mac;
    private String model;
    private String address;
    private Client client;

    public Address(String ip, String mac, String model, String address) {
        this.ip = ip;
        this.mac = mac;
        this.model = model;
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
