package com.wan.t12_add_search_functionality;

/**
 * @author wzc
 * @date 2018/2/10
 */
public class Result {
    private String mWord;
    private String mDefinition;

    public Result(String word, String definition) {
        mWord = word;
        mDefinition = definition;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getDefinition() {
        return mDefinition;
    }

    public void setDefinition(String definition) {
        mDefinition = definition;
    }

    @Override
    public String toString() {
        return mWord+"\n"+mDefinition;
    }
}
