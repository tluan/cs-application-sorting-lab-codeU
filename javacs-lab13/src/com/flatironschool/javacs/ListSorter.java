/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
       int HalfSize = list.size()/2;
       List<T> listA = new ArrayList<T>();
       List<T> listB = new ArrayList<T>();
       for(int i = 0; i < list.size();i++){
    	   if(i<HalfSize){
    		   listA.add(list.get(i));
    		   
    	   }else{
    		   listB.add(list.get(i));
    	   }
       }
       if (listA.size() < 5) {
			insertionSort(listA, comparator);		
			insertionSort(listB, comparator);
		}
		else {
			listA = mergeSort(listA, comparator);
			listB = mergeSort(listB, comparator);
		}
    
       List<T> sortedList = merge(listA,listB,comparator);
       return  sortedList;
       
	}
	public List<T> merge(List<T> list1,List<T> list2,Comparator<T> comparator){
	int i = 0;
	
	for(int j = 0; j < list2.size();j++){
		T object = list2.get(j);
		boolean m = true;
		while(m){
			if(comparator.compare(object, list1.get(i))<=0){
				list1.add(i,object);
				m = false;
				}
			i++;
			}
		}
	return list1;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
		PriorityQueue<T> heapSort = new PriorityQueue(list.size(), comparator);
		heapSort.addAll(list);
		for (int i = 0; i < list.size(); i++) {
			list.set(i, heapSort.poll());
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
//		List<T> sortedlist = new LinkedList<T>(list); 
//		insertionSort(sortedlist,comparator);
//		for(int i = sortedlist.size() - k -1; i < sortedlist.size();i++){
//			sortedlist.remove(i);
//		}
//		return sortedlist;
		PriorityQueue<T> heap = new PriorityQueue(list.size(), comparator);
		heap.addAll(list);
		Stack<T> stack = new Stack<T>();
		int i =  heap.size();
			//if(i>heap.size()-k-1){
			//stack.push(heap.poll());
				while(heap.size()!=k){
				heap.poll();
				//System.out.println(heap.toString());
				
			//}
		}
		List<T> sortedlist = new ArrayList<T>();
		while(heap.size()!=0){
			sortedlist.add(heap.poll());
		}
		return sortedlist;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
