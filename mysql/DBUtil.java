package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    
    private DBUtil() { }

    private static ConnectionPool connectionPool = ConnectionPool.getInstance();

    public static Connection getConnection() throws SQLException {
        Connection connection = connectionPool.checkOut();
        return connection;
    }

    public static void close(Connection connection){
        if(connection != null){
            connectionPool.checkIn(connection);
        }
    }

    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement, ResultSet resultSet){
        close(statement);
        close(resultSet);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        close(connection);
        close(statement);
        close(resultSet);
    }

    public static String preparePattern(String text) {
		return text.replace('*', '%').replace('?', '_');
	}
}
