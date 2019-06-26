public enum Queries {

    GET_ACTIVE_CUSTOMERS("SELECT * FROM activecustomers.currentusers;"),
    INSERT_ACTIVE_CUSTOMER("INSERT INTO activecustomers.currentusers" +
                                  "(customer_id, customer_email, customer_name, customer_phone, customer_visit_reason)" +
                                  "VALUES (?, ?, ?, ?, ?)");
    private String query;

    public String getQueryText() {
        return query;
    }

    Queries(String query) {
        this.query = query;
    }

}
