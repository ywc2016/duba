package job;

import duba.Duba;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by yuwc on 2017/6/1.
 */
public class DubaJob {
    public void execute() throws JobExecutionException {
        System.out.println("定时任务 DubaJob 开始运行！");
        Duba duba = new Duba();
        duba.getOneWithPagination(duba.getNewest());
        duba.getOneWithPagination(duba.getTech());
        duba.getOneWithPagination(duba.getInternational());
        duba.getOneWithPagination(duba.getEntertainment());
        duba.getOneWithPagination(duba.getMilitary());
        duba.getOneWithPagination(duba.getFinance());
        duba.getOneWithPagination(duba.getSport());
        System.out.println("定时任务 DubaJob 运行结束！");
    }
}
