import com.datastax.driver.core.ResultSet;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        CassandraRW cassandraManager = new CassandraRW();

        System.out.println("[*] Initiating Cassandra Connection");
        cassandraManager.setup();
        System.out.println("[+] Cassandra Connection Successful");

        System.out.println("[*] Attempting to Get Active Customer List");
        ResultSet resultSet = cassandraManager.getActiveCustomers();
        System.out.println("[+] Successfully Fetched Active Customer List");

        System.out.println("=================================================");
        System.out.println(resultSet);
        resultSet.getColumnDefinitions();
        CassandraRW.printResult(resultSet);
        System.out.println("=================================================");

        System.out.println("[*] Attempting to Write New Customer To DB");
//        cassandraManager.writeCustomer((int) (20 + Math.floor((Math.random() * 100) + 1)), "bogus@elixer.xom", "Waerling Vortol't" + Math.floor(Math.random() * 10), new BigInteger(String.valueOf(555234423)), "Testing the waters");
//        cassandraManager.writeCustomer(4242, "imr@an.com", "Imran Shaikh", new BigInteger(String.valueOf(555222333)), "Looking around");
        System.out.println("[+] Successfully Wrote New Customer To DB");
    }



}
