import com.example.model.CarNumberModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import static org.junit.Assert.*;

public class CarNumberTest {
    String name = "test_car";

    @Before
    public void beforeMethod() throws SQLException {
        CarNumberModel userModel = CarNumberModel.findByName(name);
        if (userModel != null) {
            userModel.delete();
        }

    }

    @After
    public void afterMethod() throws SQLException {
//        CarNumberModel userModel = CarNumberModel.findByName(name);
//        if (userModel != null) {
//            userModel.delete();
//        }
    }

    @Test
    public void insertTest() throws SQLException {
        CarNumberModel userModel = new CarNumberModel(UUID.randomUUID());
        Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
        Date expirationDate = new Date(Calendar.getInstance().getTimeInMillis());
        userModel.setName(name);
        userModel.setExpirationDate(expirationDate);
        userModel.setCreationDate(creationDate);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarNumberModel resultModel = CarNumberModel.findByName(name);
        assertNotNull(resultModel);
        assertEquals(resultModel.getName(),name);
        assertEquals(resultModel.getExpirationDate().getTime(),expirationDate.getTime());
        assertEquals(resultModel.getCreationDate().getTime(),creationDate.getTime());
    }


    @Test
    public void updateTest() throws Exception {
        Date dateStart1 = new Date(123123123);
        Date dateStart2 = new Date(123123121);
        Date dateEnd1 = new Date(214253123);
        Date dateEnd2 = new Date(222313123);
        CarNumberModel userModel = new CarNumberModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart1);
        userModel.setExpirationDate(dateEnd1);
        userModel.insert();
        assertTrue(userModel.isSaved());
        userModel.setCreationDate(dateStart2);
        userModel.setExpirationDate(dateEnd2);
        userModel.update();

        CarNumberModel resultModel = CarNumberModel.findByName(name);
        assertEquals(resultModel.getCreationDate().getTime(), dateStart2.getTime());
        assertEquals(resultModel.getExpirationDate().getTime(), dateEnd2.getTime());
    }

    @Test
    public void deleteTest() throws Exception {
        Date dateStart1 = new Date(123123123);
        Date dateEnd1 = new Date(223123123);
        CarNumberModel userModel = new CarNumberModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart1);
        userModel.setExpirationDate(dateEnd1);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarNumberModel tempModel = CarNumberModel.findByName(name);
        if (tempModel != null)
            tempModel.delete();
        CarNumberModel resultModel = CarNumberModel.findByName(name);
        assertNull(resultModel);
    }

    @Test
    public void selectSingleTest() throws Exception {
        Date dateStart = new Date(123123123);
        Date dateEnd = new Date(223123123);
        CarNumberModel userModel = new CarNumberModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart);
        userModel.setExpirationDate(dateEnd);
        userModel.insert();
        assertTrue(userModel.isSaved());
        CarNumberModel resultTest = CarNumberModel.findByName(name);
        assertNotNull(resultTest);
    }

    @Test
    public void selectByIdTest() throws Exception {
        Date dateStart = new Date(123123123);
        Date dateEnd = new Date(223123123);
        CarNumberModel userModel = new CarNumberModel(UUID.randomUUID());
        userModel.setName(name);
        userModel.setCreationDate(dateStart);
        userModel.setExpirationDate(dateEnd);
        userModel.insert();
        long id = userModel.getId();
        assertTrue(userModel.isSaved());
        CarNumberModel resultTest = CarNumberModel.findByID(id);
        assertNotNull(resultTest);
        assertEquals(userModel.getCreationDate().getTime(),resultTest.getCreationDate().getTime());
        assertEquals(userModel.getExpirationDate().getTime(),resultTest.getExpirationDate().getTime());
        assertEquals(userModel.getName(),resultTest.getName());
        assertEquals(userModel.getUUID(),resultTest.getUUID());
    }

}
