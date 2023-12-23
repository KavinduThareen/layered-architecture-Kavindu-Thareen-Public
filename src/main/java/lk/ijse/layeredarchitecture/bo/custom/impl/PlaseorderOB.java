package lk.ijse.layeredarchitecture.bo.custom.impl;

import lk.ijse.layeredarchitecture.bo.SuperBO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaseorderOB extends SuperBO {
    boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException ;
    CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;

    boolean existItem(String code)throws SQLException, ClassNotFoundException;

    boolean existCustomer(String id)throws SQLException, ClassNotFoundException;

    String generateOrderID()throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomer()throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems()throws SQLException, ClassNotFoundException;
}
