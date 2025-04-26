import java.util.regex.*;

public class ValidaContra {

    public static boolean validar(String pass) {
        if (pass.length() < 8) return false;

        int mayus = contarCoincidencias(pass, "[A-Z]");
        int minus = contarCoincidencias(pass, "[a-z]");
        int numeros = contarCoincidencias(pass, "[0-9]");
        int especiales = contarCoincidencias(pass, "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");

        return mayus >= 2 && minus >= 3 && numeros >= 1 && especiales >= 1;
    }

    private static int contarCoincidencias(String texto, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(texto);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }
}
