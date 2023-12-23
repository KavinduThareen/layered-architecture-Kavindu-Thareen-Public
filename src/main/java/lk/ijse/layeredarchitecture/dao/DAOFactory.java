package lk.ijse.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.Impl.*;
import lk.ijse.layeredarchitecture.dao.custom.Impl.*;

public class DAOFactory {
    //singleton

  private static DAOFactory daoFactory;

  private DAOFactory() {

  }

  public static DAOFactory getDaoFactory(){
      return (daoFactory==null)?daoFactory = new DAOFactory():daoFactory;
  }

  public enum DAOType{
    CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY
  }
   public SuperDAO getDAO(DAOType daoType){

    switch (daoType){
      case CUSTOMER:
        return new CustomerDAOImpl();

      case ITEM:
        return new ItemDAOImpl();

        case ORDER:
            return new OrderDAOImpl();

      case ORDER_DETAIL:
        return new OrderDetailsDAOImpl();

      case QUERY:
        return new QueryDAOImpl();
      default:
        return null;
    }

   }
}
