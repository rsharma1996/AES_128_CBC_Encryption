package com.company;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
/* Java program for 128-bit AES encryption in CBC mode
    *Riddhi Sharma, rshar59@uwo.ca;250824256
    *This program involves encryption  using 128-bit AES
     in CBC mode.
    *128-bit block
    *This is the class that will be invoked by AES_client
    *This has functions for encryption and decryption
            */
public class AES_Manager {




        public static String ALGORITHM = "AES"; // Specifying the algorithm
        private static String AES_CBS_PADDING = "AES/CBC/PKCS5Padding";

        public static byte[] encrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
            return AES_Manager.encryptDecrypt(Cipher.ENCRYPT_MODE, key, IV, message);
        }

        public static byte[] decrypt(final byte[] key, final byte[] IV, final byte[] message) throws Exception {
            return AES_Manager.encryptDecrypt(Cipher.DECRYPT_MODE, key, IV, message);
        }

        private static byte[] encryptDecrypt(final int mode, final byte[] key, final byte[] IV, final byte[] message)
                throws Exception {
            final Cipher cipher = Cipher.getInstance(AES_CBS_PADDING);
            final SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            final IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(mode, keySpec, ivSpec);
            return cipher.doFinal(message);
        }
    }


