package state;

import io.restassured.response.Response;

public final class GlobalState {

	private static GlobalState INSTANCE;
	private Response response;
    
    private GlobalState() {        
    }
    
    public static GlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new GlobalState();
        }
        
        return INSTANCE;
    }

    public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
