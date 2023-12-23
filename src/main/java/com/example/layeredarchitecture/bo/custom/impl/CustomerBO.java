package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.SuperBO;
import com.example.layeredarchitecture.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean saveCus(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCus() throws SQLException, ClassNotFoundException;
    public boolean updateCus(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean existCus(String id) throws SQLException, ClassNotFoundException;
    void deleteCus(String id) throws SQLException, ClassNotFoundException;
    String generateIDCus() throws SQLException, ClassNotFoundException;
    CustomerDTO searchCus(String id) throws SQLException, ClassNotFoundException;
}
