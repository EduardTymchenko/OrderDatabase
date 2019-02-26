public class Order {
    public static final String FORMAT_HEADER = "%-11s%-30s%-30s%-30s%-10s%-10s%-10s";
    private int number;
    private Client client;
    private Product product;
    private int quantity;

    public Order(int number, Client client, Product product, int quantity) {
        this.number = number;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(FORMAT_HEADER, number, client.getFirst_name(), client.getLast_name(), product.getName(),
                quantity, product.getPrice(), quantity * product.getPrice());
    }
}
