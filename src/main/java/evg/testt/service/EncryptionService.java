package evg.testt.service;

/**
 * Created on 11/20/15.
 */
public interface EncryptionService {

    String encryptString(String source);

    String decryptString(String source);

}
