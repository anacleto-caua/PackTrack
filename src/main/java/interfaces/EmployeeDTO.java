package interfaces;

public record EmployeeDTO(
        String name,
        String email,
        String password,
        String role, // TODO change to enum
        String salary,
        String commission // TODO change to decimal
) {
}
