import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
        final String ID_REGEX = "^[C]\\d{4}[G|H|I|K]{1}\\d{2}$";
        final String EMAIL_REGEX = "^[a-zA-Z]{1,7}.[a-zA-Z]{2,7}(@codegym.vn){1}$";
        final String DATE_OF_BIRTH_REGEX = "^(0{1}[1-9]|[12][0-9]|3[01])-(0{1}[1-9]|1[0-2])-\\d\\d\\d\\d";
        final String GENDER_REGEX = "[1|2]";

        public boolean validateID(String string){
            Pattern pattern = Pattern.compile(ID_REGEX);
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
        public boolean validateEmail(String string){
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
        public boolean validateBirthDay(String string){
            Pattern pattern = Pattern.compile(DATE_OF_BIRTH_REGEX);
            Matcher matcher = pattern.matcher(string);
            return matcher.matches();
        }
        public boolean validateGender(String string){
            Pattern pattern = Pattern.compile(GENDER_REGEX);
            Matcher matcher = pattern.matcher(string);
            return ((Matcher) matcher).matches();
        }

    public boolean validate(String stringRegex, String string){
        Pattern pattern = Pattern.compile(stringRegex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    }

