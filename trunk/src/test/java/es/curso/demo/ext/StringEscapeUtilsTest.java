package es.curso.demo.ext;

import junit.framework.Assert;

import org.junit.Test;

public class StringEscapeUtilsTest {

    @Test
    public void testEscapeFileNameNull() {
        final String result = StringEscapeUtils.escapeFileName(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEscapeFileNameEmpty() {
        final String result = StringEscapeUtils.escapeFileName("");
        Assert.assertEquals(result, "");
    }

    @Test
    public void testEscapeFileNameNotEmpty() {
        final String result = StringEscapeUtils.escapeFileName("test.txt");
        Assert.assertEquals(result, "test.txt");
    }

    @Test
    public void testEscapeFileNameNotEmptyUpppercase() {
        final String result = StringEscapeUtils.escapeFileName("TEST.TXT");
        Assert.assertEquals(result, "test.txt");
    }

    @Test
    public void testEscapeFileNameNotEmptyWithSpaces() {
        final String result = StringEscapeUtils.escapeFileName("test TEST.TXT");
        Assert.assertEquals(result, "test_test.txt");
    }

    @Test
    public void testEscapeFileNameNotEmptyWithSpacesAndAccents() {
        final String result = StringEscapeUtils.escapeFileName("test TEST ó Á.TXT");
        Assert.assertEquals(result, "test_test_o_a.txt");
    }

}
