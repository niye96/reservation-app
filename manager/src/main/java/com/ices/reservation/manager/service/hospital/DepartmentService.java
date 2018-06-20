package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Department;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 14:16 2018/4/12 0012
 */
@Service
public class DepartmentService extends BaseService<Department>{
    @Override
    protected void beforeAdd(Department clz) throws RuntimeException {
        clz.setDepartmentId(IdUtil.generateId());
    }

    public Object listByType(Department department){
        Map<String, List> res = new HashMap();
        List<Map> list = this.baseDao.selectNoPageList(department, null);

        if(list == null) return ReturnUtil.error("查询失败");

        for(int i = 0; i < list.size(); i++){
            String typeName = (String) list.get(i).get("typeName");
            if(res.containsKey(typeName)){
                res.get(typeName).add(list.get(i));
            }else{
                List type = new ArrayList();
                type.add(list.get(i));
                res.put(typeName, type);
            }
        }
        return ReturnUtil.success(res);
    }
}
