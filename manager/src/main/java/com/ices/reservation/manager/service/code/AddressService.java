package com.ices.reservation.manager.service.code;

import com.ices.pojo.code.Address;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 14:10 2018/3/31 0031
 */
@Service
public class AddressService extends BaseService<Address> {
    public Object getAddressTree(Long provinceId, Long cityId, Long countyId){
//        Address province = new Address();
//        province.setPreId("0");
//        List<Map> provinces = this.baseDao.selectNoPageList(pro)
//        // 查询county组
//        county.setId(null);
//        county.setPreId(map);
//        List<Map> = this.baseDao.selectNoPageList()
        return null;
    }
}
