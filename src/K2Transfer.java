import org.json.JSONObject;

public class K2Transfer {
    private String access_token;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token () {
        return this.access_token;
    }

    public K2Transfer (String access_token) {
        this.access_token = access_token;
    }

    // Create a Verified Settlement Account via API
    public void settlement_account (JSONObject transfer_params) {
        // Settlement Body
        String settlement_body = new JSONObject()
                .put("account_name", transfer_params.get("account_name").toString())
                .put("bank_ref", transfer_params.get("bank_ref").toString())
                .put("bank_branch_ref", transfer_params.get("bank_branch_ref").toString())
                .put("account_number", transfer_params.get("account_number").toString()).toString();
        K2Connection.to_connect(settlement_body, "/merchant_bank_accounts", this.access_token, false, false);
    }

    //  Create a either a 'blind' transfer, for when destination is specified, and a 'targeted' transfer which has a specified destination.
    public void transfer_funds (String destination, JSONObject transfer_params) {
        String transfer_body = new JSONObject()
                .put("amount", new JSONObject()
                        .put("currency", transfer_params.get("currency").toString())
                        .put("value", transfer_params.get("value").toString())).toString();
        K2Connection.to_connect(transfer_body, "/transfers", this.access_token, false, false);
    }

    // Check the status of a prior initiated Transfer.
    public void query_transfer (String id) {
        String query_body = new JSONObject()
                .put("ID", id).toString();
        K2Connection.to_connect(query_body, "/payments", this.access_token, false, true);
    }
}
