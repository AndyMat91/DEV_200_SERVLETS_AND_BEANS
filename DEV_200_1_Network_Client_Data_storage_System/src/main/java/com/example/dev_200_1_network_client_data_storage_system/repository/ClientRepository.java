package com.example.dev_200_1_network_client_data_storage_system.repository;

import com.example.dev_200_1_network_client_data_storage_system.models.Address;
import com.example.dev_200_1_network_client_data_storage_system.models.Client;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface ClientRepository {
    void save(Client client);
    void remove(Client client);
    void update(int clientId, String name, String type);
    List<Client> findAllClient();
    Client findByClientId(int id);
    void save(Address address);
    void remove(Address address);
    void update(String mac, Address address);
    List<Address> findAllAddress();
    Address findByAddressMac(String mac);

}
