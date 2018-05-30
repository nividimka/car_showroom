import com.example.model.CarModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.*;

public class CarTest {
    String name = "test_car";

    @Before
    public void beforeMethod() throws SQLException {
        System.setProperty("file.encoding", "UTF-8");
        CarModel userModel = CarModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }

    }

    @After
    public void afterMethod() throws SQLException {
//        CarModel userModel = CarModel.findByName(name);
//        if (userModel != null) {
//            userModel.delete();
//        }
    }

    @Test
    public void insertTest() throws SQLException {
        CarModel userModel = new CarModel(UUID.randomUUID());
        Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
        userModel.setName(name);
        userModel.setCreationDate(creationDate);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarModel resultModel = CarModel.findByName(name);
        assertNotNull(resultModel);
        assertEquals(resultModel.getName(),name);
        assertEquals(resultModel.getCreationDate().getTime(),creationDate.getTime());
    }


    @Test
    public void updateTest() throws Exception {
        Date dateStart1 = new Date(123123123);
        Date dateStart2 = new Date(123123121);
        Date dateEnd1 = new Date(214253123);
        Date dateEnd2 = new Date(222313123);
        CarModel userModel = new CarModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart1);
        userModel.insert();
        assertTrue(userModel.isSaved());
        userModel.setCreationDate(dateStart2);
        userModel.update();

        CarModel resultModel = CarModel.findByName(name);
        assertEquals(resultModel.getCreationDate().getTime(), dateStart2.getTime());
    }

    @Test
    public void deleteTest() throws Exception {
        Date dateStart1 = new Date(123123123);
        Date dateEnd1 = new Date(223123123);
        CarModel userModel = new CarModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart1);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarModel tempModel = CarModel.findByName(name);
        if (tempModel != null)
            tempModel.delete();
        CarModel resultModel = CarModel.findByName(name);
        assertNull(resultModel);
    }

    @Test
    public void selectSingleTest() throws Exception {
        Date dateStart = new Date(123123123);
        Date dateEnd = new Date(223123123);
        CarModel userModel = new CarModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarModel resultTest = CarModel.findByName(name);
        assertNotNull(resultTest);
    }

    @Test
    public void selectByIdTest() throws Exception {
        Date dateStart = new Date(123123123);
        Date dateEnd = new Date(223123123);
        CarModel userModel = new CarModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart);
        userModel.insert();
        long id = userModel.getId();
        assertTrue(userModel.isSaved());
        CarModel resultTest = CarModel.findByID(id);
        assertNotNull(resultTest);
        assertEquals(userModel.getCreationDate().getTime(),resultTest.getCreationDate().getTime());
        assertEquals(userModel.getName(),resultTest.getName());
    }

}
