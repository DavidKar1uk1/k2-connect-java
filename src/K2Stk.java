import org.json.JSONObject;

public class K2Stk {
    private String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token () {
        return this.access_token;
    }

    public K2Stk (String access_token) {
        this.access_token = access_token;
    }

    // Receive payments from M-PESA users.
    public void mpesa_receive_payments (JSONObject stk_receive_params) {
        // Subscriber Details
        String k2_request_subscriber = new JSONObject()
                .put("first_name", stk_receive_params.get("first_name").toString())
                .put("last_name", stk_receive_params.get("last_name").toString())
                .put("phone", stk_receive_params.get("phone").toString())
                .put("email", stk_receive_params.get("email").toString()).toString();
        // Amount Details
        String k2_request_amount = new JSONObject()
                .put("currency", stk_receive_params.get("currency").toString())
                .put("value", stk_receive_params.get("value").toString()).toString();
        // Metadata Details
        String k2_request_metadata = new JSONObject()
                .put("customer_id", "asd")
                .put("reference", "asdsa")
                .put("notes", "adadsaas").toString();
        // Link Details
        String k2_request_links = new JSONObject()
                .put("call_back_url", "https://call_back_to_your_app.your_application.com").toString();
        String receive_body = new JSONObject()
                .put("payment_channel", "M-PESA")
                .put("till_identifier", "444555")
                .put("subscriber", k2_request_subscriber)
                .put("amount", k2_request_amount)
                .put("metadata", k2_request_metadata)
                .put("_links", k2_request_links).toString();
        K2Connection.to_connect(receive_body, "/payment_requests", this.access_token, false, false);
    }

    // Query Payment Request Status
    public void mpesa_query_payments (String id) {
        String receive_body = new JSONObject()
                .put("ID", id).toString();
        K2Connection.to_connect(receive_body, "/payment_requests", this.access_token, false, true);
    }

    public void mpesa_process_payments(JSONObject the_request) {

    }
}
