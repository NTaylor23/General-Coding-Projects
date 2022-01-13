public class Snail {

    public static int[] snail(int[][] array) {
      
        if(array.length < 1 || array[0].length < 1) return new int[]{};
      
        int limit = array.length - 1;
        int len = (int) Math.pow(array.length, 2);
        int[] result = new int[len];
        int row = 0; int col = 0, base = 0;
        
        for (int i = 0; i < len; i++) {

            result[i] = array[col][row];

            if (row == base & col == base + 1){
                limit--; base++; row = base; col = base;
            } else if (row < limit && col == base){
                row++;
            } else if (row == limit && col < limit){
                col++;
            } else if (col == limit && row != base){
                row--;
            } else if (row == base){
                col--;
            }

        }

        return result;
   } 
}
