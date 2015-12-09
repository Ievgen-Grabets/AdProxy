package evg.testt.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 12/8/15.
 */
public class CachingInputStream extends InputStream
{
    private final InputStream is;
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    public CachingInputStream(InputStream is)
    {
        this.is = is;
    }

    public byte[] getCache()
    {
        return baos.toByteArray();
    }

    public int read() throws IOException
    {
        int result = is.read();
        baos.write(result);
        return result;
    }

    public int available() throws IOException
    {
        return is.available();
    }

    public void close() throws IOException
    {
        is.close();
    }

}
