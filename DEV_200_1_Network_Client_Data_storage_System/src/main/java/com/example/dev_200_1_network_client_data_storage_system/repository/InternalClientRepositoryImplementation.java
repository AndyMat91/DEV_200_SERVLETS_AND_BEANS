package com.example.dev_200_1_network_client_data_storage_system.repository;

import com.example.dev_200_1_network_client_data_storage_system.models.Address;
import com.example.dev_200_1_network_client_data_storage_system.models.Client;
import jakarta.ejb.Singleton;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class InternalClientRepositoryImplementation implements ClientRepository {

    private static Map<Integer, Client> clientDb;
    private static Map<String, Address> addressDb;

    static {
        clientDb = new HashMap<>();
        addressDb = new HashMap<>();
        Address address1 = new Address("192.168.52.20", "2B-3G-5G-34-57-Y6", "XYL-001", "Russia, Moscow, ul. Lesnaya, d. 5, kv. 176");
        Address address2 = new Address("192.168.0.143", "2C-5G-9H-39-66-XO", "ZTK-076", "Russia, Moscow, ul. Lenina, d. 20, kv. 786");
        Address address3 = new Address("192.168.112.5", "67-45-JK-14-76-IO", "XYL-001", "Russia, Saint Petersburg, ul. Sosnovaia, d. 33, kv. 11");
        Address address4 = new Address("192.168.34.102", "HG-YU-77-H0-47-83", "ZTK-002", "Russia, Saint Petersburg, ul. Shishkina, d. 23, kv. 54");
        Client client1 = new Client("Матвеев Андрей", "Физическое лицо", Timestamp.valueOf(LocalDateTime.now()));
        client1.setClientId(1);
        Client client2 = new Client("ООО \"Кондор\"", "Юридическое лицо", Timestamp.valueOf(LocalDateTime.now()));
        client2.setClientId(2);
        address1.setClient(client1);
        address2.setClient(client1);
        address3.setClient(client2);
        address4.setClient(client2);
        client1.setAddresses(new ArrayList<>(Arrays.asList(address1,address2)));
        client2.setAddresses(new ArrayList<>(Arrays.asList(address3,address4)));
        clientDb.put(client1.getClientId(),client1);
        clientDb.put(client2.getClientId(), client2);
        addressDb.put(address1.getMac(),address1);
        addressDb.put(address2.getMac(),address2);
        addressDb.put(address3.getMac(),address3);
        addressDb.put(address4.getMac(),address4);
    }

    @Override
    public void save(Client client) {
        clientDb.put(client.getClientId(), client);
    }

    @Override
    public void remove(Client client) {
        clientDb.remove(client.getClientId());
    }

    @Override
    public void update(int clientId, String newName, String newType) {
        Client client = findByClientId(clientId);
        client.setClientName(newName);
        client.setType(newType);
    }

    @Override
    public List<Client> findAllClient() {
        return new ArrayList<>(clientDb.values());
    }

    @Override
    public Client findByClientId(int id) {
        return clientDb.get(id);
    }

    @Override
    public void save(Address address) {
        addressDb.put(address.getMac(),address);
    }

    @Override
    public void update(String mac, Address address) {
        addressDb.remove(mac);
        addressDb.put(address.getMac(), address);
    }

    @Override
    public List<Address> findAllAddress() {
        return addressDb.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
    }

    @Override
    public Address findByAddressMac(String mac) {
        return addressDb.get(mac);
    }

    @Override
    public void remove(Address address) {
        addressDb.remove(address.getMac());
    }
}
