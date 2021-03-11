package astro.database;

public class Database {

        var url = "jdbc:h2:tcp://localhost:9092/~/tmp/h2dbs/testdb";
        var user = "sa";
        var passwd = "s$cret";

        var query = "SELECT * FROM cars";

        try (var con = DriverManager.getConnection(url, user, passwd);
             var st = con.createStatement();
             var rs = st.executeQuery(query)) {

            while (rs.next()) {

                System.out.printf("%d %s %d%n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3));
            }

        } catch (SQLException ex) {

            var lgr = Logger.getLogger(JavaSeH2Server.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

}
