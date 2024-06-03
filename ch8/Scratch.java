package ch8;

class Scratch {
    static int[] cache = new int[10000];
    //static int[] array = {1,2,3,4,1,2,3,4,};
    //static int[] array = {1,1,1,1,1,2,2,2};
    //static int[] array = {1,2,1,2,2,2,2,2};
    //static int[] array = {2,2,2,2,2,2,2,2};
    static int[] array = {1,2,6,7,3,9,3,9};

    public static void main(String[] args) {


        for (int i = 0; i < cache.length; i++) {
            cache[i] = -1;
        }

        System.out.println(pi(0));
    }

    private static int pi(int index) {
        if(index == array.length) return 0;
        if(index > array.length) return 100000;

        int ret = cache[index];
        if(ret != -1) return ret;

        int threeChunk = checkLevel(index, index + 3);
        int fourChunk = checkLevel(index, index + 4);
        int fiveChunk = checkLevel(index, index + 5);

        ret = Math.min(threeChunk + pi(index+3),  Math.min(fourChunk + pi(index+4), fiveChunk + pi(index+5)));
        cache[index] = ret;

        return ret;
    }

    private static int checkLevel(int start, int end) {
        if(end > array.length) return 100000;

        boolean checkOne = true;
        boolean checkTwo = true;
        boolean checkThree = true;
        boolean checkFour = true;

        int checkOneVar = -1;
        int checkTwoVar = 0;
        int checkThreeVar1 = -1;
        int checkThreeVar2 = -1;
        int checkFourVar = 0;

        for(int i = start; i < end; i++) {
            // level 1
            if(checkOneVar == -1) {
                checkOneVar = array[i];
            } else {
                if(checkOneVar != array[i]) {
                    checkOne = false;
                }
            }

            // level 2
            if(i != start) {
                if(i == start+1) {
                    checkTwoVar = array[i] - array[start];
                    if(checkTwoVar != 1 && checkTwoVar != -1){
                        checkTwo = false;
                    }
                } else {
                    if(array[i] - array[i-1] != checkTwoVar){
                        checkTwo = false;
                    }
                }
            }

            // level 4
            if (i == start) {
                checkThreeVar1 = array[i];
            } else if(i == start+1) {
                checkThreeVar2 = array[i];
            } else if((i-start)%2 == 0) {
                if(array[i] != checkThreeVar1) {
                    checkThree = false;
                }
            } else {
                if(array[i] != checkThreeVar2) {
                    checkThree = false;
                }
            }

            // level 5
            if(i != start) {
                if(i == start+1) {
                    checkFourVar = array[i] - array[start];
                } else {
                    if(array[i] - array[i-1] != checkFourVar){
                        checkFour = false;
                    }
                }
            }

        }

        if(checkOne) return 1;
        else if (checkTwo) return 2;
        else if (checkThree) return 4;
        else if (checkFour) return 5;
        else return 10;
    }
}