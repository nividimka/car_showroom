import com.example.ui.NewUserDialog;
import org.fest.swing.fixture.DialogFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class NewUserTest {

    private DialogFixture demo;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        demo = new DialogFixture(new NewUserDialog());
        demo.show();
    }

    @After
    public void tearDown() {

        demo.cleanUp();
    }
    @Test
    public void test() {
        demo.label("password1").requireText("Пароль");
        demo.label("password2").requireText("Повторите Пароль");
        demo.label("login").requireText("Логин");
        demo.button("okButton").click();
        demo.label("OptionPane.label").requireText("Введите все данные");
    }
}






//unit test
//нагрузочное тестирование
//покрытие тестов
//web
//gui