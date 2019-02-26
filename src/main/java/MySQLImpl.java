import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLImpl implements Dao {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/orderdb" + "?useSSL=false" + "&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    @Override
    public void addClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO clients(first_name, last_name) " +
                    "VALUE ('" + client.getFirst_name() + "', '" + client.getLast_name() + "')");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO products(name, price) " +
                    "VALUE ('" + product.getName() + "', '" + product.getPrice() + "')");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO orders(number, client_id,product_id,quantity) " +
                    "VALUE ('" + order.getNumber() + "', '" + order.getClient().getId() + "', '" +
                    order.getProduct().getId() + "', +  '" + order.getQuantity() + "')");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Client> getAllClient() {
        List<Client> resuls = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            while (resultSet.next()) {
                resuls.add(new Client(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resuls;
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> resuls = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                resuls.add(new Product(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resuls;
    }


    @Override
    public List<Order> getAllOrder() {
        List<Order> resuls = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery
                    ("SELECT * FROM orders o LEFT JOIN clients c ON o.client_id=c.id " +
                            "LEFT JOIN products p ON o.product_id=p.id");
            while (resultSet.next()) {
                Client client = new Client(resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
                Product product = new Product(resultSet.getInt(8), resultSet.getString(9), resultSet.getInt(10));
                resuls.add(new Order(resultSet.getInt(1), client, product, resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resuls;
    }

    @Override
    public Client getClientById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients WHERE id=" + id);
            if (resultSet.next())
                return new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductById(int id) {
        try (Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE id=" + id);
            if (resultSet.next()) return new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}