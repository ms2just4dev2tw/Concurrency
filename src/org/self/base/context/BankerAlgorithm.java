package org.self.base.context;

/**
 * <h3>银行家算法</h3>
 * 1，每有一个新进程进入系统，它必须申明它在运行过程中需要每种资源类型的<b>最大单元数目</b>
 * <p>
 * 2，系统会确定是否右足够的资源分配给该进程
 * <p>
 * 3，再进一步计算如果将这些资源分配给进程，会不会使得系统处于不安全的状态
 * <p>
 * <h3>银行家算法的数据结构</h3>
 * 1，可使用资源总量Available，这是一个长度为m的数组，代表m种资源
 * <p>
 * 2，最大需求矩阵Max，这是一个n x m的矩阵，代表n个进程对于m种资源的最大需求量
 * <p>
 * 3，分配矩阵Allocation，这是一个n x m的矩阵，代表n个进程对于m种资源的已分配量
 * <p>
 * 4，需求矩阵Need，这是一个n x m的矩阵，代表n个进程对于m种资源的还需要分配的资源
 * <p>
 * Need[ i, j ] =  Max[ i, j ] - Allocation[ i, j ]
 * <p>
 * <h3>银行家算法的安全性算法</h3>
 * 1，工作向量Work初始化为可使用资源总量Available，这是一个长度为m的数组
 * <p>
 * 2，从需求矩阵Need中找到满足 Need[ i ] <= Work的所有进程<b>i</b> 
 * <p>
 * 3，从找到的所有进程<b>i</b>中找到分配矩阵Allocation中占有资源最多的一个进程<b>j</b>
 * <p>
 * 4，假设完成进程<b>j</b>之后，释放它占有的资源，<b>Work = Work + Allocation [ j ]</b>
 * <p>
 * 5，转到步骤2，最后找到系统的安全序列
 * 
 * @author TungWang
 * @Description 原先是Dijkstra为银行系统设计的，
 * <p>
 * 以确保在发放贷款时，不会发生不能满足所有客户的情况
 * <p>
 * 在OS中，使用它来避免死锁
 */
public class BankerAlgorithm {

	private enum SignEnum {
		// 二维矩阵存储数据
		MAX("总需求"), NEED("还需要"), ALLOCATION("已分配"),
		// 一维数组存储数据
		AVAILABLE("可使用"), WORK("预期量"), REQUEST("请求量");

		private String name;

