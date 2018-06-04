/*
package tianmao.service.quartz;

import cn.alphaidea.wancheleyuan.common.model.quartzs.QuartzTaskData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

*/
/**
 * 任务调度携带数据服务接口
 *
 * @author roach
 * @date 2018/3/14
 *//*

@FeignClient(name = "${server.post.name}")
public interface QuartzTaskDataService {

    @RequestMapping(value = "/task/data/create")
    boolean create(@RequestParam("taskId") Long taskId, List<QuartzTaskData> quartzTaskDataList);

    */
/**
     * 根据任务编号获取数据
     *
     * @param taskId 任务编号
     * @return
     *//*

    @RequestMapping(value = "/task/data/task-id")
    Map<String, Object> getTaskDataByTaskId(@RequestParam("taskId") Long taskId);
}
*/
