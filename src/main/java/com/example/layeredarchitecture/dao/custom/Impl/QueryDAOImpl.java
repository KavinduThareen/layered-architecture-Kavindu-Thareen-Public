package com.example.layeredarchitecture.dao.custom.Impl;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.QueryDAO;
import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public void customerOrderDetail(CustomerDAO customerDAO) {

        //join query

        try (Connection connection = DBConnection.getDbConnection().getConnection()) {
            String sql = "SELECT o.oid, o.date, c.name AS customerName, i.description AS itemDescription, od.qty, od.unitPrice " +
                    "FROM orders o " +
                    "JOIN customer c ON o.customerID = c.id " +
                    "JOIN orderdetails od ON o.oid = od.oid " +
                    "JOIN item i ON od.itemCode = i.code";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String orderId = resultSet.getString("oid");
                    String orderDate = resultSet.getString("date");
                    String customerName = resultSet.getString("customerName");
                    String itemDescription = resultSet.getString("itemDescription");
                    int quantity = resultSet.getInt("qty");
                    double unitPrice = resultSet.getDouble("unitPrice");

                    // Perform actions with the retrieved data, e.g., print or store in a data structure
                    System.out.println("Order ID: " + orderId);
                    System.out.println("Order Date: " + orderDate);
                    System.out.println("Customer Name: " + customerName);
                    System.out.println("Item Description: " + itemDescription);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Unit Price: " + unitPrice);
                    System.out.println("-----------------------------");
                }


            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception or use a logging framework
                // Handle the exception, throw a custom exception, or return an appropriate value
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Log the exception or use a logging framework
            // Handle the exception, throw a custom exception, or return an appropriate value
        }
    }
    }

