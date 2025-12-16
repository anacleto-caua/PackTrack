package validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValidatorMaster {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$"
    );

    private static final Pattern CPF_PATTERN = Pattern.compile(
            "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$"
    );

    private static final Pattern DECIMAL_PATTERN = Pattern.compile(
            "^\\d+(\\.\\d{1,2})?$"
    );

    private static final Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{4}-\\d{2}-\\d{2}$"
    );

    public ValidatorMaster(){}

    public static ArrayList<String> notEmpty(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value == null || value.trim().isEmpty()) {
            errors.add("Este campo não pode estar em branco");
        }
        return errors;
    }

    public static ArrayList<String> isEmail(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value != null && !EMAIL_PATTERN.matcher(value).matches()) {
            errors.add("Formato de e-mail inválido");
        }
        return errors;
    }

    public static ArrayList<String> isPhoneNumber(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value != null && !PHONE_PATTERN.matcher(value).matches()) {
            errors.add("Telefone inválido (use o formato XX 99999-9999)");
        }
        return errors;
    }

    // Ps. This fails to consider the mathematical formula for a valid CPF
    public static ArrayList<String> isCPF(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value != null && !CPF_PATTERN.matcher(value).matches()) {
            errors.add("CPF inválido (use o formato 000.000.000-00)");
        }
        return errors;
    }

    // Strict validation (max 2 decimal places, positive only) via Regex
    public static ArrayList<String> isDecimal(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value != null && !DECIMAL_PATTERN.matcher(value).matches()) {
            errors.add("Valor numérico inválido (use ponto para decimais, ex: 1500.00)");
        }
        return errors;
    }

    public static ArrayList<String> isBigDecimal(String value) {
        ArrayList<String> errors = new ArrayList<>();

        // We skip validation if empty/null because notEmpty() handles that check.
        if (value == null || value.trim().isEmpty()) {
            return errors;
        }

        try {
            new BigDecimal(value);
        } catch (NumberFormatException e) {
            errors.add("O valor deve ser um número válido (ex: 10.50)");
        }

        return errors;
    }

    public static ArrayList<String> isValidDate(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (value != null && !DATE_PATTERN.matcher(value).matches()) {
            errors.add("Data inválida (use o formato AAAA-MM-DD)");
        }
        return errors;
    }

    public static <E extends Enum<E>> ArrayList<String> isValidEnum(String value, Class<E> enumClass) {
        ArrayList<String> errors = new ArrayList<>();
        if (value == null || value.isEmpty()) {
            errors.add("Opção inválida");
            return errors;
        }

        try {
            Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            errors.add("O valor '" + value + "' não é uma opção válida para " + enumClass.getSimpleName());
        }
        return errors;
    }
}