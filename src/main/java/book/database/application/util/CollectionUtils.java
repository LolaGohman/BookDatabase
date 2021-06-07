package book.database.application.util;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils () {
        //hides public constructor
    }

    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
