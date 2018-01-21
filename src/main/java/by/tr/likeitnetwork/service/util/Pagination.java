package by.tr.likeitnetwork.service.util;

public final class Pagination {
    public static int countFromId(int page, int count) {
        return (page - 1) * count;
    }

    private Pagination() {
    }
}
