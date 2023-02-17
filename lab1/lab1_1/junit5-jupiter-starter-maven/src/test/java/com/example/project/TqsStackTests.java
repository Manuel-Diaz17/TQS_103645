package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TqsStackTests {

	private TqsStack<String> wordsStack;

	@BeforeEach // Execute before each test
	public void testBeforeEach() throws Exception {
		wordsStack = new TqsStack<>();
	}

	@AfterEach
    public void clear() {
        wordsStack.clear();
    }

	@DisplayName("A stack is empty on construction")
    @Test
    void isEmpty(){ 
        assertTrue(wordsStack.isEmpty()); 
        
        wordsStack.push("Test Empty");
        assertFalse(wordsStack.isEmpty());
        
        wordsStack.pop();
        assertTrue(wordsStack.isEmpty());
    }

	@DisplayName("A stack has a size 0 on construction")
    @Test
    void size(){ 
        assertEquals(0, wordsStack.size()); 

        wordsStack.push("Test Size");
        assertEquals(1, wordsStack.size());

        wordsStack.pop();
        assertEquals(0, wordsStack.size());

    }

	@DisplayName("After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    @Test
    void pushFromEmpty(){
		assertTrue(wordsStack.isEmpty());

		wordsStack.push("a");
		wordsStack.push("b");
		wordsStack.push("c");

		assertEquals(wordsStack.size(), 3);
		assertFalse(wordsStack.isEmpty());
	}

	@DisplayName("If one pushes x then pops, the value popped is x")
    @Test
	void pushThenPop(){
		wordsStack.push("x");
		assertTrue(wordsStack.pop().equals("x"));
	}

	@DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same")
    @Test
    void pushThenPeek(){
		wordsStack.push("x");

		int stacksize = wordsStack.size();

		assertTrue( (wordsStack.peek() == "x") && (wordsStack.size() == stacksize));
	}

	@DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void emptyAfterPop(){
		wordsStack.push("a");
		wordsStack.push("b");
		wordsStack.push("c");

		assertEquals(wordsStack.size(), 3);
		assertFalse(wordsStack.isEmpty());

		wordsStack.pop();
		wordsStack.pop();
		wordsStack.pop();

		assertTrue( (wordsStack.isEmpty()) && (wordsStack.size() == 0) );
	}

	@DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void popFromEmpty(){ 
		assertThrows(NoSuchElementException.class, () -> wordsStack.pop()); 
	}

	@DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peekFromEmpty(){ 
		assertThrows(NoSuchElementException.class, () -> wordsStack.peek()); 
	}

	@DisplayName("For bounded stacks only: pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushForBound(){
        TqsStack<String> boundStack = new TqsStack<>(1);

        boundStack.push("a");

        assertThrows(IllegalStateException.class, () -> boundStack.push("b")); 
    };

}
