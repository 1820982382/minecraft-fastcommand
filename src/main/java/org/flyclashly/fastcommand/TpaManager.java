package org.flyclashly.fastcommand;


import net.minecraft.server.network.ServerPlayerEntity;
import java.util.HashMap;
import java.util.Map;

public class TpaManager {
    private static final Map<ServerPlayerEntity, TpaRequest> requests = new HashMap<>();
    private static final long REQUEST_TIMEOUT = 60 * 1000; // 60秒超时

    public static void addRequest(ServerPlayerEntity sender, ServerPlayerEntity target) {
        requests.put(target, new TpaRequest(sender, System.currentTimeMillis()));
    }

    public static TpaRequest getRequest(ServerPlayerEntity target) {
        TpaRequest request = requests.get(target);
        if (request != null && System.currentTimeMillis() - request.timestamp > REQUEST_TIMEOUT) {
            requests.remove(target);
            return null;
        }
        return request;
    }

    public static void removeRequest(ServerPlayerEntity target) {
        requests.remove(target);
    }

    public static class TpaRequest {
        public final ServerPlayerEntity sender;
        public final long timestamp;

        public TpaRequest(ServerPlayerEntity sender, long timestamp) {
            this.sender = sender;
            this.timestamp = timestamp;
        }
    }
}