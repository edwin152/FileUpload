package cn.ddzu.shop.util;

public class DataUtils {

    private static Long lastId;

    public synchronized static Long generateId(){
        Long currentId = System.currentTimeMillis();

        while (lastId != null && lastId >= currentId) {
            currentId ++;
        }

        lastId = currentId;
        return lastId;
    }
}
