
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PriorityQueueTest {

    @Test
    void add_Default_True() {
        PriorityQueue<String> queue= new PriorityQueue<String>(10);
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        Object []array = new Object[10];
        array[0]="a";
        array[1]="b";
        array[2]="c";
        array[3]="d";
        array[4]="e";
        assertEquals(queue.getSize(), 5);
        assertArrayEquals(queue.toArray(),array);
    }
    @Test
    void remove_Default_True() {
        Object []array = new Object[10];
        array[0]="a";
        array[1]="bbb";
        array[2]="c";
        array[3]="d";
        array[4]="e";
        array[5]="x";
        array[6]="z";
        array[7]="k";

        PriorityQueue<String> queue = PriorityQueueFabric();
        queue.remove("baa");
        assertEquals(queue.getSize(), 8);
        assertArrayEquals(queue.toArray(),array);
    }
    @Test
    void extractMaximum_Default_True() {
        PriorityQueue<String> queue = PriorityQueueFabric();
        assertEquals(queue.extractMaximum(),"a");
    }


    PriorityQueue<String> PriorityQueueFabric() {
        PriorityQueue<String> queue= new PriorityQueue<String>(10);
        queue.add("a");
        queue.add("bbb");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        queue.add("x");
        queue.add("z");
        queue.add("k");
        queue.add("baa");
        return queue;
    }

}
