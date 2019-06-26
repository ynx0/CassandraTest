import com.datastax.driver.core.*;

import java.math.BigInteger;
import java.util.HashMap;

public class CassandraRW {

    private Cluster cluster;
    private Session session;
    private HashMap<Queries, PreparedStatement> preparedStatements = new HashMap<>();

    public CassandraRW() {
        Cluster.Builder builder = Cluster.builder();
        builder.addContactPoint("127.0.0.1");
        this.cluster = builder.build();
        this.session = null;
    }

    public void setup() {
        this.connect();
        this.prepareStatements();
    }


    private void prepareStatements() {
        for (Queries unprepQuery : Queries.values()) {
            preparedStatements.put(unprepQuery, session.prepare(unprepQuery.getQueryText()));
        }
    }

    private void connect() {
        session = cluster.connect();
    }


    public ResultSet execPreparedStatement(PreparedStatement statement, Object... args) {
        // from https://docs.datastax.com/en/developer/java-driver/3.0/manual/statements/prepared/
        return session.execute(statement.bind(args));
    }

    public ResultSet execQuery(Queries query, Object... args) {
        return execPreparedStatement(preparedStatements.get(query), args);
    }

    public ResultSet getActiveCustomers() {
        return execQuery(Queries.GET_ACTIVE_CUSTOMERS);
    }

    public ResultSet writeCustomer(int customerID, String customerEmail, String customerName, BigInteger customerPhone, String customerVisitReason) {
        return execQuery(Queries.INSERT_ACTIVE_CUSTOMER, customerID, customerEmail, customerName, customerPhone, customerVisitReason);
    }


    public static void printResult(ResultSet tableOfCustomers) {
        for (Row customer : tableOfCustomers) {
            System.out.println("-----------------------");

            // this resource helps a bit http://itdoc.hitachi.co.jp/manuals/3020/30203V0300e/BV030040.HTM
            System.out.println("Customer ID:\t " + customer.get(0, Integer.class));
            System.out.println("Customer Email:\t " + customer.get(1, String.class));
            System.out.println("Customer Name:\t " + customer.get(2, String.class));
            System.out.println("Customer Phone:\t " + customer.get(3, BigInteger.class));
            System.out.println("Customer Visit Reason:\t " + customer.get(4, String.class));
        }
    }

}
