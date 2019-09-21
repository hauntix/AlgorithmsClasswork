import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmsExercise {

    public static void main(String[] args) {
        int[] arr0 = {1,5,7,8,9,11,18,19,20,25};
        int[] arr1 = {1,2,3,4,5};

        int[] permutation0 = {1,2};
        int[] permutation1 = {1,2,3};

        List<String> example = new ArrayList<String>();
        example.add("abc");
        example.add("bcd");
        example.add("bbb");
        example.add("ace");
        example.add("snb");
        example.add("aaaa");
        example.add("bbbb");
        example.add("eeee");

        String input0 = "3+2*5-1";
        String input1 = "1+2";

        System.out.println(findTarget(arr0, 11));
        System.out.println(findTarget(arr1, 10));


        printPermutn(permutation0,0);
        System.out.println();
        printPermutn(permutation1, 0);
        System.out.println();

        System.out.println(Arrays.toString(groupString(example)));

        System.out.println(input0 + "= " + calculate(input0));
        System.out.println(input1 + "= " + calculate(input1));

    }

    private static int findTarget(int[] arr, int target) {
        int index = -1;
        int cielingIndex = arr.length;

        while(index+1 < cielingIndex) {
            int distance = cielingIndex - index;
            int halfDistance = distance /2;
            int guessIndex = index + halfDistance;

            int guessValue = arr[guessIndex];

            if(guessValue == target)
                return guessIndex;

            if(guessValue > target){
                cielingIndex = guessIndex;
            } else {
                index = guessIndex;
            }
        }

        return -1;
    }

    private static void printPermutn(int[] arr, int index) {
        int size = arr.length;


        if(size == index ){
            for (int value : arr) {
                System.out.print(value);
            }
            System.out.print(", ");
        } else {
            for (int i = index; i < size; i++) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                printPermutn(arr, index+1);
            }
        }
    }

    public static List<String>[] groupString(List<String> input) {
        List<String>[] result = new List[3];
        List<String> groupA = new ArrayList<String>();
        List<String> groupB = new ArrayList<String>();
        List<String> groupOther = new ArrayList<String>();

        for (int i = 0; i < input.size(); i++) {
            char firstChar = input.get(i).charAt(0);

            switch (firstChar) {
                case 'a':
                    groupA.add(input.get(i));
                    break;
                case 'b':
                    groupB.add(input.get(i));
                    break;
                default:
                    groupOther.add(input.get(i));
            }
        }

        result[0] = groupA;
        result[1] = groupB;
        result[2] = groupOther;

        return result;
    }

    public static int calculate(String s){

        String operands[] = s.split("[+-/*]");
        String operators[] = s.split("[0-9]");

        List<String> nums = new ArrayList<String>(Arrays.asList(operands));
        List<String> actions =  new ArrayList<String>(Arrays.asList(operators));

        int result = Integer.parseInt(operands[0]);

        actions.remove(0);


        int leftSide;
        int rightSide;

        int size = actions.size();
        while (size >=0) {

            //do multiplying first
            for (int i = 0; i < actions.size(); i++) {

                if (actions.get(i).equals("*")) {

                    leftSide = Integer.parseInt(nums.get(i));
                    rightSide = Integer.parseInt(nums.get(i + 1));
                    nums.remove(i + 1);
                    nums.remove(i);
                    actions.remove(i);

                    result = leftSide * rightSide;
                    nums.add(i,Integer.toString(result));
                }

            }
            size--;
        }

        size = actions.size();
        while(size>=0) {
            // do addition
            for (int i = 0; i < actions.size(); i++) {
                if (actions.get(i).equals("+")) {
                    result = Integer.parseInt(nums.get(i)) + Integer.parseInt(nums.get(i + 1));
                    nums.remove(i + 1);
                    nums.remove(i);
                    actions.remove(i);
                    nums.add(i, Integer.toString(result));
                }
            }
            size--;
        }

        size = actions.size();
        while (size>=0) {
            // do subtraction
            for (int i = 0; i < actions.size(); i++) {
                if (actions.get(i).equals("-")) {
                    result = Integer.parseInt(nums.get(i)) - Integer.parseInt(nums.get(i + 1));
                    nums.remove(i + 1);
                    nums.remove(i);
                    actions.remove(i);
                    nums.add(i, Integer.toString(result));
                }
            }
            size--;
        }

        return result;
    }
}
