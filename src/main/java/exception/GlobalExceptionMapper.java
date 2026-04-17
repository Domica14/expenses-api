package exception;

import java.util.Map;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<ApiException> {
    
    @Override
    public Response toResponse(ApiException exception) {
        return Response.status(exception.status)
                    .entity(Map.of(
                        "message", exception.getMessage()
                    ))
                    .build();
    }
}
