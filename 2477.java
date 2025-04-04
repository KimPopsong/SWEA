import java.io.*;
import java.util.*;

class Solution {
	static int counterNumber, repairNumber, customerNumber; // 접수 창구, 정비 창구, 손님의 수
	static int mainCounter, mainRepair; // 지갑을 잃어버린 손님이 사용한 창구
	static int[] counters, repairs; // 각각의 창구별 소요 시간
	static ArrayDeque<Customer> customers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			// 입력부 시작

			st = new StringTokenizer(br.readLine());

			counterNumber = Integer.parseInt(st.nextToken());
			repairNumber = Integer.parseInt(st.nextToken());
			customerNumber = Integer.parseInt(st.nextToken());
			mainCounter = Integer.parseInt(st.nextToken());
			mainRepair = Integer.parseInt(st.nextToken());

			counters = new int[counterNumber];
			repairs = new int[repairNumber];
			customers = new ArrayDeque<>();

			st = new StringTokenizer(br.readLine()); // 접수 창구 입력
			for (int i = 0; i < counterNumber; i++) {
				counters[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine()); // 정비 창구 입력
			for (int i = 0; i < repairNumber; i++) {
				repairs[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine()); // 손님의 도착 시간 입력
			for (int i = 0; i < customerNumber; i++) {
				int arrTime = Integer.parseInt(st.nextToken());

				Customer customer = new Customer();
				customer.customerNumber = i + 1;
				customer.arrivalTime = arrTime;
				customers.addLast(customer);
			}

			// 입력부 종료

			// 접수 창구 완료 순서 계산
			PriorityQueue<Customer> repairWaiting = new PriorityQueue<>(new Comparator<Customer>() {
				// 정비 창구 도착 시간을 기준으로 오름차순. 동시 도착시 접수 창구 번호가 작은 순
				@Override
				public int compare(Customer c1, Customer c2) {
					if (c1.registerCompleteTime == c2.registerCompleteTime) {
						return Integer.compare(c1.counterNumber, c2.counterNumber);
					}

					else {
						return Integer.compare(c1.registerCompleteTime, c2.registerCompleteTime);
					}
				}
			}); // 정비 창구 대기 순서

			int[] endTime = new int[counterNumber];
			while (!customers.isEmpty()) {
				Customer customer = customers.removeFirst();

				boolean flag = false;
				int minIndex = 0, minTime = Integer.MAX_VALUE; // 탐색 실패시

				for (int i = 0; i < counterNumber; i++) { // 접수 창구 탐색
					if (endTime[i] <= customer.arrivalTime) {
						flag = true;

						customer.counterNumber = i + 1;
						customer.registerCompleteTime = customer.arrivalTime + counters[i];
						endTime[i] = customer.arrivalTime + counters[i];
						repairWaiting.add(customer);

						break;
					}

					else {
						if (endTime[i] < minTime) {
							minTime = endTime[i];
							minIndex = i;
						}
					}
				}

				if (flag == false) { // 창구 탐색에 실패시
					// 대기했다가 이용
					customer.counterNumber = minIndex + 1;
					customer.registerCompleteTime = minTime + counters[minIndex];
					endTime[minIndex] = minTime + counters[minIndex];
					repairWaiting.add(customer);
				}
			}

			// 접수 창구 완료 순서 계산 끝

			// 정비 창구 완료 순서 계산

			endTime = new int[repairNumber];
			while (!repairWaiting.isEmpty()) {
				Customer customer = repairWaiting.remove();

				boolean flag = false;
				int minIndex = 0, minTime = Integer.MAX_VALUE;

				for (int i = 0; i < repairNumber; i++) { // 정비 창구 탐색
					if (endTime[i] <= customer.registerCompleteTime) {
						flag = true;

						customer.repairNumber = i + 1;
						endTime[i] = customer.registerCompleteTime + repairs[i];
						customers.add(customer);

						break;
					}

					else {
						if (endTime[i] < minTime) {
							minTime = endTime[i];
							minIndex = i;
						}
					}
				}

				if (flag == false) { // 창구 탐색 실패시
					// 대기했다가 이용
					customer.repairNumber = minIndex + 1;
					endTime[minIndex] = minTime + repairs[minIndex];
					customers.add(customer);
				}
			}

			// 정비 창구 완료 순서 계산 끝

			// 지갑 찾기
			int sum = 0;

			while (!customers.isEmpty()) {
				Customer customer = customers.removeFirst();

				if (customer.counterNumber == mainCounter && customer.repairNumber == mainRepair) {
					sum += customer.customerNumber;
				}
			}

			if (sum == 0) {
				sum = -1;
			}

			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}

		System.out.print(sb);
	}

	static class Customer {
		int customerNumber; // 고객 번호
		int arrivalTime; // 도착 시간
		int counterNumber; // 접수 창구 번호
		int repairNumber; // 정비 창구 번호
		int registerCompleteTime; // 접수 완료 시간
	}
}
