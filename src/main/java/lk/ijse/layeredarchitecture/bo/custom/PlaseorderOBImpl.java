package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.Entity.Customer;
import lk.ijse.layeredarchitecture.Entity.Item;
import lk.ijse.layeredarchitecture.Entity.Order;
import lk.ijse.layeredarchitecture.Entity.OrderDetail;
import lk.ijse.layeredarchitecture.bo.custom.impl.PlaseorderOB;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.*;
import lk.ijse.layeredarchitecture.dao.custom.*;
import lk.ijse.layeredarchitecture.db.DBConnection;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;
import lk.ijse.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.layeredarchitecture.dao.DAOFactory.DAOType.*;

public class PlaseorderOBImpl implements PlaseorderOB {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(ITEM);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(ORDER_DETAIL);
    QueryDAO queryDAO= (QueryDAO) DAOFactory.getDaoFactory().getDAO(QUERY);
    @Override
    public boolean placeOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection= DBConnection.getDbConnection().getConnection();

        //Check order id already exist or not

        boolean b1 = orderDAO.exist(orderId);
        /*if order id already exist*/
        if (b1) {
            return false;
        }

        connection.setAutoCommit(false);

        //Save the Order to the order table
        boolean b2 = orderDAO.save(new Order(orderId, orderDate, customerId));

        if (!b2) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }


        // add data to the Order Details table

        for (OrderDetailDTO detail : orderDetails) {
            boolean b3 = orderDetailsDAO.save(new OrderDetail(detail.getOid(),detail.getItemCode(),detail.getQty(),detail.getUnitPrice()));
            if (!b3) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            //Search & Update Item
            ItemDTO item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            //update item
            boolean b = itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

            if (!b) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer=customerDAO.search(id);
        CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());

    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

    public ItemDTO findItem(String code) {
        try {
            Item item = itemDAO.search(code);
            return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
