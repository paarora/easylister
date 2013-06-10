package com.cts.app.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHelper {
	Connection con = null;
	PreparedStatement pst = null;
	Statement st = null;
	boolean rs = false;

	String url = "jdbc:mysql://localhost:3306/INVENTORY";
	String user = "community";
	String password = "asdf1234";

	public static void main(String[] args) {
		
		  TransactionRecord record = new TransactionRecord();
		  
		  record.setListingPrice(1000.0); record.setSourceUrl("");
		  record.setTargetUrl("http://test"); record.setUserid("testUser");
		  System.out.println(new DatabaseHelper().insertRecord(record));
		 

		//List<TransactionRecord> records = new DatabaseHelper()
		//		.readRecords("testUser");
		//System.out.println(records.size());
	}

	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			con = DriverManager.getConnection(url, user, password);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int insertRecord(TransactionRecord record) {
		int transactionId = 0;
		try {
			init();
			pst = con
					.prepareStatement(
							"INSERT INTO INVENTORY.items (userid, listing_price, source_url, target_url, created_date) VALUES (?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, record.getUserid());
			pst.setDouble(2, record.getListingPrice());
			pst.setString(3, record.getSourceUrl());
			pst.setString(4, record.getTargetUrl());
			pst.setDate(5, new Date(new java.util.Date().getTime()));
			pst.execute();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				transactionId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			transactionId = 0;
		} finally {
			try {

				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return transactionId;
	}

	public List<TransactionRecord> readRecords(String userId) {
		List<TransactionRecord> transRecords = new ArrayList<TransactionRecord>();
		ResultSet rs = null;
		try {
			init();
			st = con.createStatement();
			rs = st.executeQuery("SELECT	* FROM INVENTORY.items where userid='"
					+ userId + "'");

			while (rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getLong(1));
				record.setUserid(rs.getString(2));
				record.setListingPrice(rs.getDouble(3));
				record.setSourceUrl(rs.getString(4));
				record.setTargetUrl(rs.getString(5));
				record.setDate(rs.getDate(6));
				transRecords.add(record);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return transRecords;
	}
	
	public List<TransactionRecord> getRecord(String transId) {
		List<TransactionRecord> transRecords = new ArrayList<TransactionRecord>();
		ResultSet rs = null;
		try {
			init();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM INVENTORY.items where transaction_id='"
					+ transId + "'");

			while (rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getLong(1));
				record.setUserid(rs.getString(2));
				record.setListingPrice(rs.getDouble(3));
				record.setSourceUrl(rs.getString(4));
				record.setTargetUrl(rs.getString(5));
				record.setDate(rs.getDate(6));
				transRecords.add(record);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return transRecords;
	}
	
	public List<TransactionRecord> getAll() {
		List<TransactionRecord> transRecords = new ArrayList<TransactionRecord>();
		ResultSet rs = null;
		try {
			init();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM INVENTORY.items");

			while (rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getLong(1));
				record.setUserid(rs.getString(2));
				record.setListingPrice(rs.getDouble(3));
				record.setSourceUrl(rs.getString(4));
				record.setTargetUrl(rs.getString(5));
				record.setDate(rs.getDate(6));
				transRecords.add(record);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return transRecords;
	}
	
	public List<TransactionRecord> getHighPriceRecord() {
		List<TransactionRecord> transRecords = new ArrayList<TransactionRecord>();
		ResultSet rs = null;
		try {
			init();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM INVENTORY.items where listing_price>'24999.9'");

			while (rs.next()) {
				TransactionRecord record = new TransactionRecord();
				record.setTransactionId(rs.getLong(1));
				record.setUserid(rs.getString(2));
				record.setListingPrice(rs.getDouble(3));
				record.setSourceUrl(rs.getString(4));
				record.setTargetUrl(rs.getString(5));
				record.setDate(rs.getDate(6));
				transRecords.add(record);
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return transRecords;
	}
}
