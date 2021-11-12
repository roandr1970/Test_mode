import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    public  static class Registration {

        public static RegistrationInfo generateInfo (String locale) {
            Faker faker = new Faker(new Locale(locale));
            String status = "active";
            String password = "password";
            return new RegistrationInfo(faker.name().username(), password, status);
        }
    }
}
