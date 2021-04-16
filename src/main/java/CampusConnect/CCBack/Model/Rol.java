package CampusConnect.CCBack.Model;

final public class Rol {

    static public final short ADMIN = 0;
    static public final short USER  = 1;

    // Si se quiere agregar nuevos roles, se tienen que poner de
    // ultimo, para evitar afectar los usuarios creados con los roles ya
    // existentes
    static private final String[] roles = {
        "ADMIN",
        "USER"
    };

    public static String string(int rol) {
        return roles[rol];
    }

}
