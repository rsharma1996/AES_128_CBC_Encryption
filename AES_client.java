package com.company;
import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;
import java.util.UUID;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.ASCII;

public class AES_client {
/*
* This class invokes AES_manager for encryption and decryption of the plaintext supplied.
* The program asks for plaintext input
* After entering, it generates the ciphertext based on Initialization Vector and Key which are random
* */
    private static int AES_256 = 128;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner( System.in );
        System.out.print( "Enter your plaintext: " );
        String input = scanner.nextLine();
        //byte[] b = input.getBytes(StandardCharsets.US_ASCII);
        //System.out.println("ASCII value: " +b);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_Manager.ALGORITHM);
        keyGenerator.init(AES_256);
        //Generate Key
        SecretKey key = keyGenerator.getInstance("AES").generateKey();
        System.out.println("Hex value: "+key);
        String encodedKey = //Integer.toHexString(key);

                Base64.getEncoder().encodeToString(key.getEncoded());
      //  String hexString = Hex.encodeHexString(decoded);
        System.out.println("Base64 value: "+encodedKey);
      //  String hexString = Hex.encodeHexString(decoded);
      //  String hexString= HexadecimalEncodingAlgorithm.WordListener
        //Initialization vector

        SecretKey IV = keyGenerator.generateKey();
        System.out.println("Hex value: "+IV);
        String encodedIV = Base64.getEncoder().encodeToString(IV.getEncoded());

        System.out.println("Base64 value: "+encodedIV);
        //String message= "RiddhiSharma250824256";
        //Scanner scanner = new Scanner( System.in );
        //System.out.print( "Enter your plaintext: " );
        //String input = scanner.nextLine();
       // System.out.println("1.PlainText:  " +message);

        byte[] cipherText = AES_Manager.encrypt(key.getEncoded(), IV.getEncoded(), input.getBytes(StandardCharsets.US_ASCII));
        System.out.println("1.  Encrypted Text: " + Base64.getEncoder().encodeToString(cipherText));
        System.out.println("a.  Encrypted text in hex: "+String.format("%040x", new BigInteger(1, cipherText)));
        byte[] decryptedString = AES_Manager.decrypt(key.getEncoded(), IV.getEncoded(), cipherText);
        System.out.println("2.   Decrypted Text: " + new String(decryptedString));

    }

}
