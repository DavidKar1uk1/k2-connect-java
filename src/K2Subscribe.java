import org.json.JSONObject;


public class K2Subscribe {
//    The Variables
    private String client_id, client_secret, k2_uri, k2_request_body, webhook_secret, event_type, k2_response_token,
            k2_response_webhook, subscriber_access_token;

    public K2Subscribe (String event_type) {
        this.event_type = event_type;
    }

    // Method for sending the request to K2 sandbox or Mock Server (Receives the access_token)
    public void token_request(String client_id, String client_secret) {
        String token_params = new JSONObject()
                .put("client_id", client_id)
                .put("client_secret", client_secret)
                .put("grant_type", "client_credentials").toString();
        K2Connection.to_connect(token_params, "/ouath", this.subscriber_access_token, true, false);
    }

    // A Case condition that minimises repetition
    public void webhook_subscribe() {
        switch (this.event_type) {
            case "buygoods_transaction_received":
                System.out.println("Buy Goods Received");
                String bg_receieve_params = new JSONObject()
                        .put("event_type", "buygoods_transaction_received")
                        .put("url", "https://myapplication.com/webhooks")
                        .put("secret", "client_credentials").toString();
                K2Connection.to_connect(bg_receieve_params, "/webhook-subscription", this.subscriber_access_token, true, false);
                break;
            case "buygoods_transaction_reversed":
                System.out.println("Buy Goods Reversed");
                String bg_reversal_params = new JSONObject()
                        .put("client_id", "buygoods_transaction_reversed")
                        .put("url", "https://myapplication.com/webhooks")
                        .put("grant_type", "client_credentials").toString();
                K2Connection.to_connect(bg_reversal_params, "/buygoods-transaction-reversed", this.subscriber_access_token, true, false);
                break;
            case "customer_created":
                System.out.println("Customer Created");
                String customer_create_params = new JSONObject()
                        .put("client_id", "customer_created")
                        .put("url", "https://myapplication.com/webhooks")
                        .put("grant_type", "client_credentials").toString();
                K2Connection.to_connect(customer_create_params, "/customer-created", this.subscriber_access_token, true, false);
                break;
            case "settlement_transfer_completed":
                System.out.println("Settlement Transfer Completed");
                String settlement_transfer_params = new JSONObject()
                        .put("client_id", "settlement_transfer_completed")
                        .put("url", "https://myapplication.com/webhooks")
                        .put("grant_type", "client_credentials").toString();
                K2Connection.to_connect(settlement_transfer_params, "/settlement", this.subscriber_access_token, true, false);
                break;
            default:
                System.out.println("Nothing");
                break;
        }

    }
}
