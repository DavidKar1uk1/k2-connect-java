public class ze_main {
    public static void main(String[] args) {
        K2Subscribe k2Subscribe = new K2Subscribe("settlement_transfer_completed");
        k2Subscribe.token_request("client_id", "client_secret");
        k2Subscribe.webhook_subscribe();
        K2Stk k2Stk = new K2Stk(K2Connection.getAccess_token());
    }
}
