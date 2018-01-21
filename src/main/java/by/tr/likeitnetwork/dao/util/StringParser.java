package by.tr.likeitnetwork.dao.util;

import java.util.ArrayList;
import java.util.List;

public final class StringParser {
    private static String DELIMITER = ",";

    public static List<Integer> getIdListFromString (String line) {
        List<Integer> idList = new ArrayList<>();
        if (line == null){
            return idList;
        }
        String[] ids = line.split(DELIMITER);
        for (String id : ids) {
            idList.add(Integer.parseInt(id));
        }
        return idList;
    }

    private StringParser() {
    }
}
