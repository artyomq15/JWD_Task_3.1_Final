package by.tr.likeitnetwork.dao.util;

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
    private final static int SIZE_SALT_BYTES = 32;

    public static String getPasswordHashCode(String password, String salt) throws NoSuchAlgorithmException {
        String text = password + salt;

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(text.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();

        return String.format(FORMAT, new BigInteger(SIGNUM, digest));

    }

    public static String generateRandomString(){
        final Random random = new SecureRandom();
        byte[] salt = new byte[SIZE_SALT_BYTES];
        random.nextBytes(salt);
        return String.format(FORMAT, new BigInteger(SIGNUM, salt));
    }


}
