package com.acercow.androidlib;

import com.acercow.androidlib.net.Response;
import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        Gson gson = new Gson();
        Response res = gson.fromJson("{\n" +
                "  \"isError\" : true,\n" +
                "  \"errorType\" : 1,\n" +
                "  \"errorMessage\" : \"Network Error\",\n" +
                "  \"result\" : {\n" +
                "    \"text\" : \"niubi\"\n" +
                "  }\n" +
                "}", Response.class);
        System.out.println(res.getResult());
    }
}