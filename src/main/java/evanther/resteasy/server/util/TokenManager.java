package evanther.resteasy.server.util;

public class TokenManager {
    public static boolean isValid(String token) {
        return token == null ? false : token.contains("resteasy");
    }
}
