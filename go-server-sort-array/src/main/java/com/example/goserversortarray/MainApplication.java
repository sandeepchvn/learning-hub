package com.example.goserversortarray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.*;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}

@RestController
@RequestMapping("/api")
class SortController {

	private final ExecutorService executorService = Executors.newFixedThreadPool(5);

	@PostMapping("/process-single")
	public SortResponse processSingle(@RequestBody SortRequest request) {
		long startTime = System.nanoTime();
		int[][] sortedArrays = Arrays.stream(getNonNullArray(request.getToSort())).map(this::sortArray)
				.toArray(int[][]::new);
		long timeTaken = System.nanoTime() - startTime;
		return new SortResponse(sortedArrays, timeTaken);
	}

	@PostMapping("/process-concurrent")
	public SortResponse processConcurrent(@RequestBody SortRequest request) {
		long startTime = System.nanoTime();
		int[][] sortedArrays = new int[request.getToSort().length][];
		CountDownLatch latch = new CountDownLatch(request.getToSort().length);

		for (int i = 0; i < request.getToSort().length; i++) {
			int finalI = i;
			executorService.submit(() -> {
				sortedArrays[finalI] = sortArray(getNonNullArray(request.getToSort())[finalI]);
				latch.countDown();
			});
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Sorting interrupted", e);
		}

		long timeTaken = System.nanoTime() - startTime;
		return new SortResponse(sortedArrays, timeTaken);
	}

	private int[] sortArray(int[] array) {
		Arrays.sort(array);
		return array;
	}

	private int[][] getNonNullArray(int[][] array) {
		return (array != null) ? array : new int[0][];
	}
}

class SortRequest {
	private int[][] toSort;

	public int[][] getToSort() {
		return toSort;
	}

	public void setToSort(int[][] toSort) {
		this.toSort = toSort;
	}
}

class SortResponse {
	private int[][] sortedArrays;
	private long timeNs;

	public SortResponse() {
	}

	public SortResponse(int[][] sortedArrays, long timeNs) {
		this.sortedArrays = sortedArrays;
		this.timeNs = timeNs;
	}

	public int[][] getSortedArrays() {
		return sortedArrays;
	}

	public void setSortedArrays(int[][] sortedArrays) {
		this.sortedArrays = sortedArrays;
	}

	public long getTimeNs() {
		return timeNs;
	}

	public void setTimeNs(long timeNs) {
		this.timeNs = timeNs;
	}
}
