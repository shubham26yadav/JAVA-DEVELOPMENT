import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Word_Count {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Count App!");
        System.out.print("Enter 'T' to type text or 'F' to provide a file: ");
        char choice = scanner.next().charAt(0);

        String inputText = "";
        if (choice == 'T' || choice == 't') {
            System.out.println("Enter your text:");
            scanner.nextLine(); // Consume newline left by previous next()
            inputText = scanner.nextLine();
        } else if (choice == 'F' || choice == 'f') {
            System.out.print("Enter the path to the file: ");
            String filePath = scanner.next();
            try {
                inputText = readFile(filePath);
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid choice. Exiting...");
            return;
        }

        String[] words = inputText.split("[\\s.,;?!]+"); // Split using space or punctuation as delimiters

        int totalWordCount = words.length;
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
        }

        System.out.println("Total word count: " + totalWordCount);
        System.out.println("Unique word count: " + wordFrequency.size());

        // Print word frequency statistics
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " occurrences");
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
