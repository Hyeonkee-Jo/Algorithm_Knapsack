import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

/**
 * Created by jo930_000 on 2016-11-21.
 */
public class Knapsack {
    ArrayList<Item> item = new ArrayList<>();
    ArrayList<Item> composed_item = new ArrayList<>();
    int input_weight;
    int max = 0;

    public void make_item() throws  IOException {
        FileInputStream input = new FileInputStream("C:/Users/jo930_000/IdeaProjects/Algorithm_hw09/src/data11.txt");
        InputStreamReader reader = new InputStreamReader(input);
        StreamTokenizer token = new StreamTokenizer(reader);

        while((token.nextToken() != StreamTokenizer.TT_EOF)) {
            switch(token.ttype){
                case StreamTokenizer.TT_NUMBER :
                    int item_number = (int)token.nval;
                    token.nextToken();
                    token.nextToken();
                    int weight = (int)token.nval;
                    token.nextToken();
                    token.nextToken();
                    int value = (int)token.nval;
                    item.add(new Item(item_number, weight, value));
                    break;
            }
        }
        input.close();
    }

    public int opt(int item_count, int weight) {
        if(item_count == 0) return 0;
        else if(item.get(item_count-1).weight > weight) return opt(item_count-1, weight);

        else return Math.max(opt(item_count-1, weight), item.get(item_count-1).value + opt(item_count-1, weight - item.get(item_count-1).weight));
    }

    public void print_table(int weight) {
        input_weight = weight;
        for(int i = 0; i <= item.size(); i++) {
            if (i != 0) System.out.print("\n");
            for(int j = 0; j <= weight; j++) {
                if(max <= this.opt(i, j)) max = this.opt(i, j);
                System.out.print(this.opt(i, j) + "\t");
            }
        }
        System.out.print("\nmax : " + max + "\nitem : ");
    }

    public void item_backtracking() {
        int value_sum = 0;
        int weight_sum = 0;
        int i = 0;
        boolean flag = true;

        for(int j = item.size()-1; j >=0; j--) {
            int size = j-1;
            while(size >= 0) {
                composed_item.add(item.get(j));
                value_sum += item.get(j).value;
                weight_sum += item.get(j).weight;
                for(i = size; i >= 0; i--) {
                    if(value_sum <= max && weight_sum <= input_weight) {
                        if(value_sum + item.get(i).value <= max && weight_sum + item.get(i).weight <= input_weight) {
                            composed_item.add(item.get(i));
                            value_sum += item.get(i).value;
                            weight_sum += item.get(i).weight;
                        }
                    }
                }

                if(value_sum == max && weight_sum <= input_weight) {
                    flag = false;
                    j = -1;
                    break;
                }
                else {
                    composed_item.clear();
                    value_sum = 0;
                    weight_sum = 0;
                    size--;
                }

            }
        }

        for(int n = composed_item.size()-1; n > 0; n--) {
            System.out.print(composed_item.get(n).item + ", ");
        }
        System.out.print(composed_item.get(0).item);
    }
}
