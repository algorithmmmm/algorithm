import java.util.*;

class MedianFinder {
    PriorityQueue<Integer> maxHeap, minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 내림차순 : 4, 3, 2, 1
        minHeap = new PriorityQueue<>(); // 오름차순 : 5, 6, 7
    }
    
    public void addNum(int num) {
        if(maxHeap.size() == minHeap.size()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            if(maxHeap.peek() > minHeap.peek()) {
                int min = minHeap.poll();
                int max = maxHeap.poll();
                minHeap.add(max);
                maxHeap.add(min);
            }
        }
    }
    
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */