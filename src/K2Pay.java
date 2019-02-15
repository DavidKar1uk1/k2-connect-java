import org.json.JSONObject;

public class K2Pay {
    private String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token () {
        return this.access_token;
    }

    public K2Pay (String access_token) {
        this.access_token = access_token;
    }

    // Receive payments from M-PESA users.
    public void pay_recipients (JSONObject pay_recipient_params) {
        String k2_request_pay_recipient;
        // Case Function
        switch (pay_recipient_params.get("pay_type").toString()) {
            case "mobile_wallet":
                k2_request_pay_recipient = new JSONObject()
                        .put("firstName", pay_recipient_params.get("first_name").toString())
                        .put("lastName", pay_recipient_params.get("last_name").toString())
                        .put("phone", pay_recipient_params.get("phone").toString())
                        .put("email", pay_recipient_params.get("email").toString())
                        .put("network", pay_recipient_params.get("network").toString()).toString();
                        break;
            case "bank_account":
                k2_request_pay_recipient = new JSONObject()
                        .put("name", pay_recipient_params.get("first_name").toString() + " " + pay_recipient_params.get("last_name").toString())
                        .put("account_name", pay_recipient_params.get("account_name").toString())
                        .put("bank_id", pay_recipient_params.get("bank_id").toString())
                        .put("bank_branch_id", pay_recipient_params.get("bank_branch_id").toString())
                        .put("account_number", pay_recipient_params.get("account_number").toString())
                        .put("email", pay_recipient_params.get("email").toString())
                        .put("phone", pay_recipient_params.get("phone").toString()).toString();
                break;
            default:
                k2_request_pay_recipient = null;
        }
        String recipients_body = new JSONObject()
                .put("type", pay_recipient_params.get("type").toString())
                .put("pay_recipient", k2_request_pay_recipient).toString();
        K2Connection.to_connect(recipients_body, "/pay_recipients", this.access_token, false, false);
    }

    // Create an outgoing Payment to a third party.
    public void pay_create (JSONObject pay_create_params) {
        // Amount Details
        String k2_request_pay_amount = new JSONObject()
                .put("currency", pay_create_params.get("currency").toString())
                .put("value", pay_create_params.get("value").toString()).toString();
        // Metadata Details
        String k2_request_pay_metadata = new JSONObject()
                .put("customerId", "8675309")
                .put("notes", "Salary payment for May 2018").toString();
        // Link Details
        String k2_request_pay_links = new JSONObject()
                .put("call_back_url", "https://your-call-bak.yourapplication.com/payment_result").toString();
        String pay_create_body = new JSONObject()
                .put("destination", "c7f300c0-f1ef-4151-9bbe-005005aa3747")
                .put("amount", k2_request_pay_amount)
                .put("metadata", k2_request_pay_metadata)
                .put("_links", k2_request_pay_links).toString();
        K2Connection.to_connect(pay_create_body, "/payments", this.access_token, false, false);
    }

    // Process Pay Result Asynchronously after Payment is initiated
    public void process_pay (JSONObject the_request) {

    }

    // Query the status of a previously initiated Payment request
    public void query_pay (String id) {
        String receive_body = new JSONObject()
                .put("ID", id).toString();
        K2Connection.to_connect(receive_body, "/payments", this.access_token, false, true);
    }
}
