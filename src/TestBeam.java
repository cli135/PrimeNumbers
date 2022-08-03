public class TestBeam {
    public static void main(String[] args) {
        int[] dimensions = {3, 2};
        int[] your_position = {1, 1};
        int[] trainer_position = {2, 1};
        int distance = 4;
        int test = BeamPoolTables.solution(dimensions, your_position, trainer_position, distance);
        System.out.println(test);

        int test2 = BeamPoolTables.solution(new int[]{300, 275},
                new int[]{150, 150},
                new int[]{185, 100},
                500);
        System.out.println(test2);

        int test3 = BeamPoolTables.solution(new int[]{10, 10},
                new int[]{4, 4},
                new int[]{3, 3},
                5000);
        System.out.println(test3);

        int test4 = BeamPoolTables.solution(new int[]{2, 5},
                new int[]{1, 2},
                new int[]{1, 4},
                11);
        System.out.println(test4);


        int test5 = BeamPoolTables.solution(new int[]{23, 10},
                new int[]{6, 4},
                new int[]{3, 2},
                23);
        System.out.println(test5);

        int test6 = BeamPoolTables.solution(new int[]{23, 10},
                new int[]{6, 4},
                new int[]{3, 2},
                23);
        System.out.println(test6);

        int test7 = BeamPoolTables.solution(new int[]{23, 10},
                new int[]{6, 4},
                new int[]{3, 2},
                23);
        System.out.println(test7);

        int test8 = BeamPoolTables.solution(new int[]{1250, 1250},
                new int[]{1000, 1000},
                new int[]{500, 400},
                10000);
        System.out.println(test8);




        //System.out.println(test2);

    }
}
