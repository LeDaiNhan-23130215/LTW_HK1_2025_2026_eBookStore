package DTO;

import java.util.List;

public class EbookFilterView {
    private Boolean free; // true = free, false = paid, null = all
    private List<Integer> categoryId;
    private List<String> formats;

    private String sortBy; // title, price, created_at
    private String sortDir; // asc, desc

    private int page;
    private int pageSize;
    //TODO
}
