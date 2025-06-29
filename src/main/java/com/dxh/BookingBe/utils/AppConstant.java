package com.dxh.BookingBe.utils;

public interface AppConstant {
    String SEARCH_OPERATOR = "(\\w+?)(:|<|>)(.*)";
//    String SEARCH_SPEC_OPERATOR = "(\\w+?)([<:>~!])(.*)(\\p{Punct}?)(\\p{Punct}?)";
//     String SEARCH_SPEC_OPERATOR = "(\\w+?)([<:>~!])(\\*?)(.+?)(\\*?)";
    String SORT_BY = "(\\w+?)(:)(.*)";
    String SEARCH_SPEC_OPERATOR = "([\\p{L}\\p{N}_]+?)([:><~!])([*]?)([^*]+)([*]?)";


}