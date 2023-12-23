package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.Entity.Customer;
import lk.ijse.layeredarchitecture.bo.custom.impl.CustomerBO;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.CustomerDAO;
import lk.ijse.layeredarchitecture.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.layeredarchitecture.dao.DAOFactory.DAOType.CUSTOMER;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(CUSTOMER);

    @Override
    public boolean saveCus(CustomerDTO dto) throws SQLException, ClassNotFoundException{
        return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCus() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers=customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public boolean updateCus(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
    }

    @Override
    public boolean existCus(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public void deleteCus(String id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(id);
    }

    @Override
    public String generateIDCus() throws SQLException, ClassNotFoundException {
        return customerDAO.generateID();
    }

    @Override
    public CustomerDTO searchCus(String id) throws SQLException, ClassNotFoundException {
        Customer customer=customerDAO.search(id);
        CustomerDTO customerDTO=new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }
}
