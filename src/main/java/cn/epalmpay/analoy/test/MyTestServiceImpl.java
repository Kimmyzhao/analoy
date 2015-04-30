package cn.epalmpay.analoy.test;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author DELL<br/>
 *         需要注意以下几点<br/>
 *         1、spring的@Scheduled注解 需要写在实现上<br/>
 *         2、 定时器的任务方法不能有返回值（如果有返回值，spring初始化的时候会告诉你有个错误<br/>
 *         需要设定一个proxytargetclass的某个值为true、具体就去百度google吧） <br/>
 *         3、实现类上要有组件的注解@Component
 *
 */
@Component
public class MyTestServiceImpl implements IMyTestService {
	// 每5秒执行一次
	@Scheduled(cron = "0/5 * *  * * ? ")
	@Override
	public void myTest() {
		System.out.println("进入测试");
	}

}
