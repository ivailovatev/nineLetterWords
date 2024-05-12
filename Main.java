import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> validWords = new ArrayList<>();
        Set<String> words = loadWords(); 
        System.out.println(words);
        for (String word : words) {
            if (isValidWord(word, words)) {
                validWords.add(word);
            }
        }
        for (String word : validWords) {
            System.out.println(word);
        }
    }

    private static Set<String> loadWords() {
        Set<String> words = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static boolean isValidWord(String word, Set<String> dictionary) {
        if(word.length()!=9) return false;
        Set<String> visited = new HashSet<>();
        Set<String> queue = new HashSet<>();
        queue.add(word);
        while (!queue.isEmpty()) {
            String currentWord = queue.iterator().next();
            queue.remove(currentWord);
            visited.add(currentWord);
            if (currentWord.length() == 1 && (currentWord.equalsIgnoreCase("I") || currentWord.equalsIgnoreCase("A"))) {
                return true;
            }
            for (int i = 0; i < currentWord.length(); i++) {
                String shorterWord = currentWord.substring(0, i) + currentWord.substring(i + 1);
                if (dictionary.contains(shorterWord) && !visited.contains(shorterWord)) {
                    queue.add(shorterWord);
                }
            }
        }
        return false;
    }
}
