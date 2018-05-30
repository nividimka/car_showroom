import com.example.model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    String username = "qwerty";

    @Before
    public void beforeMethod() throws SQLException {
        UserModel userModel = UserModel.findByUsername(username);
        if (userModel != null) {
            userModel.delete();
        }

    }

    @After
    public void afterMethod() throws SQLException {
        UserModel userModel = UserModel.findByUsername(username);
        if (userModel != null) {
            userModel.delete();
        }
    }

    @Test
    public void insertTest() throws SQLException {
        UserModel userModel = new UserModel(username, "blablabla");
        userModel.insert();
        assertTrue(userModel.isSaved());
        UserModel resultModel = UserModel.findByUsername(username);
        assertNotNull(resultModel);
    }

    @Test
    public void updateTest() throws Exception {
        String password1 = "password1";
        String password2 = "password2";
        UserModel userModel = new UserModel(username, password1);
        userModel.insert();
        assertTrue(userModel.isSaved());
        userModel.setPassword(password2);
        userModel.update();

        UserModel resultModel = UserModel.findByUsername(username);
        assertEquals(resultModel.getPassword(), password2);
    }

    @Test
    public void deleteTest() throws Exception {
        String password = "pass";
        UserModel userModel = new UserModel(username, password);
        userModel.insert();
        assertTrue(userModel.isSaved());
        UserModel tempModel = UserModel.findByUsername(username);
        if (tempModel != null)
            tempModel.delete();
        UserModel resultModel = UserModel.findByUsername(username);
        assertNull(resultModel);
    }

    @Test
    public void selectSingleTest() throws Exception {
        String password = "pass1";
        UserModel userModel = new UserModel(username, password);
        userModel.insert();
        assertTrue(userModel.isSaved());
        UserModel resultTest = UserModel.findByUsername(username);
        assertNotNull(resultTest);
    }

    @Test
    public void selectMultipleTest() throws Exception {
        String password = "pass1";
        List<UserModel> models = UserModel.getUserList();
        long firstSize = models.size();
        UserModel model = new UserModel(username, password);
        model.insert();
        List<UserModel> tempModels = UserModel.getUserList();
        assertEquals(tempModels.size(),firstSize+1);
    }
}
