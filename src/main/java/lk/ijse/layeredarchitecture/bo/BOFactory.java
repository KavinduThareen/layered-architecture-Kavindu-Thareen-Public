package lk.ijse.layeredarchitecture.bo;

import lk.ijse.layeredarchitecture.bo.custom.CustomerBOImpl;
import lk.ijse.layeredarchitecture.bo.custom.ItemBOImpl;
import lk.ijse.layeredarchitecture.bo.custom.PlaseorderOBImpl;

public class BOFactory {


    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getDaoFactory(){
        return (boFactory==null)?boFactory = new BOFactory():boFactory;
    }

    public enum BoType{
        CUSTOMER,ITEM,PLACE_ORDER
    }

    public SuperBO getBO(BOFactory.BoType boType){

        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();

            case ITEM:
                return new ItemBOImpl();
                
            case PLACE_ORDER:
                return new PlaseorderOBImpl();
                
            default:
                return null;
        }

    }
    
}
