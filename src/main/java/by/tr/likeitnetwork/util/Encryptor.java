package by.tr.likeitnetwork.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Encryptor {
    private final static String FORMAT = "%064x";
    private final static String ALGORITHM = "SHA-256";
    private final static int SIGNUM = 1;
    private final static int SALT_STRING_SIZE = 32;
    private final static int ACCESS_TOKEN_STRING_SIZE = 32;
    private final static int REFRESH_TOKEN_STRING_SIZE = 32;

    private final static String TOKEN_INFO_DELIMITER = ":";

    public static String getPasswordHashCode(String password, String salt) throws NoSuchAlgorithmException {
        String text = password + salt;

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        return String.format(FORMAT, new BigInteger(SIGNUM, digest));

    }

    private static String generateRandomString(int size){
        final Random random = new SecureRandom();
        byte[] rand = new byte[size];
        random.nextBytes(rand);
        return String.format(FORMAT, new BigInteger(SIGNUM, rand));
    }

    public static String generateSalt(){
        return generateRandomString(SALT_STRING_SIZE);
    }

    public static String generateAccessToken(int id, String role){
        return String.valueOf(id) + TOKEN_INFO_DELIMITER + generateRandomString(ACCESS_TOKEN_STRING_SIZE) + TOKEN_INFO_DELIMITER + role;
    }

    public static String generateRefreshToken(){
        return generateRandomString(REFRESH_TOKEN_STRING_SIZE);
    }


}
