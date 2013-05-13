package es.curso.demo.ext;

import org.apache.commons.lang3.StringUtils;

public final class StringEscapeUtils {

    private StringEscapeUtils() {
    }

    private static final String LETRAS_REEMPLAZAR[] = { "á", "é", "í", "ó", "ú", "à", "è", "ì", "ò", "ù", "â", "ê",
            "î", "ô", "û", "ä", "ë", "ï", "ö", "ü" };
    private static final String LETRAS_REEMPLAZADAS[] = { "a", "e", "i", "o", "u", "a", "e", "i", "o", "u", "a", "e",
            "i", "o", "u", "a", "e", "i", "o", "u" };

    public static String escapeFileName(final String fileName) {
        String escapeFileName = null;
        if (fileName != null) {
            escapeFileName = fileName.toLowerCase();
            escapeFileName = escapeFileName.replaceAll(" ", "_");
            escapeFileName = StringUtils.replaceEach(escapeFileName, LETRAS_REEMPLAZAR, LETRAS_REEMPLAZADAS);
        }
        return escapeFileName;
    }
}
