package ch7;

public class Quardtree2 {
    private static String input = "xxwwwbxwxwbbbwwxxxwwbbbwwwwbb";
    private static int index = 0;

    public static void main(String[] args) {
        String result;

        result = flip();

        System.out.println(result);
    }

    private static String flip() {
        if (input.charAt(index) == 'w' || input.charAt(index) == 'b') {
            return input.charAt(index++) + "";
        }

        index += 1;
        String one = flip();
        String two = flip();
        String three = flip();
        String four = flip();

        return "x" + three + four + one + two;
    }
}
