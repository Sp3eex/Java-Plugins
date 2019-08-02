package _UtilsClasses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    public ListUtils() {}
  
    public static boolean stringListContainsString(List<String> list, String string) {
        return stringListContainsString(list, string, false);
    }
  
    public static boolean stringListContainsString(List<String> list, String string, boolean caseSensitive) {
        boolean contains = false;
        for (String entry : list) {
            if (caseSensitive) {
                if (entry.contains(string)) {
                    contains = true;
                }
            } else if (entry.toLowerCase().contains(string.toLowerCase())) {
                contains = true;
            }
        }
        return contains;
    }
 
    public static List<String> getStringListFromResultSet(ResultSet resultSet, String column) throws SQLException {
        List<String> list = new ArrayList<String>();
        while (resultSet.next()) {
            list.add(resultSet.getString(column));
        }
        return list;
    }
  
    public static String[] removeFirstStringFromArray(String[] array) {
        int n = array.length - 1;
        String[] newArray = new String[n];
        System.arraycopy(array, 1, newArray, 0, n);
        return newArray;
    }
  
    public static List<String> replaceInStringList(List<String> list, Object[] before, Object[] after) {
        if (before.length != after.length) {
            throw new IllegalArgumentException("before[] length must be equal to after[] length");
        }
        List<String> newList = new ArrayList<String>();
        for (String string : list) {
    	    for (int i = 0; i < before.length; i++) {
                string = string.replace(before[i].toString(), after[i].toString());
    	    }
          newList.add(string);
        }
        return newList;
    }
}
