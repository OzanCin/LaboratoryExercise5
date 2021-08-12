import com.example.LaboratoryExercise4.Category;
import com.example.LaboratoryExercise4.Connection123;
import com.example.LaboratoryExercise4.DatabaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class DAOImplementsTest {
    @InjectMocks
    private DatabaseService daoImplements;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private Connection123 connection1;

    Category category;

    @BeforeEach
    public void Setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(connection1.connect()).thenReturn(connection);
        category=new Category("101","food");
    }

    @Test
    void add() throws SQLException, ClassNotFoundException {
        when(connection.prepareStatement("INSERT INTO Category VALUES ( ?, ? )")).thenReturn(preparedStatement);
        daoImplements.add(category);
        verify(preparedStatement).executeUpdate();

    }

    @Test
    void search() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("catcode")).thenReturn("101");
        when(resultSet.getString("catdesc")).thenReturn("food");
        when(resultSet.first()).thenReturn(true);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        Category categ=daoImplements.search("101");
        assertEquals("101",categ.getCatcode());

    }
    @Test
    void SearchIfNoRecord() throws SQLException, ClassNotFoundException {
        when(connection.prepareStatement("Select * from category where catcode = ?")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("catcode")).thenReturn("norecord");
        when(resultSet.getString("catdesc")).thenReturn("norecord");


        Category categ=daoImplements.search("norecord");
        Assertions.assertNull(categ);

    }

    @Test
    void edit() throws SQLException, ClassNotFoundException {
//        when(resultSet.next()).thenReturn(true).thenReturn(false);
//        when(resultSet.getString("catcode")).thenReturn("101");
//        when(resultSet.getString("catdesc")).thenReturn("food");
//        when(resultSet.first()).thenReturn(true);
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//
//        Category categ=daoImplements.search("101");
        category=new Category("101","Food&Bev");
        Category cat1=daoImplements.edit(category,"101");
        assertEquals("Food&Bev",cat1.getCatdesc());
    }

    @Test
    void delete() throws SQLException {
        when(connection.prepareStatement("Delete from Category where catcode = ?")).thenReturn(preparedStatement);
        daoImplements.delete("101");
        verify(preparedStatement).executeUpdate();

    }

    @Test
    void display() throws SQLException, ClassNotFoundException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("catcode")).thenReturn("101");
        when(resultSet.getString("catdesc")).thenReturn("food");
        when(resultSet.first()).thenReturn(true);

        doReturn(resultSet).when(preparedStatement).executeQuery();
        daoImplements.display();
        verify(preparedStatement).executeQuery();

    }
}

