package com.example.dev_200_1_network_client_data_storage_system.models;

import java.sql.Timestamp;
import java.util.List;

public class Client {
    private int clientId;
    private String clientName;
    private String type;
    private Timestamp added;
    private List<Address> addresses;

    public Client(String clientName, String type, Timestamp added) {
        this.clientName = clientName;
        this.type = type;
        this.added = added;
    }

    public Client(int clientId, String clientName, String type, Timestamp added) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.type = type;
        this.added = added;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
