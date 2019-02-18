import org.json.JSONObject;

public class K2Client {
    private String api_secret_key, request_body, request_headers, k2_signature;

    public K2Client (String api_secret_key, String request_body, String request_headers) {
        this.api_secret_key = api_secret_key;
        this.request_body = request_body;
        this.request_headers = request_headers;
    }

    public void parse_request () {
        JSONObject _headers = new JSONObject(request_headers);
        setK2_signature(_headers.get("x-kopokopo-signature").toString());
    }

    public void setK2_signature(String signature) {
        this.k2_signature = signature;
    }

    public String getK2_signature() {
        return this.k2_signature;
    }
}
