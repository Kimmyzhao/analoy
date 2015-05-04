package cn.epalmpay.analoy.test;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 需要注意以下几点<br/>
 * 1、spring的@Scheduled注解 需要写在实现上<br/>
 * 2、 定时器的任务方法不能有返回值（如果有返回值，spring初始化的时候会告诉你有个错误<br/>
 * 需要设定一个proxytargetclass的某个值为true、具体就去百度google吧） <br/>
 * 3、实现类上要有组件的注解@Component
 * 
 * @author DELL
 *
 */
//@Component
public class Test {

	//@Scheduled(cron = "0 0/1 * * * ?")
	// 一分钟更新一次
	public void test0() {
		System.out.println("test0->" + new Date());
	}

	//@Scheduled(cron = "0/30 * * * * ?")
	// 30秒更新一次
	public void test1() {
		System.out.println("test1->" + new Date());
	}

	//@Scheduled(fixedDelay = 30000)
	public void test2() {
		System.out.println("test2->doing fixedDelay->" + new Date());
	}

	//@Scheduled(fixedRate = 50000)
	public void test3() {
		System.out.println("test3->doing fixedRate->" + new Date());
	}

	//@Scheduled(fixedDelay = 30000, initialDelay = 1000)
	public void test4() {
		System.out.println("test4->doing initialDelay->" + new Date());
	}

	//@Scheduled(fixedDelayString = "30000")
	public void test5() {
		System.out.println("test5->doing fixedDelayString->" + new Date());
	}
}
