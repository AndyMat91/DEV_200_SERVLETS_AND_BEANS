package com.example.dev_200_1_network_client_data_storage_system.servlets;

import com.example.dev_200_1_network_client_data_storage_system.beans.UpdateBean;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Delete", value = "/Delete")
public class Delete extends HttpServlet {

    @EJB
    private UpdateBean updateBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId = request.getParameter("client-id");
        if(strId!=null && !strId.isEmpty()) {
            int id = Integer.parseInt(strId);
            if (request.getParameter("name").equals("DeleteClient")) {
                updateBean.deleteClient(id);
            }
            if (request.getParameter("name").equals("DeleteAddress")) {
                String mac = request.getParameter("mac");
                if(mac!=null && !mac.isEmpty()) {
                    updateBean.deleteAddress(id, mac);
                }
            }
        }
        request.getRequestDispatcher("ViewList").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
