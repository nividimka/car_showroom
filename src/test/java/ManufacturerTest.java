import com.example.model.ManufacturerModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

import static org.junit.Assert.*;

public class ManufacturerTest {
    String name = "category_name";

    @Before
    public void beforeMethod() throws SQLException {
        ManufacturerModel userModel = ManufacturerModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }

    }

    @After
    public void afterMethod() throws SQLException {
        ManufacturerModel userModel = ManufacturerModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }
    }

    @Test
    public void insertTest() throws SQLException {
        ManufacturerModel userModel = new ManufacturerModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        ManufacturerModel resultModel = ManufacturerModel.findByName(name);
        assertNotNull(resultModel);
        assertEquals(resultModel.getName(),name);
    }


    @Test
    public void updateTest() throws Exception {
        String name2 = "blabla";
        ManufacturerModel userModel = new ManufacturerModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        userModel.setName(name2);
        userModel.update();

        ManufacturerModel resultModel = ManufacturerModel.findByName(name2);
        assertEquals(resultModel.getName(), name2);
    }

    @Test
    public void deleteTest() throws Exception {
        ManufacturerModel userModel = new ManufacturerModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        ManufacturerModel tempModel = ManufacturerModel.findByName(name);
        if (tempModel != null)
            tempModel.delete();
        ManufacturerModel resultModel = ManufacturerModel.findByName(name);
        assertNull(resultModel);
    }

    @Test
    public void selectSingleTest() throws Exception {
        ManufacturerModel userModel = new ManufacturerModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        assertTrue(userModel.isSaved());
        ManufacturerModel resultTest = ManufacturerModel.findByName(name);
        assertNotNull(resultTest);
    }

    @Test
    public void selectByIdTest() throws Exception {
        ManufacturerModel userModel = new ManufacturerModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.insert();
        long id = userModel.getId();
        assertTrue(userModel.isSaved());
        ManufacturerModel resultTest = ManufacturerModel.findByID(id);
        assertNotNull(resultTest);
        assertEquals(userModel.getName(),resultTest.getName());
        assertEquals(userModel.getUUID(),resultTest.getUUID());
    }

}
