import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
        .setBaseUri("http://localhost")
        .setPort(9999)
        .setAccept(ContentType.JSON)
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
    @BeforeAll
    static void setUpAll() {
        // сам запрос
        given() // "дано"
            .spec(requestSpec) // указываем, какую спецификацию используем
            .body(new RegistrationInfo("petya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
        .when() // "когда"
            .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
        .then() // "тогда ожидаем"
            .statusCode(200); // код 200 OK
    }

    @Test
     void shouldGenerateTestDataUsingUtils() {
        RegistrationInfo info = DataGenerator.Registration.generateInfo("ru");
        $("[type='text']").setValue(info.getName());
        $("[type='password']").setValue(info.getPassword());
        $(withText("Продолжить")).click();
        $("[data-test-id='error-notification'] .notification__content")
                .shouldBe(visible)
                .shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));

    }
}

