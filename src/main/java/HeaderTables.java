public enum HeaderTables {

    HEADER_CLIENT("Clients table\n" + String.format(Client.FORMAT_HEADER, "id", "first_name", "last_name")),
    HEADER_PRODUCT("Products table\n" + String.format(Product.FORMAT_HEADER, "id", "Product name", "price")),
    HEADER_ORDER("Order table\n" + String.format(Order.FORMAT_HEADER, "Number", "First name", "Last Name", "Product", "quantity", "Price", "Sum"));

    private String header;

    HeaderTables(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
