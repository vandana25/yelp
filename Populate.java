/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

/**
 *
 * @author vandana
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DbConnection {

    static Connection sampleDBconn = null;

    Connection conn = null;

    void getConnection() {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_URL = "jdbc:mysql://localhost/";

        Statement stmt = null;
        String user = "DBHW";
        String password = "25@Scooty";
        String port = "1521";
        String DBname = "SYSTEM";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed!" + e);
            return;
        }
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            System.out.println("Creating database...");
            System.out.println("Database created successfully...");

        } catch (SQLException se) {
            System.out.println("Connection failed!" + se);
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        }
    }//end try
}

public class Populate {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        DbConnection dbconn = new DbConnection();
        dbconn.getConnection();
        Connection conn2 = dbconn.conn;
 
        //insert into yelp user
        
        	try {
			//	Connection conn2 = dbconn.conn;
		String insertUsers = ("INSERT INTO YELP_USERS VALUES (?,?,?,?,?,?,?,?,?,?,?)");
	          
		           PreparedStatement psUser = conn2.prepareStatement(insertUsers);
				Object obj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//yelp_user.json"));
	//			Object obj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//newdata.json"));
				 
                                JSONArray jsonArray = (JSONArray) obj;  
				int countusersinserts=0;
				 
				 for(int i=0;i<jsonArray.size();i++)
				 {
                                     countusersinserts++;
					 JSONObject temp = (JSONObject) jsonArray.get(i);
					 JSONObject votesObject = (JSONObject) temp.get("votes");
					 JSONArray friendsarray = (JSONArray) temp.get("friends");			
					 String yelping_since = (String)temp.get("yelping_since");
					 StringBuffer replacement = new StringBuffer();
					 replacement.append(yelping_since+"-01");
					 Date Yelpdate =  (Date)java.sql.Date.valueOf(replacement.toString());
                  
				 Long review_count =(Long) temp.get("review_count");
		                String name = (String)temp.get("name");
		                String user_id = (String)temp.get("user_id");
		                Long fans = (Long)temp.get("fans");
                                 Long Funny =(Long) votesObject.get("funny");
                                 Long Useful =(Long) votesObject.get("useful");
                                Long cool =(Long) votesObject.get("cool");
		                Double average_stars  = (Double)temp.get("average_stars");
                               
		                String type = (String)temp.get("type");	 
					 //record of friends
                                         int Count_friends=0;
		                List<String> list = new ArrayList<String>();
		                for(int j = 0; j < friendsarray.size(); j++){
		                    list.add((String)friendsarray.get(j));
		                }
		               if (!list.isEmpty()){
		            	   for (int j = 1; j <= list.size(); j++) {
			            	  Count_friends++;	
						}
		               }
		               
		                psUser.setDate(1, Yelpdate);
		                psUser.setLong(2, review_count);
		                psUser.setString(3, name);
		                psUser.setString(4, user_id);
		                psUser.setLong(5, fans);        
		                psUser.setDouble(6, average_stars);
                                 psUser.setLong(7, Count_friends);  
		                psUser.setString(8, type);
                                 psUser.setLong(9, Funny);
                                    psUser.setLong(10, Useful);
                                      psUser.setLong(11, cool);
	                
		                psUser.executeUpdate();	
                                 System.out.println("number of inserts for yelp_users table   " +countusersinserts);
				 }
			 System.out.println("parsed");	 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                                   
         
 		 //insert into business table
			 try {
		 String insertBusiness = ("INSERT INTO BUSINESS VALUES (?,?,?,?,?,?,?,?,?)");
         String insertsubcategories = ("INSERT INTO Subcategories VALUES (?,?,?)");
	           PreparedStatement psBusiness = conn2.prepareStatement(insertBusiness);
	          PreparedStatement psSubCat = conn2.prepareStatement(insertsubcategories);
			Object businessObj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//yelp_business.json"));
		//	Object businessObj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//smallbusiness.json"));
			
                        JSONArray jsonArray = (JSONArray) businessObj;  
			 int count=0;
			 for(int i=0;i<jsonArray.size();i++)
			 {
                             count++;
				 JSONObject temp = (JSONObject) jsonArray.get(i);
				 JSONArray categoryarray = (JSONArray) temp.get("categories");
				 
			     String Business_id = (String)temp.get("business_id");
			     String city =(String) temp.get("city");
			     String state =(String) temp.get("state");
				 Double latitude =(Double) temp.get("latitude");
				 Double longitude =(Double) temp.get("longitude");
				 Long business_review_count =(Long) temp.get("review_count");
				 Double stars =(Double) temp.get("stars");
	             String Business_name = (String)temp.get("name");
	             String type = (String)temp.get("type");
	         //       Double average_stars  = (Double)temp.get("average_stars");  

				 //listing category with subcategory
	               List<String> list = new ArrayList<String>();
	                for(int j = 0; j < categoryarray.size(); j++){
	                    list.add((String)categoryarray.get(j));
	                }
	               List<String> categorieslist = new ArrayList<>();
	                categorieslist.add("Active Life");
	                categorieslist.add("Arts & Entertainment");
	                categorieslist.add("Automotive");	
	                categorieslist.add("Car Rental");
	                categorieslist.add("Cafes");
	                categorieslist.add("Beauty & Spas");
	                categorieslist.add("Convenience Stores");
	                categorieslist.add("Dentists");
	                categorieslist.add("Doctors");
	                categorieslist.add("Drugstores");
	                categorieslist.add("Department Stores");
	                categorieslist.add("Education");
	                categorieslist.add("Event Planning & Services");
	                categorieslist.add("Flowers & Gifts");
	                categorieslist.add("Food");
	                categorieslist.add("Health & Medical");
	                categorieslist.add("Home Services");
	                categorieslist.add("Home & Garden");
	                categorieslist.add("Hospitals");
	                categorieslist.add("Hotels & Travel");
	                categorieslist.add("Hardware Stores");
	                categorieslist.add("Grocery");
	                categorieslist.add("Medical Centers");
	                categorieslist.add("Nurseries & Gardening");
	                categorieslist.add("Nightlife");
	                categorieslist.add("Restaurants");
	                categorieslist.add("Shopping");
	                categorieslist.add("Transportation"); 
	                ArrayList<String> listofcat = new ArrayList<>();
	            	   ArrayList<String> listofsubcat = new ArrayList<>();
	               if (!list.isEmpty()){
	            	   for (int j = 0; j < list.size(); j++) {
		            	   String temp1=list.get(j);
		            	  
		            	   if(categorieslist.contains(temp1)){
		            		listofcat.add(temp1);
		            	   }
		            	   else {
		            		  listofsubcat.add(temp1);
		            		   }
	            	   }
		            	   for (String str: listofcat){
		            		   if (!listofsubcat.isEmpty()){
		            		   for (String substr: listofsubcat){
		      
		            			   psSubCat.setString(1, Business_id);
		            			   psSubCat.setString(2, str);
		            			   psSubCat.setString(3, substr);
		            			   psSubCat.executeUpdate();
		            		   }
		            		   }
		            		   else{
		            		   psSubCat.setString(1, Business_id);
	            			   psSubCat.setString(2, str);
	            			   psSubCat.setString(3, null);
	            			   psSubCat.executeUpdate();}
		            	   } 	   
					}
	               	 psBusiness.setString(1, Business_id);
		             psBusiness.setString(2, city);
		             psBusiness.setString(3, state);
		             psBusiness.setDouble(4, latitude);
		             psBusiness.setDouble(5, longitude);
		             psBusiness.setLong(6, business_review_count);
		             psBusiness.setDouble(7, stars);
		             psBusiness.setString(8, Business_name);
		             psBusiness.setString(9, type);
		             psBusiness.executeUpdate();
                              System.out.println(count+"   no of business inserts");	
		 }
	             
	 
		 System.out.println("inserted into business table");	 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}                                   
         
 
//insert into checkin 
		 try {
			 System.out.println("in checkin block");
			 String insertCheckin = ("INSERT INTO CHECKIN VALUES (?,?,?,?,?)");
		           PreparedStatement psCheckin = conn2.prepareStatement(insertCheckin);
				Object CheckinObj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//yelp_checkin.json"));
		//		Object CheckinObj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//smallcheckin.json"));
				
                                JSONArray jsonArray = (JSONArray) CheckinObj;  
				 int checkincount=0;
				 for(int i=0;i<jsonArray.size();i++)
				 {
                                     checkincount++;
					 JSONObject temp = (JSONObject) jsonArray.get(i);	 
					  JSONObject checkinInfo = (JSONObject) temp.get("checkin_info");
					  
				     String Business_id = (String)temp.get("business_id");
		             String type = (String)temp.get("type"); 
		             psCheckin.setString(1, Business_id);
		             psCheckin.setString(5, type);
		             for(int k=0;k<7;k++)
		                {
		                    for(int j=0;j<24;j++)
		                    {
		                        String temp_key=j+"-"+k;
		                        if(checkinInfo.containsKey(temp_key))
		                        {
		                            long num_checkin = (long)checkinInfo.get(temp_key);
		                            psCheckin.setLong(2, (long)j); // hours
		                            psCheckin.setLong(3, (long)k); //day
		                            psCheckin.setLong(4,num_checkin );                          
		                            psCheckin.executeUpdate();
		                             System.out.println(checkincount+"num of checkins");
		                        }
		                    }
		                }
			 }
	                   
			 System.out.println("checkin parsed");	 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        
               //insert review tables
      
 
        String insertUsers = ("INSERT INTO REVIEWS VALUES (?,?,?,?,?,?,?,?,?,?)");
        Object obj;
        int countreviewinserts =0;
        try {
            PreparedStatement psReview = conn2.prepareStatement(insertUsers);
            obj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//yelp_review.json"));
           // obj = parser.parse(new FileReader("c://Users//vandana//Desktop//db Archi//json files//smallreview.json"));
           
            JSONArray jsonReviewArray = (JSONArray) obj;

            for (int i = 0; i < jsonReviewArray.size(); i++) {
                countreviewinserts++;
                JSONObject temp = (JSONObject) jsonReviewArray.get(i);
                JSONObject reviewVotesObject = (JSONObject) temp.get("votes");
                Long funny = (Long) reviewVotesObject.get("funny");
                Long useful = (Long) reviewVotesObject.get("useful");
                Long cool = (Long) reviewVotesObject.get("cool");
                String user_id = (String) temp.get("user_id");
                String review_id = (String) temp.get("review_id");
                Long stars = (Long) temp.get("stars");
                String date = (String) temp.get("date");
                Date ReviewDate = (Date) java.sql.Date.valueOf(date);
                String text = (String) temp.get("text");
                System.out.println("text lenth "+text.length());
                if (text.length() > 2000) {
                    text = text.substring(0, 1999);
                }
            System.out.println("new text lenth "+text.length());
                String type = (String) temp.get("type");
                String business_id = (String) temp.get("business_id");

                psReview.setLong(1, funny);
                psReview.setLong(2, useful);
                psReview.setLong(3, cool);
                psReview.setString(4, user_id);
                psReview.setString(5, review_id);
                psReview.setLong(6, stars);
                psReview.setDate(7, ReviewDate);
                psReview.setString(8, text);
                psReview.setString(9, type);
                psReview.setString(10, business_id);

                psReview.executeUpdate();
                   System.out.println("number of review inserts"  + countreviewinserts);
            }

         

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   

  //      drop and create db
        try {
            Statement stm = conn2.createStatement();

            stm.executeQuery("drop table YELP_USERS");
            stm.executeQuery("drop table reviews");
            stm.executeQuery("drop table BUSINESS");
            stm.executeQuery("drop table SUBCATEGORIES");
            stm.executeQuery("drop table checkin");
            stm.executeQuery("drop table friends");
            System.out.println("all tables dropped");
            stm.executeQuery("CREATE TABLE YELP_USERS (yelping_since DATE,review_count NUMBER,name varchar2(100),user_id varchar2(100) PRIMARY KEY,fans NUMBER,average_stars NUMBER,Count_Friends NUMBER,type varchar2(50),Funny NUMBER,USEFUL NUMBER,COOL NUMBER)");
            System.out.println("Table Yelp_users Created");
            stm.executeQuery("create table checkin (business_id varchar2(100),hours integer,day integer,numcheckin integer,type varchar2(100))");
            System.out.println("Table CHECKIN Created");
            stm.executeQuery("CREATE TABLE FRIENDS(user_id varchar2(100),friends_id varchar2(100))");
            System.out.println("Table FRIENDS Created");
            stm.executeQuery("CREATE TABLE BUSINESS (Business_id varchar2(100) PRIMARY KEY,city varchar2(100),state varchar2(100),latitude NUMBER,longitude NUMBER,review_count NUMBER,stars varchar2(100),Business_name varchar2(100),type varchar2(100))");
            System.out.println("BUSINESS TABLE Created");
            stm.executeQuery("CREATE TABLE SUBCATEGORIES (Business_id varchar2(100),Category varchar2(100),Subcategory varchar2(100))");
            System.out.println("SUBCATEGORIES TABLE Created");
            stm.executeQuery("create table reviews(funny NUMBER,useful NUMBER,cool NUMBER,user_id varchar2(100),review_id varchar2(100),stars NUMBER,ReviewDate Date,text varchar2(2000),type varchar2(50),business_id varchar2(100))");
            System.out.println("reviews TABLE Created");
            

                 
  

          
            
 stm.close();
conn2.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
