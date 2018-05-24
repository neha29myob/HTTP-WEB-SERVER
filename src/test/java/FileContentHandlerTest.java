import Handlers.FileContentHandler;
import HttpRequest.Request;
import HttpRequest.RequestMethod;
import HttpResponse.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class FileContentHandlerTest {
    FileContentHandler fileHandler;
    Request request;

    @Before
    public void setUp() {
        fileHandler = new FileContentHandler();
    }

    @Test
    public void whenBogusRequestReturnFourOhFive() {
        request = new Request(RequestMethod.BAD, "/", new HashMap<>(), "", new HashMap<>());
        Response expectedResponse = fileHandler.handle(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), 405);
    }

    @Test
    public void whenRequestUnknownFileReturnFourOhFour() {
        request = new Request(RequestMethod.GET, "/no/file", new HashMap<>(), "", new HashMap<>());
        Response expectedResponse = fileHandler.handle(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), 404);
    }

    @Test
    public void whenGetRequestOnTextFileReturnResponseWithFileContents() throws UnsupportedEncodingException {
        request = new Request(RequestMethod.GET, "/file1", new HashMap<>(), "", new HashMap<>());
        Response expectedResponse = fileHandler.handle(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), 200);
        Assert.assertEquals(new String(expectedResponse.getResponseBody(), "UTF-8"), "file1 contents");
        Assert.assertTrue(expectedResponse.getResponseHeader().contains("content-type:text/plain"));
    }

    @Test
    public void whenGetRequestOnImageFileReturnResponseWithImageContent() {
        request = new Request(RequestMethod.GET, "/image.jpeg", new HashMap<>(), "", new HashMap<>());
        Response expectedResponse = fileHandler.handle(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), 200);
        Assert.assertTrue(expectedResponse.getResponseHeader().contains("content-type:image/jpeg"));
        Assert.assertTrue(expectedResponse.getResponseBody().length > 0);
    }

    @Test
    public void whenPutRequestWithRequestBodyResponseWithBodyContent() throws UnsupportedEncodingException {
        request = new Request(RequestMethod.PUT, "/file2", new HashMap<>(), "Hello file contents", new HashMap<>());
        Response expectedResponse = fileHandler.handle(request);
        Assert.assertEquals(expectedResponse.getStatusCode(), 200);
        Assert.assertEquals(new String(expectedResponse.getResponseBody(), "UTF-8"), "Hello file contents");
    }
}
