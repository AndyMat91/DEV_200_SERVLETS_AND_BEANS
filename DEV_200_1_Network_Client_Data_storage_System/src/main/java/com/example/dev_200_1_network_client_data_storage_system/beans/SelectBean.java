package com.example.dev_200_1_network_client_data_storage_system.beans;

import com.example.dev_200_1_network_client_data_storage_system.dto.ClientDto;
import com.example.dev_200_1_network_client_data_storage_system.models.Address;
import com.example.dev_200_1_network_client_data_storage_system.repository.ClientRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class SelectBean {

    @EJB
    private ClientRepository clientRepository;

    public List<ClientDto> findAllClient() {
        List<ClientDto> list = new ArrayList<>();
        clientRepository.findAllClient().forEach(client -> {
            if (client.getAddresses() != null && !client.getAddresses().isEmpty()) {
                client.getAddresses().forEach(clientAdr -> list.add(new ClientDto(client, clientAdr)));
            } else {
                list.add(new ClientDto(client, null));
            }
        });
        return list;
    }

    public List<ClientDto> findAllClientWithNameOrAddress(String data) {
        List<ClientDto> list = new ArrayList<>();
        clientRepository.findAllClient().forEach(client -> {
            if (client.getAddresses() != null && !client.getAddresses().isEmpty() && data != null && !data.equals("")) {
                if (client.getClientName().equals(data)) {
                    client.getAddresses().forEach(clientAdr -> list.add(new ClientDto(client, clientAdr)));
                } else if (client.getAddresses().stream().map(Address::getAddress).anyMatch(data::equals)) {
                    client.getAddresses().forEach(clientAdr -> {
                        if (clientAdr.getAddress().equals(data)) {
                            list.add(new ClientDto(client, clientAdr));
                        }
                    });
                }
            } else {
                client.getAddresses().forEach(clientAdr -> list.add(new ClientDto(client, clientAdr)));
            }
        });
        return list;
    }

    public List<ClientDto> findAllClientWithType(String type, List<ClientDto> list) {
        List<ClientDto> newList = new ArrayList<>();
        if (type != null && !type.equals("") && !type.equals("--> Выберите тип <--"))  {
            List<ClientDto> finalNewList = newList;
            list.forEach(client -> {
                if (client.getType().equals(type)) {
                    finalNewList.add(client);
                }
            });
        } else newList = list;
        return newList;
    }
}
