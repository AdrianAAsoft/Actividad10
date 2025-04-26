import java.util.function.Consumer;

public class validar extends Thread {
    private final String contrasena;
    private final Consumer<String> callback; // función lambda para registrar

    public validar(String contrasena, Consumer<String> callback) {
        this.contrasena = contrasena;
        this.callback = callback;
    }

    @Override
    public void run() {
        boolean esValida = ValidaContra.validar(contrasena);
        String resultado = esValida ? "✅ VALIDA" : "❌ INVALIDA";
        System.out.println("🔐 Contraseña: " + contrasena + " --> " + resultado);

        // Guardar en el archivo usando la lambda
        callback.accept(resultado);
    }
}
