package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        detectAllWords(crossword, "fder", "home", "same");
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> wrd = new LinkedList<>();
        Trie trie = new Trie();

        int[][] dir = new int[][]{
                { 0, 1},
                { 1, 1},
                { 1, 0},
                { 1,-1},
                { 0,-1},
                {-1,-1},
                {-1, 0},
                {-1, 1}
        };

        for (String s : words) {
            trie.add(s);
        }

        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[0].length; j++) {
                if (trie.next[(char) crossword[i][j] - 'a'] != null) {
                    for (int k = 0; k < dir.length; k++) {
                        traverse(crossword, i, j, trie, dir[k], j, i, wrd);
                    }
                }
            }
        }

        wrd.forEach(System.out::println);

        return wrd;
    }

    private static void traverse(int[][] crossword, int i, int j, Trie trie, int[] dir,
                                 int startY, int startX, List<Word> res) {
        if (       i < 0
                || j < 0
                || i >= crossword.length
                || j >= crossword[0].length
                || trie.next[(char) crossword[i][j] - 'a'] == null) {
            return;
        }
        trie = trie.next[(char) crossword[i][j] - 'a'];
        if (trie.words.size() > 0) {
            for (String word : trie.words) {
                res.add(new Word(word, startY, startX, j, i));
            }
        }
        traverse(crossword, i + dir[0], j + dir[1], trie, dir, startY, startX, res);
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public Word(String text, int startX, int startY, int j, int i) {
            this.text = text;
            setStartPoint(startX, startY);
            setEndPoint(j, i);
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
