//package org.moviefusion;
//
//import java.io.*;
//import java.util.regex.*;
//
//public class CsvCleaner {
//
//    public static void main(String[] args) {
//    	 String inputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/Movies.csv";
//			String outputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/m.csv";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
//
//            String header = br.readLine();
//            bw.write(header);
//            bw.newLine();
//
//            String line;
//            int lineNumber = 1;
//            while ((line = br.readLine()) != null) {
//                lineNumber++;
//
//                // Fix unescaped inner quotes in JSON-like fields
//                String cleanedLine = sanitizeJsonFields(line);
//
//                if (hasUnterminatedQuotes(cleanedLine)) {
//                    System.out.println("⚠️ Skipping line " + lineNumber + ": Unterminated quotes");
//                    continue;
//                }
//
//                bw.write(cleanedLine);
//                bw.newLine();
//            }
//
//            System.out.println("✅ Cleaned file written to: " + outputFilePath);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Replace " with "" inside JSON-like strings that are surrounded by quotes
//    private static String sanitizeJsonFields(String line) {
//        // This regex finds quoted JSON strings in a CSV line
//        Pattern pattern = Pattern.compile("\"(\\[\\{.*?\\}\\])\"");
//        Matcher matcher = pattern.matcher(line);
//        StringBuffer result = new StringBuffer();
//
//        while (matcher.find()) {
//            String originalJson = matcher.group(1);
//            String escapedJson = originalJson.replace("\"", "\"\""); // escape inner quotes
//            matcher.appendReplacement(result, "\"" + escapedJson + "\"");
//        }
//
//        matcher.appendTail(result);
//        return result.toString();
//    }
//
//    // Check if a line has an odd number of quotes (unterminated)
//    private static boolean hasUnterminatedQuotes(String line) {
//        long count = line.chars().filter(ch -> ch == '"').count();
//        return count % 2 != 0;
//    }
//}


//package org.moviefusion;
//
//import java.io.*;
//import java.util.regex.*;
//
//public class CsvCleaner {
//
//    public static void main(String[] args) {
//        // Update file paths as needed
//        String inputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/c.csv";
//        String outputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/credit.csv";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
//
//            String header = br.readLine();
//            bw.write(header);
//            bw.newLine();
//
//            String line;
//            int lineNumber = 1;
//            while ((line = br.readLine()) != null) {
//                lineNumber++;
//
//                // Fix unescaped inner quotes in JSON-like fields
//                String cleanedLine = sanitizeJsonFields(line);
//
//                if (hasUnterminatedQuotes(cleanedLine)) {
//                    System.out.println("⚠️ Skipping line " + lineNumber + ": Unterminated quotes");
//                    continue;
//                }
//
//                bw.write(cleanedLine);
//                bw.newLine();
//            }
//
//            System.out.println("✅ Cleaned file written to: " + outputFilePath);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Replace " with "" inside JSON-like strings that are surrounded by quotes
//    private static String sanitizeJsonFields(String line) {
//        Pattern pattern = Pattern.compile("\"(\\[\\{.*?\\}\\])\"");
//        Matcher matcher = pattern.matcher(line);
//        StringBuffer result = new StringBuffer();
//
//        while (matcher.find()) {
//            String originalJson = matcher.group(1);
//            String escapedJson = originalJson.replace("\"", "\"\""); // escape inner quotes
//            String replacement = "\"" + escapedJson + "\"";
//            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
//        }
//
//        matcher.appendTail(result);
//        return result.toString();
//    }
//
//    // Check if a line has an odd number of quotes (unterminated)
//    private static boolean hasUnterminatedQuotes(String line) {
//        long count = line.chars().filter(ch -> ch == '"').count();
//        return count % 2 != 0;
//    }
//}



//package org.moviefusion;
//
//import java.io.*;
//import java.util.regex.*;
//
//public class CsvCleaner {
//
//    public static void main(String[] args) {
//        String inputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/m.csv";
//        String outputFilePath = "D:/Giri's Tech Hub/Movie Recom Project/MovieData/movie.csv";
//
//        int totalLines = 0;
//        int skippedLines = 0;
//        int writtenLines = 0;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
//             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
//
//            String header = br.readLine();
//            bw.write(header);
//            bw.newLine();
//
//            String line;
//            int lineNumber = 1;
//            while ((line = br.readLine()) != null) {
//                lineNumber++;
//                totalLines++;
//
//                // Clean line
//                String cleanedLine = sanitizeJsonFields(line);
//
//                if (hasUnterminatedQuotes(cleanedLine)) {
//                    System.out.println("⚠️ Skipping line " + lineNumber + ": Unterminated quotes → " + cleanedLine);
//                    skippedLines++;
//                    continue;
//                }
//
//                bw.write(cleanedLine);
//                bw.newLine();
//                writtenLines++;
//            }
//
//            System.out.println("\n✅ Cleaned file written to: " + outputFilePath);
//            System.out.println("📊 Total lines processed: " + totalLines);
//            System.out.println("✅ Lines written: " + writtenLines);
//            System.out.println("❌ Lines skipped: " + skippedLines);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Escapes inner quotes inside JSON-like array/object strings.
//     * Ensures any unquoted JSON-like data becomes safely quoted and escaped.
//     */
//    private static String sanitizeJsonFields(String line) {
//        Pattern pattern = Pattern.compile("(\\[\\{.*?\\}\\])"); // Matches JSON-like arrays
//        Matcher matcher = pattern.matcher(line);
//        StringBuffer result = new StringBuffer();
//
//        while (matcher.find()) {
//            String json = matcher.group(1);
//            String escapedJson = json.replace("\"", "\"\""); // Escape quotes
//            matcher.appendReplacement(result, "\"" + escapedJson + "\"");
//        }
//
//        matcher.appendTail(result);
//        return result.toString();
//    }
//
//    /**
//     * Checks if the number of double quotes in the line is odd.
//     */
//    private static boolean hasUnterminatedQuotes(String line) {
//        long count = line.chars().filter(ch -> ch == '"').count();
//        return count % 2 != 0;
//    }
//}




