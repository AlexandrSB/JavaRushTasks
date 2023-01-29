package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    Trie[] next;
    List<String> words;

    public Trie() {
        this.next = new Trie[26];
        words = new ArrayList<>();
    }

    void add(String word) {
        Trie cur = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next[c - 'a'] == null)
                cur.next[c - 'a'] = new Trie();
            cur = cur.next[c - 'a'];
        }
        cur.words.add(word);
    }
}
