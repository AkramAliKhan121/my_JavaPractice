package com.edubridge.contact_app.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edubridge.contact_app.model.Contact;
import com.edubridge.contact_app.util.DBUtils;

public class ContactDaoImpl implements ContactDao {

	private static final String SELECT_BY_NAME = null;
	private static final Contact Contacts = null;
	private Contact c11;

		
	@Override
	public List<Contact> getAllContacts() {
		
			String SELECT = "select * from contact";
			Connection con = DBUtils.getconnection();
			List<Contact> contacts = new ArrayList<Contact>();
			
			try {
				PreparedStatement ps = con.prepareStatement(SELECT);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Contact c = new Contact();
					c.setId(rs.getInt("id"));
					c.setName(rs.getString("name"));
					c.setEmail(rs.getString("email"));
					c.setMobile(rs.getLong("mobile"));
					contacts.add(c);
				}
			
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return contacts;}
	

	@Override
	public Contact getContact(String searchName) {

			        Connection con = DBUtils.getconnection();
		            String SELECT_BY_NAME = "SELECT * FROM contact WHERE name=?";

		            
		            Contact  c1 = new Contact();
			        try {
			            PreparedStatement ps = con.prepareStatement(SELECT_BY_NAME);
			            ps.setString(1, searchName);
			            ResultSet rs = ps.executeQuery();
			            if (rs.next()) {
			                c1.setId(rs.getInt("id"));
			                c1.setName(rs.getString("name"));
			                c1.setEmail(rs.getString("email"));
			                c1.setMobile(rs.getLong("mobile"));
			            }
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
					return c1;
		}

	@Override
	public int addContact(Contact c) {

			String INSERT = "insert into  contact(id, name, email, mobile) values(?,?,?,?)";
			
			Connection con = DBUtils.getconnection();
			
			int status = 0;
			
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setInt(1, c.getId());
				ps.setString(2, c.getName());
				ps.setString(3, c.getEmail());
				ps.setLong(4, c.getMobile());			
				
				status = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block.
				e.printStackTrace();
			}
			
			return status;
		}
		

	@Override
	public int updateContact(Contact c) {
		 String UPDATE = "update contact set name = ?, email = ?, mobile = ? where id = ?";
		    Connection con = DBUtils.getconnection();
		    int status = 0;
		    try {
		        PreparedStatement ps = con.prepareStatement(UPDATE);
		        ps.setString(1, c.getName());
		        ps.setString(2, c.getEmail());
		        ps.setLong(3, c.getMobile());
		        ps.setInt(4, c.getId());
		        status = ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return status;

	}
	
	
	@Override
	public Contact deleteContact(String contactName) {
	    String DELETE = "DELETE FROM contact WHERE name = ?";
	    Connection con = null;
	    PreparedStatement ps = null;
	    Contact deletedContact = null;

	    try {
	        con = DBUtils.getconnection();
	        ps = con.prepareStatement(DELETE);
	        ps.setString(1, contactName);
	        int status = ps.executeUpdate();

	        if (status > 0) {
	            deletedContact = new Contact();
	            deletedContact.setName(contactName); // Assuming there is a setName method in Contact class
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return deletedContact;
	}



	@Override
	public void deleteAllContacts() {
		
		 String DELETE_ALL = "truncate table contact";
		    Connection con = DBUtils.getconnection();
		    try {
		        PreparedStatement ps = con.prepareStatement(DELETE_ALL);
		        ps.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		




	

	
	
}
