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
		/*TransactionRecord record = new TransactionRecord();
		
		record.setListingPrice(1000.0);
		record.setSourceUrl("");
		record.setTargetUrl("http://test");
		record.setUserid("testUser");
		new DatabaseHelper().insertRecord(record);
		*/
		List<TransactionRecord> records = new DatabaseHelper().readRecords("testUser");
		System.out.println(records.size());
	}

	public boolean insertRecord(TransactionRecord record) {
		boolean isTransactionSuccessfull = false;
		try {
			con = DriverManager.getConnection(url, user, password);
			pst = con
					.prepareStatement("INSERT INTO INVENTORY.items (userid, listing_price, source_url, target_url, created_date) VALUES (?,?,?,?,?)");
			pst.setString(1, record.getUserid());
			pst.setDouble(2, record.getListingPrice());
			pst.setString(3, record.getSourceUrl());
			pst.setString(4, record.getTargetUrl());
			pst.setDate(5, new Date(new java.util.Date().getTime()));
			pst.execute();
			isTransactionSuccessfull = true;
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DatabaseHelper.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			isTransactionSuccessfull = false;
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
		return isTransactionSuccessfull;
	}

	public List<TransactionRecord> readRecords(String userId) {
		List<TransactionRecord> transRecords = new ArrayList<TransactionRecord>();
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			rs = st.executeQuery("SELECT	* FROM INVENTORY.items where userid='"+userId+"'");
			
			while(rs.next()){
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
				if(rs!=null) {
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
