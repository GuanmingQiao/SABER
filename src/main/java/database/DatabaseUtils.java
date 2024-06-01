package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.mysql.jdbc.Statement;

import main.Main.UseCase;
import pojo.IORecord;

public class DatabaseUtils {
	
	private static final String DATABASE_NAME = "saberResults";
	private static final String OIR_TABLE_NAME = "originalIORecords";
	private static final String NIR_TABLE_NAME = "newIORecords";
	private static final String MS_TABLE_NAME = "methodSimilarity";

	public static Connection getConnection(String url, String username, String password) {
		Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
            System.err.println("Reporting database and username: " + url + " " + username);
        }
        return connection;
    }
	
	public static void createDatabaseAndTables(Connection connection) {
		String query1 = "DROP DATABASE IF EXISTS " + DATABASE_NAME;
		String query2 = "CREATE DATABASE " + DATABASE_NAME;
		String query3 = "DROP TABLE IF EXISTS " + DATABASE_NAME + "." + OIR_TABLE_NAME; 
		String query4 = "CREATE TABLE " + DATABASE_NAME + "." + OIR_TABLE_NAME + " (\n" + 
				"  `id` int(11) NOT NULL,\n" + 
				"  `methodKey` varchar(256) CHARACTER SET utf8mb4 NOT NULL,\n" + 
				"  `stackInfo` varchar(256) NOT NULL,\n" + 
				"  `recordID` int(11) NOT NULL,\n" + 
				"  `stamp` varchar(256) NOT NULL," +
				"  PRIMARY KEY (`id`),\n" + 
				"  KEY `methodKey_idx` (`methodKey`,`stackInfo`)\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
				"";
		String query5 = "DROP TABLE IF EXISTS " + DATABASE_NAME + "." + NIR_TABLE_NAME; 
		String query6 = "CREATE TABLE " + DATABASE_NAME + "." + NIR_TABLE_NAME + " (\n" + 
				"  `id` int(11) NOT NULL,\n" +
				"  `methodKey` varchar(256) CHARACTER SET utf8mb4 NOT NULL,\n" + 
				"  `stackInfo` varchar(256) NOT NULL,\n" + 
				"  `recordID` int(11) NOT NULL,\n" + 
				"  `stamp` varchar(256) NOT NULL," +
				"  PRIMARY KEY (`id`),\n" + 
				"  KEY `methodKey_idx` (`methodKey`,`stackInfo`)\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" + 
				"";
		String query7 = "DROP TABLE IF EXISTS " + DATABASE_NAME + "." + MS_TABLE_NAME;
		String query8 = "CREATE TABLE " + DATABASE_NAME + "." + MS_TABLE_NAME + " (\n" + 
				"  `id` int(11) NOT NULL,\n" + 
				"  `useCaseType` enum ('DEBUG', 'BASELINE', 'ADD_FEATURE', 'REPLACE'),\n" +
				"  `method1Key` varchar(256) NOT NULL,\n" + 
				"  `method2Key` varchar(256) NOT NULL,\n" + 
				"  `ioSim` double DEFAULT NULL,\n" + 
				"  `metapropSim` double DEFAULT NULL,\n" + 
				"  `extraceSim` double DEFAULT NULL,\n" + 
				"  `complexity` double DEFAULT NULL,\n" + 
				"  PRIMARY KEY (`id`)" +
				") ENGINE=InnoDB DEFAULT CHARSET=latin1;\n" +
				"";
		Statement statement;
		try {
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(query1);
			statement.executeUpdate(query2);
			statement.executeUpdate(query3);
			statement.executeUpdate(query4);
			statement.executeUpdate(query5);
			statement.executeUpdate(query6);
			statement.executeUpdate(query7);
			statement.executeUpdate(query8);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addOriginalIORecord(Connection connection, IORecord ioRecord) {
		String query1 = "SELECT * FROM " + DATABASE_NAME + "." + OIR_TABLE_NAME;
		String subQuery = "(SELECT MAX(id) FROM (SELECT * FROM " + DATABASE_NAME + "." + OIR_TABLE_NAME + ") AS A)";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query1);
			resultSet.last();
			String query2 = "INSERT INTO " + DATABASE_NAME + "." + OIR_TABLE_NAME + "(\n" + 
					"`id`,\n" + 
					"`methodKey`,\n" + 
					"`stackInfo`,\n" + 
					"`recordID`,\n" + 
					"`stamp`)\n" + 
					"VALUES\n" + 
					"(" + (resultSet.getRow() == 0 ? "1" : subQuery + " + 1") + ",\n" + 
					"\"" + ioRecord.methodKey + "\",\n" + 
					"\"" + ioRecord.stackInfo + "\",\n" + 
					ioRecord.id + ",\n" + 
					"\"" + ioRecord.stamp + "\");";
			statement.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addNewIORecord(Connection connection, IORecord ioRecord) {
		String query1 = "SELECT * FROM " + DATABASE_NAME + "." + NIR_TABLE_NAME;
		String subQuery = "(SELECT MAX(id) FROM (SELECT * FROM " + DATABASE_NAME + "." + NIR_TABLE_NAME + ") AS A)";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query1);
			resultSet.last();
			String query2 = "INSERT INTO " + DATABASE_NAME + "." + NIR_TABLE_NAME + "(\n" + 
					"`id`,\n" + 
					"`methodKey`,\n" + 
					"`stackInfo`,\n" + 
					"`recordID`,\n" + 
					"`stamp`)\n" + 
					"VALUES\n" + 
					"(" + (resultSet.getRow() == 0 ? "1" : subQuery + " + 1") + ",\n" + 
					"\"" + ioRecord.methodKey + "\",\n" + 
					"\"" + ioRecord.stackInfo + "\",\n" + 
					ioRecord.id + ",\n" + 
					"\"" + ioRecord.stamp + "\");";
			statement.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addMethodSimilarity(Connection connection,
										   String method1Key,
										   String method2Key,
										   double ioSim,
										   double metapropSim,
										   double extraceSim,
										   double complexity,
										   UseCase useCase) {
		String query1 = "SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME;
		String subQuery = "(SELECT MAX(id) FROM (SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME + ") AS A)";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query1);
			resultSet.last();
			String query2 = "INSERT INTO " + DATABASE_NAME + "." + MS_TABLE_NAME + "(\n" + 
					"`id`,\n" +
					"`useCaseType`,\n" +
					"`method1Key`,\n" + 
					"`method2Key`,\n" + 
					"`ioSim`,\n" + 
					"`metapropSim`,\n" + 
					"`extraceSim`,\n" + 
					"`complexity`)\n" +
					"VALUES\n" + 
					"(" + (resultSet.getRow() == 0 ? "1" : subQuery + " + 1") + ",\n" + 
					"\"" + useCase.name() + "\",\n" +
					"\"" + method1Key + "\",\n" + 
					"\"" + method2Key + "\",\n" + 
					ioSim + ",\n" + 
					metapropSim + ",\n" + 
					extraceSim + ",\n" + 
					complexity + ");";
			statement.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public static void sortByLeastComplexity(Connection connection,
										double ioSimThreshold,
										double metapropSimThreshold,
										double extraceSimThreshold) {
		String query = "SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME +
				" WHERE ioSim >= " + ioSimThreshold +
				" AND metapropSim >= " + metapropSimThreshold +
				" AND extraceSim >= " + extraceSimThreshold +
				" ORDER BY complexity";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void sortByIOSimilarity(Connection connection,
										  double ioSimThreshold,
										  double metapropSimThreshold,
										  double extraceSimThreshold) {
		String query = "SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME +
				" WHERE ioSim >= " + ioSimThreshold +
				" AND metapropSim >= " + metapropSimThreshold +
				" AND extraceSim >= " + extraceSimThreshold +
				" ORDER BY ioSim DESC";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void sortByMPSimilarity(Connection connection,
										  double ioSimThreshold,
										  double metapropSimThreshold,
										  double extraceSimThreshold) {
		String query = "SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME +
				" WHERE ioSim >= " + ioSimThreshold +
				" AND metapropSim >= " + metapropSimThreshold +
				" AND extraceSim >= " + extraceSimThreshold +
				" ORDER BY metapropSim DESC";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void sortByExTraceSimilarity(Connection connection,
										       double ioSimThreshold,
										       double metapropSimThreshold,
										       double extraceSimThreshold) {
		String query = "SELECT * FROM " + DATABASE_NAME + "." + MS_TABLE_NAME +
				" WHERE ioSim >= " + ioSimThreshold +
				" AND metapropSim >= " + metapropSimThreshold +
				" AND extraceSim >= " + extraceSimThreshold +
				" ORDER BY extraceSim DESC";
		Statement statement;
		ResultSet resultSet;
		try {
			statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/";
		String username = "saber";
		String password = "saber123";
		Connection connection = DatabaseUtils.getConnection(url, username, password);
//		DatabaseUtils.createDatabaseAndTables(connection);
		IORecord ioRecord = new IORecord();
		ioRecord.id = 23;
		ioRecord.methodKey = "some method";
		ioRecord.stackInfo = "some stack info";
		ioRecord.sortedInputs.add(5);
		ioRecord.sortedInputs.add("hi");
		ioRecord.sortedOutputs.add("bye");
		ioRecord.sortedOutputs.add(7.3);
		List<Integer> a = new ArrayList<>();
		a.add(1); a.add(92); a.add(-35);
		ioRecord.sortedOutputs.add(a);
		ioRecord.stamp = "321jkl234";
//		DatabaseUtils.addOriginalIORecord(connection, ioRecord);
//		DatabaseUtils.addNewIORecord(connection, ioRecord);
//		String stamp = new Random().nextInt(Integer.MAX_VALUE) + "_" + new Date().getTime();
//		System.out.println(stamp);
	}
}