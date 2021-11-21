package employee;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EmployeeClient {

    private final WebClient client;

    public EmployeeClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8087").build();
    }

    public Mono<String> getMessage() {
        return this.client.get().uri("/employee").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Employee.class)
                .map(Employee::toString);
    }
}
