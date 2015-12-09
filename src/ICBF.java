import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Kev on 09/12/2015.
 */
public class ICBF {
    public static class HttpURLConnectionExample {

        private final String USER_AGENT = "Mozilla/5.0";

        public static void main(String[] args) throws Exception {

            HttpURLConnectionExample http = new HttpURLConnectionExample();

            System.out.println("\nTesting 2 - Send Http POST request");
            http.sendPost();

        }
        private void sendPost() throws Exception {

            String url = "https://webapp.icbf.com/auth/";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "username=C1560113&password=Miltownmalbay1";

          //  String urlParameters = "username=dsad&password=sdfgfd";


            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            String str = con.getHeaderField("Location");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println("Redirect to + "+str);
            System.out.println("Responseis + "+response.toString());

        }
    }
}


