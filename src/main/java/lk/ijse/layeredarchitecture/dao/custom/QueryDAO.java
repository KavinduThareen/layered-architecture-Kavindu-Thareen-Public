package lk.ijse.layeredarchitecture.dao.custom;

import lk.ijse.layeredarchitecture.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    void customerOrderDetail (CustomerDAO customerDAO);
}
