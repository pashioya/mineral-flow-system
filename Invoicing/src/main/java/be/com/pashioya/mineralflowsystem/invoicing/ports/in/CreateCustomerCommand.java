package be.com.pashioya.mineralflowsystem.invoicing.ports.in;

public record CreateCustomerCommand(String name, String address, String email, String vatNumber) {
}
