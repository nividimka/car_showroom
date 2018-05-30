//import com.example.model.OrderModel;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.Calendar;
//import java.util.UUID;
//
//import static org.junit.Assert.*;
//
//public class OrderTest {
//    UUID orderUUID = UUID.randomUUID();
//
//    @Before
//    public void beforeMethod() throws SQLException {
//        OrderModel userModel = OrderModel.findByUUID(orderUUID);
//        if (userModel != null) {
//            userModel.delete();
//        }
//
//    }
//
//    @After
//    public void afterMethod() throws SQLException {
//        OrderModel userModel = OrderModel.findByUUID(orderUUID);
//        if (userModel != null) {
//            userModel.delete();
//        }
//    }
//
//    @Test
//    public void insertTest() throws SQLException {
//        OrderModel userModel = new OrderModel(UUID.randomUUID());
//        Date creationDate = new Date(Calendar.getInstance().getTimeInMillis());
//        userModel.setExpirationDate(creationDate);
//        userModel.insert();
//        assertTrue(userModel.isSaved());
//        OrderModel resultModel = OrderModel.findByUUID(userModel.getUuid());
//        assertNotNull(resultModel);
//        assertEquals(resultModel.getUuid(), userModel.getUuid());
//    }
//
//
//    @Test
//    public void updateTest() throws Exception {
//        Date dateEnd1 = new Date(214253123);
//        Date dateEnd2 = new Date(222313123);
//        OrderModel userModel = new OrderModel(orderUUID);
//        userModel.setExpirationDate(dateEnd1);
//        userModel.insert();
//        assertTrue(userModel.isSaved());
//        userModel.setExpirationDate(dateEnd2);
//        userModel.update();
//
//        OrderModel resultModel = OrderModel.findByUUID(orderUUID);
//        assertEquals(resultModel.getExpirationDate().getTime(), dateEnd2.getTime());
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        OrderModel userModel = new OrderModel(orderUUID);
//        userModel.setExpirationDate(new Date(123123123));
//        userModel.insert();
//        assertTrue(userModel.isSaved());
//        OrderModel tempModel = OrderModel.findByUUID(orderUUID);
//        if (tempModel != null)
//            tempModel.delete();
//        OrderModel resultModel = OrderModel.findByUUID(orderUUID);
//        assertNull(resultModel);
//    }
//
//    @Test
//    public void selectSingleTest() throws Exception {
//        OrderModel userModel = new OrderModel(orderUUID);
//        userModel.setExpirationDate(new Date(123123123));
//        userModel.insert();
//        assertTrue(userModel.isSaved());
//        OrderModel resultTest = OrderModel.findByUUID(orderUUID);
//        assertNotNull(resultTest);
//    }
//
//    @Test
//    public void selectByIdTest() throws Exception {
//        OrderModel userModel = new OrderModel(UUID.randomUUID());
//        userModel.setExpirationDate(new Date(123123123));
//        userModel.insert();
//        long id = userModel.getId();
//        assertTrue(userModel.isSaved());
//        OrderModel resultTest = OrderModel.findByID(id);
//        assertNotNull(resultTest);
//    }
//
//}
