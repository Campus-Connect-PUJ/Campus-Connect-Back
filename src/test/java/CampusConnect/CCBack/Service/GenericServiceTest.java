package CampusConnect.CCBack.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class GenericServiceTest {


    static public boolean equal(Object o1, Object o2) {
        // if (o1 instanceof PersistentBag) {
        //       CollectionUtils.isEqualCollection(
        //           ((List)o1).getItems() != null ? order1.getItems() : new ArrayList(),
        //           o2.getItems() != null ? order2.getItems() : new ArrayList());
        // }

        assertEquals(o1, o2);
        return true;
    }

    // Comparar todos los campos de dos objetos, excepto el id
    static public boolean compareAllExceptId(Object o1, Object o2) {

        var fields1 = o1.getClass().getDeclaredFields();
        var fields2 = o2.getClass().getDeclaredFields();

        int tam = fields1.length;
        assertEquals(tam, fields2.length);

        for (int i = 0; i < tam ; i++) {
            var field = fields1[i];
            var field2 = fields2[i];

            field.setAccessible(true);
            field2.setAccessible(true);

            String name = field.getName();
            if (name != "id") {
                Object value = null;
                Object value2 = null;
                try {
                    value = field.get(o1);
                    value2 = field2.get(o2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.printf("%s: %s%n", name, value);

                equal(value, value2);
            }
        }
        return true;
    }
}
