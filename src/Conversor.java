import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public static void convertirMoneda( String monedaBase, String nombreBase,  String cambioDeMoneda, String nombreDeCambio, double cantidad, Gson gson) {

        String direccion = "https://v6.exchangerate-api.com/v6/b29937625e957c73ef35fa56/latest/" + monedaBase;

        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                String responseBody = response.body();


                JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);


                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");


                if (conversionRates.has(cambioDeMoneda)) {

                    double tasaDeCambio = conversionRates.get(cambioDeMoneda).getAsDouble();


                    double cantidadConvertida = cantidad * tasaDeCambio;


                    System.out.println("El valor "+cantidad + " " + nombreBase + " corresponde al valor final de =>>> " + cantidadConvertida + " " + nombreDeCambio
                    +"\n***************************************************************************");
                } else {
                    System.out.println("La moneda de cambio especificada no está disponible.");
                }
            } else {
                System.out.println("Error al obtener la tasa de cambio. Código de estado: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud: " + e.getMessage());
        }
    }

}
