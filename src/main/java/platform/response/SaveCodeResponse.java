package platform.response;


import java.util.UUID;

public class SaveCodeResponse {
    UUID id;

    public UUID getId() {
        return id;
    }

    public SaveCodeResponse(UUID id) {
        this.id = id;
    }
}
