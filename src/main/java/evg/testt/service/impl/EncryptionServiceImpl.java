package evg.testt.service.impl;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

@Service
public class EncryptionServiceImpl implements evg.testt.service.EncryptionService {

    private final String KEY = "Bar12345Bar12345"; // 128 bit key

    // Create key and cipher
    private SecretKeySpec aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
    byte[] IV = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);

    public EncryptionServiceImpl(){
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public String encryptString(String source){

        try {
            Cipher encryptCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
            encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec);
            byte[] encrypted = encryptCipher.doFinal(source.getBytes("UTF-8"));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String decryptString(String source){

        String returnValue = null;
        try {
            Cipher decryptCipher = Cipher.getInstance("AES/CTR/NoPadding", "BC");
            decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
            byte[] encrypted = decryptCipher.doFinal(Base64.decodeBase64(source));
            return new String(encrypted);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;

    }


}
