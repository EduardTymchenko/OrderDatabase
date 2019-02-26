import java.util.List;

public interface Dao {

    void addClient(Client client);
    void addProduct(Product product);
    void addOrder(Order order);

    List<Client> getAllClient();
    List<Product> getAllProduct();
    List<Order> getAllOrder();
    Client getClientById(int id);
    Product getProductById(int id);
}
