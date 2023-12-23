package lk.ijse.layeredarchitecture.bo.custom;

import lk.ijse.layeredarchitecture.Entity.Item;
import lk.ijse.layeredarchitecture.bo.custom.impl.ItemBO;
import lk.ijse.layeredarchitecture.dao.DAOFactory;
import lk.ijse.layeredarchitecture.dao.custom.ItemDAO;
import lk.ijse.layeredarchitecture.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.layeredarchitecture.dao.DAOFactory.DAOType.ITEM;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(ITEM);
    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand()));
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
    //    return itemDAO.getAll();

        ArrayList<Item> items=itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();
        for (Item item:items) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOS;
    }

    @Override
    public String generateIDItem() throws SQLException, ClassNotFoundException {
        return itemDAO.generateID();
    }

    @Override
    public void deleteItem(String code) throws SQLException, ClassNotFoundException {

    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(code);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(), item.getQtyOnHand());
    }


}
