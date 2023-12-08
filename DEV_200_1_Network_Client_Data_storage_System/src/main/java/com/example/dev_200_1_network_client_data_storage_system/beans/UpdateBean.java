package com.example.dev_200_1_network_client_data_storage_system.beans;

import com.example.dev_200_1_network_client_data_storage_system.models.Address;
import com.example.dev_200_1_network_client_data_storage_system.models.Client;
import com.example.dev_200_1_network_client_data_storage_system.repository.ClientRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class UpdateBean {

    @EJB
    private ClientRepository clientRepository;

    @EJB
    private ValidatorBean validatorBean;

    public void deleteClient(int clientId) {
        Client client = clientRepository.findByClientId(clientId);
        if (client != null) {
            clientRepository.remove(client);
        }
    }

    public boolean updateClient(String clientId, String name, String type) throws ServletException, IOException {
        boolean validateClienResult = validatorBean.validateClientList(clientId, name, type);
        if (validateClienResult)  {
            int id = Integer.parseInt(clientId);
            clientRepository.update(id, name, type);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAddress(String clientId, String mac, String newMac, String ip, String model, String address) throws ServletException, IOException {
        boolean validateClienResult = validatorBean.validateAddressList(ip, newMac, model, address);
        int id = Integer.parseInt(clientId);
        if (validateClienResult) {
            Client client = clientRepository.findByClientId(id);
            Address addr = new Address(ip,newMac,model,address);
            List<Address> list = client.getAddresses();
            list.removeIf(o -> o.getMac().equals(mac));
            list.add(addr);
            clientRepository.update(mac, addr);
            return true;
        } else {
            return false;
        }
    }

public void deleteAddress(int id, String mac) {
    Client client = clientRepository.findByClientId(id);
    List<Address> list = client.getAddresses();
    if (list.size() == 1) {
        clientRepository.remove(clientRepository.findByAddressMac(mac));
        clientRepository.remove(client);
    } else {
        list.removeIf(o -> o.getMac().equals(mac));
        clientRepository.remove(clientRepository.findByAddressMac(mac));
    }
}

    public boolean createClient(String id, String name, String type, String ip, String mac, String model, String address) throws ServletException, IOException {
        boolean validateClienResult = validatorBean.validateClientList(id, name, type);
        boolean validateAddressResult = validatorBean.validateAddressList(ip, mac, model, address);
        if (validateClienResult && validateAddressResult) {
            Address addr = new Address(ip, mac, model, address);
            Client cli = new Client(Integer.parseInt(id), name, type, Timestamp.valueOf(LocalDateTime.now()));
            addr.setClient(cli);
            cli.setAddresses(new ArrayList<>(Collections.singletonList(addr)));
            clientRepository.save(cli);
            clientRepository.save(addr);
            return true;
        } else {
            return false;
        }
    }

    public boolean createAddress(String id, String ip, String mac, String model, String address) throws ServletException, IOException {
        boolean validateAddressResult = validatorBean.validateAddressList(ip, mac, model, address);
        int clId = Integer.parseInt(id);
        if (validateAddressResult) {
            Client client = clientRepository.findByClientId(clId);
            Address addr = new Address(ip, mac, model, address);
            List<Address> list = client.getAddresses();
            addr.setClient(client);
            list.add(addr);
            client.setAddresses(list);
            clientRepository.save(addr);
            return true;
        } else {
            return false;
        }
    }
}
