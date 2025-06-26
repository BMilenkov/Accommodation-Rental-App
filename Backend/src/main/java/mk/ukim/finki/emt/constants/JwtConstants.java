package mk.ukim.finki.emt.constants;

public class JwtConstants {
    public static final String SECRET_KEY = System.getenv("SECRET_KEY");
    public static final Long EXPIRATION_TIME = 864000000L;
    public static final String HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
