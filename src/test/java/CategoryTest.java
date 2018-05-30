import com.example.model.CategoryModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.*;

public class CategoryTest {
    String name = "category_name";

    @Before
    public void beforeMethod() throws SQLException {
        CategoryModel userModel = CategoryModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }

    }

    @After
    public void afterMethod() throws SQLException {
        CategoryModel userModel = CategoryModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }
    }

    @Test
    public void insertTest() throws SQLException {
        CategoryModel userModel = new CategoryModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CategoryModel resultModel = CategoryModel.findByName(name);
        assertNotNull(resultModel);
        assertEquals(resultModel.getName(),name);
    }


    @Test
    public void updateTest() throws Exception {
        String name2 = "blabla";
        CategoryModel userModel = new CategoryModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        userModel.setName(name2);
        userModel.update();

        CategoryModel resultModel = CategoryModel.findByName(name2);
        assertEquals(resultModel.getName(), name2);
    }

    @Test
    public void deleteTest() throws Exception {
        CategoryModel userModel = new CategoryModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CategoryModel tempModel = CategoryModel.findByName(name);
        if (tempModel != null)
            tempModel.delete();
        CategoryModel resultModel = CategoryModel.findByName(name);
        assertNull(resultModel);
    }

    @Test
    public void selectSingleTest() throws Exception {
        CategoryModel userModel = new CategoryModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CategoryModel resultTest = CategoryModel.findByName(name);
        assertNotNull(resultTest);
    }

    @Test
    public void selectByIdTest() throws Exception {
        CategoryModel userModel = new CategoryModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        long id = userModel.getId();
        assertTrue(userModel.isSaved());
        CategoryModel resultTest = CategoryModel.findByID(id);
        assertNotNull(resultTest);
        assertEquals(userModel.getName(),resultTest.getName());
        assertEquals(userModel.getUUID(),resultTest.getUUID());
    }

}