		SignEnum(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum RequestStatusEnum {
		// 请求超过需求
		OUT_NEED("请求超过需求\r\n"),
		// 请求超过系统的可分配资源
		OUT_AVAILABLE("请求超过系统的可分配资源\r\n"),
		// 请求会使得系统处于不安全的状态
		UNSAFE_STATUS("请求会使得系统处于不安全的状态\r\n"),
		// 请求成功
		SUCCESS("请求成功\r\n");

		private String label;
		private StringBuilder status;

		RequestStatusEnum(String label) {
			this.label = label;
			status = new StringBuilder(label);
		}

		public RequestStatusEnum addInfoToStatus(String info) {
			status.append(info).append("\r\n");
			return this;
		}

		@Override
		public String toString() {
			String info = status.toString();
			status.replace(0, status.length(), label);
			return info;
		}
	}

	private int num4prc; // 所有的进程个数
	private int num4res; // 所有的资源种类
	private int Max[][]; // 最大需求矩阵
	private int Need[][]; // 需求矩阵
	private int Allocation[][]; // 已分配矩阵
	private int Available[]; // 可使用资源总量
	private int Work[]; // 预期可使用量

	/**
	 * 模拟系统的进程现场
	 * <p>
	 * 进程个数为5，系统的资源种类为3
	 */
	public BankerAlgorithm(int available[], int max[][], int allocation[][]) {
		this.num4prc = max.length;
		this.num4res = available.length;

		this.Max = max;
		this.Available = available;
		this.Allocation = allocation;

		Need = new int[num4prc][num4res];
		// 更新 需求矩阵Need 和 可使用资源总量Available 的数据
		for (int i = 0; i < num4prc; i++)
			for (int j = 0; j < num4res; j++) {
				Need[i][j] = Max[i][j] - Allocation[i][j];
				Available[j] -= Allocation[i][j];
			}
	}

	/**
	 * 分配资源的请求
	 * 
	 * @param pid 进程号
	 * @param request m种资源的请求数组
	 * @return
	 */
	public RequestStatusEnum requestSource(int pid, int[] request) {
		// 判断进程pid的请求资源量是否超过原先的最大需求
		for (int i = 0; i < num4res; i++)
			if (request[i] > Need[pid][i])
				return RequestStatusEnum.OUT_NEED;
		// 判断当前系统可分配的资源能否满座进程pid的请求
		for (int i = 0; i < num4res; i++)
			if (request[i] > Available[i])
				return RequestStatusEnum.OUT_AVAILABLE;

		// 进入安全性算法检查前，系统假设按照request分配了资源
		for (int i = 0; i < num4res; i++) {
			Available[i] -= request[i];
			Need[pid][i] -= request[i];
			Allocation[pid][i] += request[i];
		}

		// 根据安全性算法的结果选择是否回滚Available，Need，Allocation的状态
		RequestStatusEnum status = Security();
		if (status == RequestStatusEnum.UNSAFE_STATUS)
			for (int i = 0; i < num4res; i++) {
				Available[i] += request[i];
				Need[pid][i] += request[i];
				Allocation[pid][i] -= request[i];
			}
		return status;
	}

	/**
	 * 判断该请求是否会使得系统处于不安全的状态
	 * 
	 * @param pid 进程号
	 * @param request m种资源的请求数组
	 * @return
	 */
	private RequestStatusEnum Security() {
		// 期望可分配资源
		Work = new int[num4res];
		for (int i = 0; i < num4res; i++)
			Work[i] = Available[i];

		// 初始状态: 0，加入筛选队列: 1，加入安全序列: -1
		int readyPids[] = new int[num4prc];
		for (int i = 0; i < num4prc; i++)
			readyPids[i] = 0;

		// 保存状态信息
		StringBuilder info = new StringBuilder();
		// 执行次数最多等于进程数
		for (int times = 0; times < num4prc; times++) {
			// 1，从需求矩阵Need中筛选出小于 work 的进程 pid
			int limit = -1;
			for (int pid = 0, index = 0; pid < num4prc; pid++) // 进程遍历
				if (readyPids[pid] == 0) { // 过滤掉已经放入安全序列的进程
					for (index = 0, readyPids[pid] = 1; index < num4res; index++) // 资源遍历
						readyPids[pid] &= Need[pid][index] <= Work[index] ? 1 : 0;
					limit = readyPids[pid] == 1 ? pid : limit;
				}

			// 2，从readyPids中布尔值为true的进程中选出最大的占有资源者
			int maxPid = limit, index = 0, diff = 0; // maxPid，最大资源占有pid,，初始为上限pid
			for (int pid = 0; pid < limit; pid++)
				if (readyPids[pid] == 1) { // 只放入筛选队列的进程
					// 求出当前pid与maxPid之间的差距
					for (index = 0, diff = 0; index < num4res; index++)
						diff += Allocation[pid][index] - Allocation[maxPid][index];
					if (diff > 0) {
						readyPids[maxPid] = 0; // 筛选出来但没有进入安全序列的进程返回初始状态
						maxPid = pid;
					} else
						readyPids[pid] = 0; // 筛选出来但没有进入安全序列的进程返回初始状态
				}
			// 将当前进程的信息放入缓冲
			info.append(pidForSecurity(maxPid));
			// 3，释放选择进程的占有资源
			if (maxPid == -1) // 没有一个进程符合要求
				return RequestStatusEnum.UNSAFE_STATUS.addInfoToStatus(info.toString());
			else { // 假设，当前maxPid完成任务后会释放它占有的资源
				for (int i = 0; i < num4res; i++)
					Work[i] += Allocation[maxPid][i];
				readyPids[maxPid] = -1; // 将进程放入安全序列
			}
		}
		return RequestStatusEnum.SUCCESS.addInfoToStatus(info.toString());
	}

	/**
	 *  打印系统内部资源的分布情况
	 *  <p>
	 *  1，所有进程对每种资源的总需求量
	 *  <p>
	 *  2，系统对每个进程每种资源的已分配量
	 *  <p>
	 *  3，所有进程对每种资源的剩余需求量
	 */
	public void displaySystem() {
		System.out.println("============开始打印系统进程的资源情况============");
		// Max，Allocation，Need的分布情况
		StringBuilder max[] = statusFor(Max, SignEnum.MAX);
		StringBuilder allocation[] = statusFor(Allocation, SignEnum.ALLOCATION);
		StringBuilder need[] = statusFor(Need, SignEnum.NEED);
		// 打印
		for (int pid = 0; pid < num4prc; pid++) {
			StringBuilder sb = new StringBuilder();
			sb.append(pidForString(pid));
			sb.append(max[pid]).append(allocation[pid]).append(need[pid]);
			System.out.println(sb);
		}
		System.out.println("==========================================\r\n");
	}

	/**
	 *  打印要请求的资源详细
	 *  
	 * @param pid 发起请求的进程的 pid
	 * @param request 请求的资源
	 */
	public void displayRequest(int pid, int request[]) {
		System.out.printf("===========进程%d开始请求资源===========\r\n", pid);
		System.out.println(statusFor(request, SignEnum.REQUEST));
		System.out.println("==================================\r\n");
	}

	/**
	 * @param pid 进程 pid
	 * @param work 工作集，也是预期可使用量
	 * @return 当前进程 pid 在安全算法中的分布情况
	 */
	private StringBuilder pidForSecurity(int pid) {
		StringBuilder sb = new StringBuilder();
		if (pid < 0) {
			sb.append("当前预期可使用量\t");
			sb.append(statusFor(Work, SignEnum.WORK));
			sb.append("\t从需求矩阵中找不到下一个小于当前可使用量的进程");
		} else {
			sb.append(pidForString(pid));
			// Max，Allocation，Need
			StringBuilder allocation = statusFor(Allocation[pid], SignEnum.ALLOCATION);
			StringBuilder need = statusFor(Need[pid], SignEnum.NEED);
			StringBuilder work = statusFor(Work, SignEnum.WORK);
			sb.append(allocation).append(need).append(work);
		}
		sb.append("\r\n");
		return sb;
	}

	// 返回一个 pid 的字符串表现形式
	private String pidForString(int pid) {
		return "Pid[" + pid + "]\t";
	}

	/**
	 * @param matrix 项目分布矩阵
	 * @param sign 项目标识
	 * @return 获得当前系统内所有进程关于某项目的分布情况
	 */
	private StringBuilder[] statusFor(int matrix[][], SignEnum sign) {
		StringBuilder sb[] = new StringBuilder[num4prc];
		for (int pid = 0; pid < num4prc; pid++)
			sb[pid] = statusFor(matrix[pid], sign);
		return sb;
	}

	/**
	 * @param pid 进程id
	 * @param array 进程 id 的项目分布数组
	 * @param sign 项目的标识
	 * @return 获得单个进程关于某项目的分布情况
	 */
	private StringBuilder statusFor(int array[], SignEnum sign) {
		StringBuilder sb = new StringBuilder();
		for (int id = 0; id < num4res; id++)
			sb.append("  " + sign + "[" + int2char(id) + "]：" + array[id]);
		return sb;
	}

	// 将资源 id 的数字表示形式转化成字母表示形式
	private char int2char(int id) {
		return (char) (id + 65);
	}

}
