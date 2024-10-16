package be.com.pashioya.mineralflowsystem.warehousing.ports.in;

public record CreateMaterialCommand(String name, String description, double price, double storagePrice) {
}