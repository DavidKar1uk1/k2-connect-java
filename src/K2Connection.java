import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class K2Connection {
    private static String location;
    private static String access_token;

    public static String getAccess_token () {
        return K2Connection.access_token;
    }

    public static void setAccess_token (String access_token) {
        K2Connection.access_token = access_token;
    }

    public static String getLocation () {
        return K2Connection.location;
    }

    public static void setLocation (String location) {
        K2Connection.location = location;
    }

    // Common Code for connection
    public static void to_connect(String params, String path_url, String access_token, boolean is_subscribe, boolean is_get_request) {
        HttpsURLConnection connection = null;
        try {
            // Create connection
            String ze_base_uri = "https://a54fac07-5ac2-4ee2-8fcb-e3d5ac3ba8b1.mock.pstmn.io";
            String the_uri = ze_base_uri + path_url;
            URL k2_uri = new URL(the_uri);
            connection = (HttpsURLConnection) k2_uri.openConnection();
            if (is_get_request) {
                connection.setRequestMethod("GET");
            } else {
                connection.setRequestMethod("POST");
            }
            if (path_url.contains("/ouath")) {
                connection.setRequestProperty("Content-Type", "application/vnd.kopokopo.v4.hal+json");
            } else {
                connection.setRequestProperty("Content-Type", "application/vnd.kopokopo.v4.hal+json");
                connection.setRequestProperty("Accept", "application/vnd.kopokopo.v4.hal+json");
                connection.setRequestProperty("Authorization", "Bearer "+ access_token);
            }
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Send Request
            DataOutputStream wr = new DataOutputStream( connection.getOutputStream() );
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            // Get Response
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\n');
                }
                rd.close();
                System.out.println("The Response:\t" + response.toString());

                JSONObject response_body = new JSONObject(response.toString());
                if (path_url.contains("/ouath")){
                    setAccess_token(response_body.get("access_token").toString());
                    System.out.println("The Access Token:\t" + getAccess_token());
                } else {
                    if (!is_subscribe) {
                        setLocation(response_body.get("location").toString());
                        System.out.println("The Location:\t" + getLocation());
                    }
                }
            } else {
                System.out.println("Exception to be caught. Not Connected");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
