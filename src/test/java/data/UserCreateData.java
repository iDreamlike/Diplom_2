package data;

import dto.UserBodyDto;
import net.datafaker.Faker;

public class UserCreateData {

    private static final Faker faker = new Faker();

    public static UserBodyDto createUserData() {
        UserBodyDto user = new UserBodyDto();
                user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
//        System.out.printf("Создали пользака: \nemail: %s\npassword: %s\nname: %s", user.getEmail(), user.getPassword(), user.getName());
        return user;
    }

    public static UserBodyDto createUserWithoutEmail() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .password(faker.lorem().characters(10))
                .name(faker.name().firstName())
                .build();
        return user;
    }

    public static UserBodyDto createUserWithoutPassword() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .name(faker.name().firstName())
                .build();
        return user;
    }

    public static UserBodyDto createUserWithoutName() {
        UserBodyDto user = new UserBodyDto();
        user = user.toBuilder()
                .email(faker.internet().emailAddress())
                .password(faker.lorem().characters(10))
                .build();
        return user;
    }

    public static void updateUserEmailData(UserBodyDto user) {
        user.setEmail(faker.internet().emailAddress());
    }

    public static void updateUserPasswordData(UserBodyDto user) {
        user.setPassword(faker.lorem().characters(10));
    }

    public static void updateUserNameData(UserBodyDto user) {
        user.setName(faker.name().firstName());
    }
}
