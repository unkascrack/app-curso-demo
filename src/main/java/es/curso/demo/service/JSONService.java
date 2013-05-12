package es.curso.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class JSONService {

    /**
     * @param object
     * @return
     */
    public String serialize(final Object object) {
        return object == null ? null : new JSONSerializer().exclude("*.class").serialize(object);
    }

    /**
     * @param rootName
     * @param object
     * @return
     */
    public String serialize(final String rootName, final Object object) {
        return object == null ? null : new JSONSerializer().rootName(rootName).exclude("*.class").serialize(object);
    }

    /**
     * @param rootName
     * @param json
     * @return
     */
    public <T> T deserialize(final Class<T> clase, final String json) {
        return json == null ? null : new JSONDeserializer<T>().deserialize(json, clase);
    }

    /**
     * @param clase
     * @param rootName
     * @param json
     * @return
     */
    public <T> T deserialize(final Class<T> clase, final String rootName, final String json) {
        return new JSONDeserializer<Map<String, T>>().use("values", clase).deserialize(json).get(rootName);
    }
}
