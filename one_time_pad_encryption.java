import java.util.Random;

public class OneTimePadEncryption {
    public static void main(String[] args) {
        String plaintext = "MY NAME IS UNKNOWN";
        // Generate random key of the same length
        byte[] key = new byte[plaintext.length()];
        new Random().nextBytes(key);

        // Encrypt
        byte[] ciphertext = new byte[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext[i] = (byte) (plaintext.charAt(i) ^ key[i]);
        }

        // Print ciphertext in hex
        System.out.print("Ciphertext (hex): ");
        for (byte b : ciphertext) {
            System.out.printf("%02X ", b);
        }
        System.out.println();

        // Decrypt
        char[] decrypted = new char[plaintext.length()];
        for (int i = 0; i < ciphertext.length; i++) {
            decrypted[i] = (char) (ciphertext[i] ^ key[i]);
        }
        System.out.println("Decrypted text: " + new String(decrypted));
    }
}