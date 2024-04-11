import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        System.out.println("**************************************************");
        System.out.println("Sea bienvenido/a al Conversor de Monedas =)\n");

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (opcion != 7) {
            mostrarMenu();
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese el valor que desea convertir:");
                double cantidad = teclado.nextDouble();

                switch (opcion) {
                    case 1:
                        Conversor.convertirMoneda( "USD","[USD]",  "ARS","[ARS]", cantidad, gson);
                        break;
                    case 2:
                        Conversor.convertirMoneda("ARS", "[ARS]", "USD", "[USD]", cantidad, gson);
                        break;
                    case 3:
                        Conversor.convertirMoneda("USD", "[USD]", "BRL", "[BRL]", cantidad, gson);
                        break;
                    case 4:
                        Conversor.convertirMoneda("BRL", "[BRL]", "USD", "[USD]", cantidad, gson);
                        break;
                    case 5:
                        Conversor.convertirMoneda("USD", "[USD]", "COP", "[COP]", cantidad, gson);
                        break;
                    case 6:
                        Conversor.convertirMoneda("COP", "[COP]", "USD", "[USD]", cantidad, gson);
                        break;
                }
            } else if (opcion == 7) {
                System.out.println("Gracias por utilizar nuestro servicio. ¡Hasta luego!");
            } else {
                System.out.println("Opción no válida");
            }
        }

        teclado.close();
    }

    private static void mostrarMenu() {
        System.out.println("""
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                Elija una opción:
                **************************************************""");
    }
}
