package util;

import dto.UserBodyDto;

public class BaseTest {
    protected UserBodyDto user = new UserBodyDto();
    protected String accessToken;

    protected void deleteUserIfExists() {
        if (accessToken != null) {
            UserHelper.deleteUser(accessToken);
        }
    }
}
