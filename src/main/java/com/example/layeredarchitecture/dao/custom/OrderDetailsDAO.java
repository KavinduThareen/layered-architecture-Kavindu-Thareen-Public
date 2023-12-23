package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.Entity.OrderDetail;
import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;

public interface OrderDetailsDAO extends CrudDAO<OrderDetail> {
   // public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
