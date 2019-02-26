import java.util.List;
import java.util.Scanner;

public class Main {
    private static Dao base = new MySQLImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int item;
        do {
            System.out.println("Enter operation:");
            System.out.println("1. add client");
            System.out.println("2. add product");
            System.out.println("3. add order");
            System.out.println("4. exit");

            if (sc.hasNextInt()) item = sc.nextInt();
            else {
                sc.nextLine();
                continue;
            }

            switch (item) {
                case 1:
                    addClient();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    addOrder();
                    break;
                case 4:
                    return;
            }

        } while (true);
    }

    private static void addOrder() {
        Scanner sc = new Scanner(System.in);

        System.out.println(HeaderTables.HEADER_ORDER.getHeader());
        System.out.println(listToString(base.getAllOrder()));
        int orderNumber = getIntInput("Enter number of order: ");
        System.out.println(HeaderTables.HEADER_CLIENT.getHeader());
        System.out.println(listToString(base.getAllClient()));
        int clientNumber;
        do {
            clientNumber = getIntInput("Enter number of client: ");
            if (base.getClientById(clientNumber) != null) break;
        } while (true);

        System.out.println(HeaderTables.HEADER_PRODUCT.getHeader());
        System.out.println(listToString(base.getAllProduct()));
        do {
            int productNumber;
            do {
                productNumber = getIntInput("Enter number of product: ");
                if (base.getProductById(productNumber) != null) break;
            } while (true);
            int productQuantity = getIntInput("Enter quantity of products: ");
            base.addOrder(new Order(orderNumber, new Client(clientNumber), new Product(productNumber), productQuantity));
            System.out.println("Will you want to add more products? (y / no = any key)");
            if (sc.nextLine().equalsIgnoreCase("y")) continue;
            else break;
        } while (true);
        System.out.println(HeaderTables.HEADER_ORDER.getHeader());
        System.out.println(listToString(base.getAllOrder()));
    }

    private static void addProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter product name: ");
        String productName = sc.nextLine();
        int productPrice = getIntInput("Enter the cost of the product: ");
        base.addProduct(new Product(productName, productPrice));

        System.out.println(HeaderTables.HEADER_PRODUCT.getHeader());
        System.out.println(listToString(base.getAllProduct()));
    }

    private static void addClient() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        String fName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lName = sc.nextLine();
        base.addClient(new Client(fName, lName));

        System.out.println(HeaderTables.HEADER_CLIENT.getHeader());
        System.out.println(listToString(base.getAllClient()));
    }

    private static String listToString(List<?> list) {
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private static int getIntInput(String inTxt) {
        int intOut;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(inTxt);
            if (sc.hasNextInt()) intOut = sc.nextInt();
            else {
                sc.nextLine();
                continue;
            }
            break;
        } while (true);
        return intOut;
    }

}
