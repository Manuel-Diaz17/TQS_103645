package com.example.project;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
	private LinkedList<T> stack = new LinkedList<T>();
	private Integer bound = null;

	public TqsStack() {
		this.stack = new LinkedList<>();
	}

	public TqsStack(int bound) {
		this.stack = new LinkedList<>();
		this.bound = bound;
	  };

	public T pop(){
		if (this.stack.isEmpty()) { 
			throw new NoSuchElementException("Error popping element"); 	
		} 
		else { 
			return this.stack.pop(); 
		}
	}

	public int size(){
		return this.stack.size();
	}

	public T peek(){
		if (this.stack.isEmpty()) { 
			throw new NoSuchElementException("Error peaking element");
    	} 
		else { 
			return this.stack.getLast(); 
		}
	}

	public void push(T w){
		if ((this.bound != null) && (this.size() > this.bound - 1) ) {
			throw new IllegalStateException("Error pushing element!");
		}
		else{
			stack.add(w);
		}
	}

	public boolean isEmpty(){
		return this.size() == 0;
	}

	public void clear() {
		this.stack.clear();
	}

}
