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

    public static UserBodyDto getUserWithoutEmail() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
        return user;
    }

    public static UserBodyDto getUserWithoutPassword() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .name(faker.name().firstName())
                .build();
        return user;
    }

    public static UserBodyDto getUserWithoutName() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .build();
        return user;
    }
}
