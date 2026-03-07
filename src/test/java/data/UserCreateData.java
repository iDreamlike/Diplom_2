package data;

import dto.UserBodyDto;
import net.datafaker.Faker;

public class UserCreateData {

    private static final Faker faker = new Faker();

    public static UserBodyDto getRandomUser() {
        UserBodyDto user = new UserBodyDto();
                user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
        return user;
    }
}
