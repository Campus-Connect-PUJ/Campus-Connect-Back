package CampusConnect.CCBack.Model;

final public class Rol {

    static public final short ADMIN    = 0;
    static public final short USER     = 1;
    static public final short MONITOR  = 2;

    // Si se quiere agregar nuevos roles, se tienen que poner de
    // ultimo, para evitar afectar los usuarios creados con los roles ya
    // existentes
    static private final String[] roles = {
        "ADMIN",
        "USER",
        "MONITOR"
    };

    public static String string(int rol) {
        return roles[rol];
    }

    public static boolean contain(Short idRol) {
        return 0 <= idRol && idRol < roles.length;
    }

}
