import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int base = 2;
        int length = 8;
        int height = 4;
        boolean show_codewords = true;
        boolean show_error_calc = false;


        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> row_1 = new ArrayList<>();
        row_1.add(1);
        row_1.add(0);
        row_1.add(0);
        row_1.add(0);
        row_1.add(1);
        row_1.add(1);
        row_1.add(1);
        row_1.add(0);
        ArrayList<Integer> row_2 = new ArrayList<>();
        row_2.add(0);
        row_2.add(1);
        row_2.add(0);
        row_2.add(0);
        row_2.add(1);
        row_2.add(1);
        row_2.add(0);
        row_2.add(1);
        ArrayList<Integer> row_3 = new ArrayList<>();
        row_3.add(0);
        row_3.add(0);
        row_3.add(1);
        row_3.add(0);
        row_3.add(1);
        row_3.add(0);
        row_3.add(1);
        row_3.add(1);
        ArrayList<Integer> row_4 = new ArrayList<>();
        row_4.add(0);
        row_4.add(0);
        row_4.add(0);
        row_4.add(1);
        row_4.add(0);
        row_4.add(1);
        row_4.add(1);
        row_4.add(1);
        matrix.add(row_1);
        matrix.add(row_2);
        matrix.add(row_3);
        matrix.add(row_4);

        ArrayList<ArrayList<Integer>> codewords = generateCodewords(matrix, length, base);


        System.out.println("Generated " + codewords.size() + " Codewords");

        if (show_codewords) {
            displayWords(codewords);
        }

        boolean insert_error = true;
        Scanner sc = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> errors = new ArrayList<>();

        while (insert_error) {
            System.out.println("please add error (length must be " + length + " ). Enter char to stop.");
            ArrayList<Integer> error = new ArrayList<>();

            while (sc.hasNextInt()) {
                error.add(sc.nextInt());
            }


            Integer[] nums = error.toArray(new Integer[0]);
            ArrayList<Integer> error_arl = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < base && nums[i] >= 0) {
                    error_arl.add(nums[i]);
                } else {
                    System.out.println("Invalid number entered");
                }
            }
            if (error_arl.size() == length) {
                errors.add(error_arl);
            } else {
                System.out.println("error has wrong length");
            }

            if (sc.hasNextBoolean()) {
                insert_error = sc.nextBoolean();
            } else {
                insert_error = false;
            }
        }

        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        if (errors.size() > 0) {
            System.out.println("Found " + errors.size() + " errors");


            for (int h = 0; h < errors.size(); ++h) {
                ArrayList<ArrayList<Integer>> result_row = new ArrayList<>();
                if (show_error_calc) {
                    System.out.println("\nCurrent error: ");
                    for (int z = 0; z < length; ++z) {
                        System.out.print(errors.get(h).get(z));
                    }
                }

                for (int i = 0; i < codewords.size(); ++i) {
                    if (show_error_calc) {
                        System.out.println("\nCurrent codeword: ");
                        for (int z = 0; z < length; ++z) {
                            System.out.print(codewords.get(i).get(z));
                        }
                    }

                    ArrayList<Integer> result = new ArrayList<>();

                    for (int z = 0; z < length; ++z) {
                        result.add((errors.get(h).get(z) + codewords.get(i).get(z)) % base);
                        if (show_error_calc) {
                            System.out.print((errors.get(h).get(z) + codewords.get(i).get(z)) % base);
                        }
                    }
                    result_row.add(result);

                    if (show_error_calc) {
                        System.out.println("\nResulting Codeword: ");
                    }
                }

                boolean new_row = true;
                for(int j = 0; j < result_row.size(); ++j){
                    for(int p = 0; p < results.size(); ++ p){
                        if(equals(result_row.get(j),results.get(p))){
                            new_row = false;
                            break;
                        }
                    }
                }

                if(new_row){
                    for(int o = 0; o < result_row.size(); ++o){
                        results.add(result_row.get(o));
                    }
                }


            }

            for (int i = 0; i < codewords.size(); ++i) {
                for (int z = 0; z < length; ++z) {
                    System.out.print(codewords.get(i).get(z));
                }
                if (i != codewords.size() - 1) {
                    System.out.print(" | ");
                } else {
                    System.out.print("\\\\");
                }
            }

            System.out.print("\n");

            for (int i = 0; i < results.size(); ++i) {
                for (int z = 0; z < length; ++z) {
                    System.out.print(results.get(i).get(z));
                }
                if ((i + 1) % codewords.size() == 0 && i != 0) {
                    System.out.print("\\\\ \n");
                } else {
                    System.out.print(" | ");
                }
            }

        }
    }

    public static void generateWords(int length, int base, int hammingd){
        //not right n over k
        int number = base^(length);
    }

    public static ArrayList<ArrayList<Integer>> generateCodewords(ArrayList<ArrayList<Integer>> matrix, int length, int base) {
        ArrayList<ArrayList<Integer>> codewords = new ArrayList<>();
        ArrayList<Integer> current_codeword = new ArrayList<>();

        System.out.println("Generating Codewords: ");
        int letter_ind = 0;

        for (int i = 0; i < base; ++i) {
            for (int j = 0; j < base; ++j) {
                for (int k = 0; k < base; ++k) {
                    for (int l = 0; l < base; ++l) {
                        for (int z = 0; z < length; ++z) {
                            int result = i * matrix.get(0).get(z) + j * matrix.get(1).get(z) + k * matrix.get(2).get(z) + l * matrix.get(3).get(z);

                            if (letter_ind == length - 1) {
                                letter_ind = 0;
                                current_codeword.add(result % base);
                                codewords.add(current_codeword);
                                current_codeword = new ArrayList<>();
                            } else {
                                current_codeword.add(result % base);
                                letter_ind += 1;
                            }
                        }
                    }
                }
            }
        }
        return codewords;
    }

    public static void displayWords(ArrayList<ArrayList<Integer>> words) {
        for (int i = 0; i < words.size(); ++i) {
            printWord(words.get(i));
            System.out.print(" | ");
        }
        System.out.print("\n");
    }

    public static void printWord(ArrayList<Integer> word){
        for(int i = 0; i < word.size(); ++i){
            System.out.print(word.get(i));
        }
    }

    public static boolean equals(ArrayList<Integer> w1, ArrayList<Integer> w2){
        if(w1.size() == w2.size()){
            for(int i = 0; i < w1.size(); ++i){
                if(w1.get(i) != w2.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
