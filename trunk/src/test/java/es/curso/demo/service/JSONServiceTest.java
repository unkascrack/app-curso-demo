package es.curso.demo.service;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import es.curso.demo.model.Curso;

public class JSONServiceTest {

    private static JSONService jsonService;

    @BeforeClass
    public static void setUp() {
        jsonService = new JSONService();
    }

    @Test
    public void testJSONServiceNotNull() {
        Assert.assertNotNull(jsonService);
    }

    @Test
    public void testSerializeNull() {
        final String json = jsonService.serialize(null, null);
        Assert.assertNull(json);
    }

    @Test
    public void testSerializeNotNull() {
        final String json = jsonService.serialize(null, new Curso());
        Assert.assertNotNull(json);
    }

}
