package interfaces;

public record ProductDTO(
        String name,
        String description,
        String price //TODO: USE SOMETHING MORE SUITABLE THAN INTEGER
) { }
