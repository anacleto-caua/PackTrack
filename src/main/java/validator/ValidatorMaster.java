package validator;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValidatorMaster {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$"
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
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            errors.add("Formato de e-mail inválido");
        }
        return errors;
    }

    public static ArrayList<String> isPhoneNumber(String value) {
        ArrayList<String> errors = new ArrayList<>();
        if (!PHONE_PATTERN.matcher(value).matches()) {
            errors.add("Telefone inválido (use o formato XX 99999-9999)");
        }
        return errors;
    }
}