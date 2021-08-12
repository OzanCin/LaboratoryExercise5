package com.example.LaboratoryExercise4;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements DatabaseInterface {

    Connection con;

    public DatabaseService(Connection con) {
        this.con = con;
    }
    @Override
    public void add(Category cat) throws ClassNotFoundException, SQLException {
        String quer1 = "INSERT INTO category VALUES ( ?, ? )";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, cat.getCatcode());
        query.setString(2, cat.getCatdesc());
        query.executeUpdate();
    }

    @Override
    public Category edit(Category cat, String catcode) throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        query = con.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?");
        query.setString(1, cat.getCatcode());
        query.setString(2, cat.getCatdesc());
        query.setString(3, catcode);
        query.executeUpdate(); return cat;
    }

    @Override
    public void delete(String catcode) throws SQLException {
    String quer1 = "Delete from Category where catcode = ?";
    PreparedStatement query = con.prepareStatement(quer1);
    query.setString(1, catcode);
    query.executeUpdate();
    }

    public Category search(String catcode) throws SQLException,ClassNotFoundException {
        String quer1 = "Select * from category where catcode = ?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, catcode);
        ResultSet rs = query.executeQuery();
        if(!rs.first()){ System.out.print("Record not existing");
            return null;
        }
        Category obj1=null;
        obj1 = new Category(rs.getString("catcode"), rs.getString("catdesc"));
        return obj1;
    }

    @Override
    public List<Category> display() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Category> Catlist = new ArrayList<Category>();
        String quer1 = "Select * from category";

    PreparedStatement query = con.prepareStatement(quer1);
    ResultSet rs = query.executeQuery();
    Category obj1;
    //display records if there is data;
    while (rs.next()) {
        obj1 = new Category(rs.getString("catcode"), rs.getString("catdesc"));
        Catlist.add(obj1);
    }
    return Catlist;
}

    public List<Items> display2(String catcode) throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Items> Itemlist = new ArrayList<Items>();
        String quer1 = "Select itemcode,itemdesc from items where catcode=?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, catcode);
        ResultSet rs = query.executeQuery();
        Items obj2;
        //display records if there is data;
        while (rs.next()) {
            obj2 = new Items(rs.getString("itemcode"),rs.getString("itemdesc"));
            Itemlist.add(obj2);
        } return Itemlist;
    }
}
