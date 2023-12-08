package com.example.dev_200_1_network_client_data_storage_system.dto;

import com.example.dev_200_1_network_client_data_storage_system.models.Address;
import com.example.dev_200_1_network_client_data_storage_system.models.Client;

import java.sql.Timestamp;

public class ClientDto {
    private int clientId;
    private String clientName;
    private String type;
    private Timestamp added;
    private String ip;
    private String mac;
    private String model;
    private String address;
    private Client cli;

    public ClientDto(Client client, Address addr) {
        clientId = client.getClientId();
        clientName = client.getClientName();
        type = client.getType();
        added = client.getAdded();
        if (addr!=null){
            ip = addr.getIp();
            mac = addr.getMac();
            model = addr.getModel();
            address = addr.getAddress();
            cli = addr.getClient();
        }
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getType() {
        return type;
    }

    public Timestamp getAdded() {
        return added;
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public String getModel() {
        return model;
    }

    public String getAddress() {
        return address;
    }

    public Client getClient() {
        return cli;
    }
}
