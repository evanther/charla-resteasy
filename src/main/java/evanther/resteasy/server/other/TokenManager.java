package evanther.resteasy.server.other;

public class TokenManager {
    public static boolean isValid(String token) {
        return token == null ? false : token.contains("resteasy");
    }
}
